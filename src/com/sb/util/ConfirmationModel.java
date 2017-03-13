package com.sb.util;

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
}