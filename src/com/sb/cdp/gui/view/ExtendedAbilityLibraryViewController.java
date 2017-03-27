package com.sb.cdp.gui.view;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import com.sb.cdp.Library;
import com.sb.cdp.ability.Ability;
import com.sb.cdp.gui.FXUtil;
import com.sb.util.Pair;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class ExtendedAbilityLibraryViewController implements Updateable {
    private static int idGiver = 0;

    @FXML
    private TextField searchField;
    @FXML
    private VBox layout;

    private Collection<Library<String, Ability>> libraries;

    private CyclicBarrier searchBarrier;
    private CyclicBarrier searchEndBarrier;
    private boolean stopSearch;
    private SearchThread[] searchThreads;
    private String oldSearch;
    private String search;
    private Map<AbilityLibraryViewController, AnchorPane> toRemove;

    public ExtendedAbilityLibraryViewController() {}

    @FXML
    private void initialize() {
	searchField.textProperty().addListener(this::search);
    }

    private void search(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	System.out.println("search()");
	stopSearch = true;
	this.oldSearch = oldValue;
	this.search = newValue;

	try {
	    System.out.println(
		    "Barrier capacity: " + searchBarrier.getParties() + ", number waiting: "
			    + searchBarrier.getNumberWaiting());
	    System.out.println("event thread waiting...");
	    searchBarrier.await();
	} catch (InterruptedException | BrokenBarrierException e) {
	    e.printStackTrace();
	} finally {
	    stopSearch = false;
	}

	try {
	    System.out.println("event thread waiting for search end");
	    searchEndBarrier.await();
	} catch (InterruptedException | BrokenBarrierException e) {
	    e.printStackTrace();
	}

	System.out.println("event thread freed");
    }

    @Override
    public void update() {
	// Remove everything that follows the searchField bar within the layout
	while (layout.getChildren().size() > 1)
	    layout.getChildren().remove(1);

	if (libraries != null) {
	    // Get the total number of abilityLibraryViews that will be stored (this is an optimization to avoid resizing the abilityLibraryViews arraylist and taking more memory than needed)
	    searchThreads = new SearchThread[libraries.size()];
	    searchBarrier = new CyclicBarrier(searchThreads.length + 1); // +1 for the event thread to break it
	    searchEndBarrier = new CyclicBarrier(searchThreads.length + 1);

	    int nSearchThread = 0;
	    // Add AbilityLibraryView for each libraries
	    for (Library<?, Ability> lib : libraries) {
		Pair<AnchorPane, AbilityLibraryViewController> pair = FXUtil.abilityLibraryView(lib);
		layout.getChildren().add(pair.getX());
		searchThreads[nSearchThread] = new SearchThread(pair.getY());
		searchThreads[nSearchThread].start();
		nSearchThread++;
	    }
	}
    }

    /**
     * Returns the libraries.
     * 
     * @return the libraries
     */
    public Collection<Library<String, Ability>> getLibraries() {
	return libraries;
    }

    /**
     * Sets the value of libraries to that of the parameter.
     * 
     * @param collection
     *            the libraries to set
     */
    public void setLibraries(Collection<Library<String, Ability>> collection) {
	this.libraries = collection;
	update();
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
	    System.out.println("libraryController.getViews().length: " + libraryController.getViews().length);

	    indexes = new LinkedHashMap<>(libraryController.getViews().length);
	    for (int i = 0; i < libraryController.getViews().length; i++)
		indexes.put(libraryController.getViews()[i], i);
	    displayed = new boolean[indexes.size()];
	    Arrays.fill(displayed, true);
	    System.out.println("indexes.size(): " + indexes.size());
	    System.out.println("displayed.length: " + displayed.length);

	    id = idGiver++;
	}

	@Override
	public void run() {
	    while (true) {
		try {
		    System.out.println("SearchThread " + id + " waiting...");
		    searchBarrier.await(); // Tripped by the event thread
		} catch (InterruptedException | BrokenBarrierException e) {
		    e.printStackTrace();
		}

		System.out.println("SearchThread " + id + " active");

		for (Pair<AnchorPane, AbilityViewController> ability : libraryController.getViews()) {
		    if (displayed[indexes.get(ability)] && !ability.getY().getAbility().getName().contains(search)) {
			libraryController.getList().getItems().remove(ability.getX());
			displayed[indexes.get(ability)] = false; // O(1), so no need for further optimization.
		    }
		    if (stopSearch)
			break;
		}
	    }
	}

	public void resetViews() {
	    for (Map.Entry<Pair<AnchorPane, AbilityViewController>, Integer> entry : indexes.entrySet())
		if (!displayed[entry.getValue()])
		    libraryController.getList().getItems().add(entry.getValue(), entry.getKey().getX());
	}
    }
}
