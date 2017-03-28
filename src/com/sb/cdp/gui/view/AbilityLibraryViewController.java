package com.sb.cdp.gui.view;

import com.sb.cdp.Library;
import com.sb.cdp.ability.Ability;
import com.sb.cdp.gui.FXUtil;
import com.sb.util.Pair;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;

public class AbilityLibraryViewController implements Controller {
    @FXML
    private TitledPane titledPane;
    @FXML
    private ListView<AnchorPane> list;

    private Library<?, Ability> abilities;

    private Pair<AnchorPane, AbilityViewController>[] views;
    
    public AbilityLibraryViewController() {}

    @FXML
    private void initialize() {}

    /**
     * Returns the abilities.
     * 
     * @return the abilities
     */
    public Library<?, Ability> getAbilities() {
	return abilities;
    }

    /**
     * Sets the value of abilities to that of the parameter.
     * 
     * @param abilities
     *            the abilities to set
     */
    public void setAbilities(Library<?, Ability> abilities) {
	this.abilities = abilities;
	titledPane.setText(abilities.getName());
	update();
    }

    @Override
    public void update() {
	this.views = new Pair[abilities.values().size()];
	ObservableList<AnchorPane> viewsList = FXCollections.observableArrayList();
	int nViews = 0;
	for (Ability ability : abilities.values()) {
	    Pair<AnchorPane, AbilityViewController> pair = FXUtil.abilityView(ability);
	    viewsList.add(pair.getX());
	    this.views[nViews++] = pair;
	}
	list.setItems(viewsList);
    }

    /**
     * Returns the views.
     * @return the views
     */
    public Pair<AnchorPane, AbilityViewController>[] getViews() {
        return views;
    }

    /**
     * Returns the list.
     * @return the list
     */
    public ListView<AnchorPane> getList() {
        return list;
    }
}
