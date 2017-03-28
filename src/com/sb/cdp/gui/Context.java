package com.sb.cdp.gui;

import java.util.Stack;

import com.sb.cdp.gui.view.Controller;
import com.sb.util.Pair;

import javafx.scene.Node;

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

    public void precedent() {
	precedent(false);
    }

    public void precedent(boolean update) {
	if (!precedents.isEmpty()) {
	    current = precedents.pop();
	    if (update)
		current.getY().update();
	    contextSetter.set(current.getX());
	}
    }
    
    public void clear() {
	precedents.clear();
    }

    @FunctionalInterface
    public static interface ContextSetter {
	public <N extends Node> void set(N node);
    }
}
