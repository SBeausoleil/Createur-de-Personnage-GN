package com.sb.util;

import java.util.function.Consumer;

public class DraftModel<E> extends ConfirmationModel<E> {

    private E draft;

    public DraftModel() {
	super();
    }

    /**
     * Returns the draft.
     * 
     * @return the draft
     */
    public E getDraft() {
	return draft;
    }

    /**
     * Sets the value of draft to that of the parameter.
     * 
     * @param draft
     *            the draft to set
     */
    public void setDraft(E draft) {
	this.draft = draft;
    }

    @Override
    public void onSets(Consumer<E> action) {
	if (draft != null)
	    action.accept(draft);
	super.onSets(action);
    }
}
