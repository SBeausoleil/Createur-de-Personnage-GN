package com.sb.util;

import java.util.function.Consumer;

public class ConfirmationModel<E> {

    private E confirmed;
    private E pending;

    public ConfirmationModel() {
	this(null);
    }

    public ConfirmationModel(E confirmed) {
	this.confirmed = confirmed;
	this.pending = null;
    }

    /**
     * Returns the confirmed.
     * 
     * @return the confirmed
     */
    public E getConfirmed() {
	return confirmed;
    }

    /**
     * Sets the value of confirmed to that of the parameter.
     * 
     * @param confirmed
     *            the confirmed to set
     */
    public void setConfirmed(E confirmed) {
	this.confirmed = confirmed;
    }

    /**
     * Returns the pending.
     * 
     * @return the pending
     */
    public E getPending() {
	return pending;
    }

    /**
     * Sets the value of pending to that of the parameter.
     * 
     * @param pending
     *            the pending to set
     */
    public void setPending(E pending) {
	this.pending = pending;
    }

    public boolean isPending() {
	return pending != null;
    }

    public void confirm() {
	confirmed = pending;
	pending = null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "ConfirmationModel [confirmed=" + confirmed + ", pending=" + pending + "]";
    }

    /**
     * Returns the last modified of the two models.
     * If one of the two is null, returns the non-null.
     * If both are non-null, return the submitted.
     * If both are null, return null.
     * 
     * @return
     */
    public E getActive() {
	if (pending != null)
	    return pending;
	return confirmed;
    }

    /**
     * Does an action on all the models that are non-null.
     * 
     * @param action
     */
    public void onSets(Consumer<E> action) {
	if (confirmed != null)
	    action.accept(confirmed);
	if (pending != null)
	    action.accept(pending);
    }
}
