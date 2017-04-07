package com.sb.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import com.sb.cdp.gui.view.AbilityLibraryViewController;
import com.sb.cdp.gui.view.AbilityViewController;

import javafx.application.Platform;
import javafx.scene.layout.AnchorPane;

public class Search {
    private int idGiver = 0;

    // Search parameters
    private String oldSearch;
    private String search;
    private boolean reset;

    /**
     * Map of all that is to be removed. Coalesced to build a runnable when removing for the JavaFX
     * thread.
     */
    private Map<AbilityLibraryViewController, LinkedList<Pair<AnchorPane, AbilityViewController>>> toRemove;

    private SearchThread[] threads;
    private volatile boolean interruptSearch;
    private ResettableCountDownLatch startLatch;
    private CyclicBarrier resetBarrier;
    private CyclicBarrier endBarrier;

    public Search(Collection<AbilityLibraryViewController> libs) {
	threads = new SearchThread[libs.size()];
	startLatch = new ResettableCountDownLatch(threads.length + 1); // +1 for the event thread
	resetBarrier = new CyclicBarrier(threads.length + 1); // +1 for the JFX thread (hit once the reset is complete)
	endBarrier = new CyclicBarrier(threads.length);
	oldSearch = search = "";
	initializeToRemove(libs);

	int i = 0;
	for (AbilityLibraryViewController lib : libs) {
	    threads[i] = new SearchThread(lib);
	    threads[i].setDaemon(true);
	    threads[i++].start();
	}

    }

    private void initializeToRemove(Collection<AbilityLibraryViewController> libs) {
	toRemove = new HashMap<>();
	for (AbilityLibraryViewController lib : libs)
	    toRemove.put(lib, new LinkedList<>());
    }

    public void search(String search) {
	System.out.println("search(" + search + ")");

	interruptSearch = true;
	oldSearch = this.search;
	this.search = search;
	reset = !search.contains(oldSearch);

	startLatch.countDown();
    }

    @Override
    protected void finalize() {
	System.out.println("search finalize()");
	for (int i = 0; i < threads.length; i++)
	    threads[i].interrupt(); // Wake the thread
	threads = null; // Free their reference
    }

    private class SearchThread extends Thread {
	private int id;

	private AbilityLibraryViewController libraryController;

	/**
	 * Stores the display index of each AbilityView pane.
	 */
	// Use LinkedHashMap to avoid IndexOutOfBoundsException when resetView()
	private LinkedHashMap<Pair<AnchorPane, AbilityViewController>, Integer> indexes;
	private boolean[] displayed;

	SearchThread(AbilityLibraryViewController libraryController) {
	    this.libraryController = libraryController;
	    id = idGiver++;

	    indexes = new LinkedHashMap<>(libraryController.getViews().length);
	    for (int i = 0; i < libraryController.getViews().length; i++)
		indexes.put(libraryController.getViews()[i], i);
	    displayed = new boolean[indexes.size()];
	    Arrays.fill(displayed, true);
	}

	@Override
	public void run() {
	    while (true) {
		try {
		    print("waiting to start...");
		    startLatch.countDown();
		    startLatch.await(); // Tripped by the event thread
		    if (id == 0) {
			interruptSearch = false;
			startLatch.reset();
		    }
		} catch (InterruptedException e) {
		    throw new RuntimeException(e);
		}

		// Wait for the reset
		if (reset) {
		    if (id == 0)
			Platform.runLater(() -> {
			    System.out.println("resetter: starting");
			    for (SearchThread thread : threads)
				thread.resetViews();
			    try {
				System.out.println("resetter: hitting barrier");
				resetBarrier.await();
			    } catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			    }
			    System.out.println("resetter: ready");
			});
		    try {
			print("waiting for reset end...");
			resetBarrier.await();
		    } catch (InterruptedException | BrokenBarrierException e) {
			throw new RuntimeException(e);
		    }
		}

		print("searching");
		for (Pair<AnchorPane, AbilityViewController> ability : libraryController.getViews()) {
		    if (displayed[indexes.get(ability)] && !ability.getY().getAbility().getName().contains(search))
			toRemove.get(libraryController).add(ability);

		    if (interruptSearch)
			break;
		}

		try {
		    print("waiting for other searchers to finish");
		    endBarrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
		    throw new RuntimeException(e);
		}

		if (id == 0)
		    // Create a runnable which will remove all the marked views
		    Platform.runLater(() -> {
			System.out.println("remover: starting");
			for (SearchThread thread : threads) {
			    LinkedList<Pair<AnchorPane, AbilityViewController>> list = toRemove.get(
				    thread.libraryController);
			    System.out.print("Removal list for thread " + thread.id + " is ");
			    System.out.println(list.isEmpty() ? "empty" : "not empty");
			    while (!list.isEmpty()) {
				Pair<AnchorPane, AbilityViewController> ability = list.removeLast(); // Remove from the end of the list in hope of reducing number of elements to move in the UI
				thread.libraryController.getList().getItems().remove(ability.getX());
				thread.displayed[thread.indexes.get(ability)] = false;
			    }
			}
			System.out.println("remover: ready");
		    });
	    }
	}

	public void resetViews() {
	    for (Map.Entry<Pair<AnchorPane, AbilityViewController>, Integer> entry : indexes.entrySet())
		if (!displayed[entry.getValue()]) {
		    libraryController.getList().getItems().add(entry.getValue(), entry.getKey().getX());
		    displayed[entry.getValue()] = true;
		}
	}

	@Override
	protected void finalize() {
	    // TODO delete me when certain that finalization is always ready correctly.
	    System.out.println("SearchThread " + id + " finalized");
	}

	private void print(String msg) {
	    System.out.println("SearchThread " + id + ": " + msg);
	}
    }
}
