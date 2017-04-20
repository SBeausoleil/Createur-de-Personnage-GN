package com.sb.cdp.gui;

import java.util.LinkedList;

import com.sb.util.ConcretePair;

import javafx.scene.control.TextField;

public class InvalidFieldException extends IllegalStateException {
    private static final long serialVersionUID = -3197261795827942830L;

    private LinkedList<ConcretePair<String, TextField>> invalidFields;

    public InvalidFieldException(LinkedList<ConcretePair<String, TextField>> invalidFields) {
	this.invalidFields = invalidFields;
    }

    /**
     * Returns the invalidFields.
     * getX(): Issue
     * getY(): Field
     * 
     * @return the invalidFields
     */
    public LinkedList<ConcretePair<String, TextField>> getInvalidFields() {
	return invalidFields;
    }
}
