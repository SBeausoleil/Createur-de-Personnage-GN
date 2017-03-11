package com.sb.util;

/**
 * A pair of data.
 * 
 * @author Samuel Beausoleil
 *
 * @param <X>
 * @param <Y>
 */
public class Pair<X, Y> {

    private X x;
    private Y y;
    
    public Pair() {}
    
    public Pair(X x, Y y) {
	this.x = x;
	this.y = y;
    }

    /**
     * Returns the x.
     * @return the x
     */
    public X getX() {
        return x;
    }

    /**
     * Sets the value of x to that of the parameter.
     * @param x the x to set
     */
    public void setX(X x) {
        this.x = x;
    }

    /**
     * Returns the y.
     * @return the y
     */
    public Y getY() {
        return y;
    }

    /**
     * Sets the value of y to that of the parameter.
     * @param y the y to set
     */
    public void setY(Y y) {
        this.y = y;
    }
    
    
}
