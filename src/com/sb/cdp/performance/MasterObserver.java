package com.sb.cdp.performance;

import java.util.LinkedList;

public class MasterObserver<Observer extends PerformanceObserver> {
    private LinkedList<Observer> observers;
    private String name;

    public MasterObserver(String name) {
	this.name = name;
	observers = new LinkedList<>();
    }

    public void addObserver(Observer po) {
	observers.add(po);
    }

    /**
     * Returns the observers.
     * 
     * @return the observers
     */
    public LinkedList<Observer> getObservers() {
	return observers;
    }

    /**
     * Sets the value of observers to that of the parameter.
     * 
     * @param observers
     *            the observers to set
     */
    public void setObservers(LinkedList<Observer> observers) {
	this.observers = observers;
    }

    /**
     * Returns the name.
     * 
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * Sets the value of name to that of the parameter.
     * 
     * @param name
     *            the name to set
     */
    public void setName(String name) {
	this.name = name;
    }

    public void printReport() {
	System.out.println(name);
	if (observers.isEmpty()) {
	    System.out.println("No data");
	    return;
	} else
	    System.out.println("Iterations: " + observers.size());

	long totalTime = 0;
	long maxTime = Long.MIN_VALUE;
	long minTime = Long.MAX_VALUE;

	long current;
	for (Observer observer : observers) {
	    current = observer.getTime();
	    totalTime += current;
	    if (current > maxTime)
		maxTime = current;
	    else if (current < minTime)
		minTime = current;
	}

	String timeScale = " " + observers.peek().getTimeScale();
	System.out.println("Total time: " + totalTime + timeScale);
	System.out.println("Max: " + maxTime + timeScale);
	System.out.println("Min: " + minTime + timeScale);
	System.out.println("Avg: " + (totalTime / observers.size()) + timeScale);
    }

}
