package com.sb.cdp.gui;

import java.util.Stack;

import com.sb.cdp.gui.view.Controller;
import com.sb.util.Pair;

import javafx.scene.Node;

/**
 * A context manager for JavaFX. Allows a stack of preceding views to be remembered.
 * 
 * @author Samuel Beausoleil
 */
public class Context {

    private ContextSetter contextSetter;

    private Pair<? extends Node, ? extends Controller> current;
    private Stack<Pair<? extends Node, ? extends Controller>> precedents;

    public Context(ContextSetter contextSetter) {
	this.contextSetter = contextSetter;
	precedents = new Stack<>();
    }

    public void enter(Pair<? extends Node, ? extends Controller> next) {
	precedents.push(current);
	current = next;
	contextSetter.set(next.getX());
    }

    /**
     * Changes the view to the precedent one.
     */
    public void precedent() {
	precedent(false);
    }

    /**
     * Changes the view to the precedent one and updates it.
     * 
     * @param update
     *            if or not to update the precedent view before displaying it.
     */
    public void precedent(boolean update) {
	if (!precedents.isEmpty()) {
	    current = precedents.pop();
	    if (update && current.getY() != null) // Tolerate null controllers
		current.getY().update();
	    contextSetter.set(current.getX());
	}
    }

    /**
     * Clears the stack of precedent views.
     */
    public void clear() {
	precedents.clear();
    }

    @FunctionalInterface
    public static interface ContextSetter {
	public <N extends Node> void set(N node);
    }
}
