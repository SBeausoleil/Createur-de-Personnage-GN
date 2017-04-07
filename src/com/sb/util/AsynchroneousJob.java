package com.sb.util;

public abstract class AsynchroneousJob<V> implements Runnable {

    protected boolean ready;
    protected boolean completed;

    protected V value;

    public AsynchroneousJob() {}

    /**
     * Checks if the value is ready to be get.
     * 
     * @return the ready
     */
    public boolean isReady() {
	return ready;
    }

    /**
     * Checks if the job is completed.
     * 
     * @return the completed
     */
    public boolean isCompleted() {
	return completed;
    }

    /**
     * Returns the value.
     * 
     * @return the value
     */
    public V getValue() {
	return value;
    }

}
