package com.sb.cdp.gui.view;

import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;

public class TitledListView<E> extends TitledPane {

    private ListView<E> list;

    public TitledListView() {
	setList(new ListView());
    }

    /**
     * Returns the list.
     * @return the list
     */
    public ListView<E> getList() {
        return list;
    }

    public void setList(ListView<E> list) {
	this.list = list;
	setContent(list);
    }
}
