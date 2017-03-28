package com.sb.cdp.gui;

import java.util.Stack;

import javafx.scene.Node;

public class Context {

    private ContextSetter contextSetter;

    private Node current;
    private Stack<Node> precedents;

    public Context(ContextSetter contextSetter) {
	this.contextSetter = contextSetter;
	precedents = new Stack<>();
    }

    public void enter(Node next) {
	precedents.push(current);
	current = next;
	contextSetter.set(next);
    }

    public void precedent() {
	if (!precedents.isEmpty()) {
	    current = precedents.pop();
	    contextSetter.set(current);
	}
    }

    public void clear() {
	precedents.clear();
    }

    @FunctionalInterface
    public static interface ContextSetter {
	public void set(Node node);
    }
}
