package com.sb.cdp.gui;

import javafx.beans.property.SimpleIntegerProperty;

public class ModifiedIntegerProperty extends SimpleIntegerProperty {

    private int modifier;

    public ModifiedIntegerProperty() {
	super();
    }

    public ModifiedIntegerProperty(int initialValue, int modifier) {
	super(initialValue);
	this.modifier = modifier;
    }

    /**
     * Returns the modifier.
     * 
     * @return the modifier
     */
    public int getModifier() {
	return modifier;
    }

    /**
     * Sets the value of modifier to that of the parameter.
     * 
     * @param modifier
     *            the modifier to set
     */
    public void setModifier(int modifier) {
	this.modifier = modifier;
	if (!isBound())
	    super.set(super.get()); // Fires all the actions related to a normal setting of the value without actually changing it
    }

    @Override
    public int get() {
	return super.get() + modifier;
    }
}