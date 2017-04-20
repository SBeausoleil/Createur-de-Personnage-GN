package com.sb.util;

/**
 * A pair of data.
 * 
 * @author Samuel Beausoleil
 * @param <X>
 * @param <Y>
 */
public class ConcretePair<X, Y> implements Pair<X, Y> {

    private X x;
    private Y y;

    public ConcretePair() {}

    public ConcretePair(X x, Y y) {
	this.x = x;
	this.y = y;
    }

    /**
     * Returns the x.
     * 
     * @return the x
     */
    @Override
    public X getX() {
	return x;
    }

    /**
     * Sets the value of x to that of the parameter.
     * 
     * @param x
     *            the x to set
     */
    @Override
    public void setX(X x) {
	this.x = x;
    }

    /**
     * Returns the y.
     * 
     * @return the y
     */
    @Override
    public Y getY() {
	return y;
    }

    /**
     * Sets the value of y to that of the parameter.
     * 
     * @param y
     *            the y to set
     */
    @Override
    public void setY(Y y) {
	this.y = y;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "ConcretePair [x=" + x + ", y=" + y + "]";
    }

}
