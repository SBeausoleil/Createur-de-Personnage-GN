package com.sb.cdp.gui.view;

import java.util.Collection;

import com.sb.cdp.ability.Ability;
import com.sb.cdp.gui.FXUtil;
import com.sb.util.ConcretePair;
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

    private Pair<String, Collection<Ability>> abilities;

    private ConcretePair<AnchorPane, AbilityViewController>[] views;
    
    public AbilityLibraryViewController() {}

    @FXML
    private void initialize() {}

    /**
     * Returns the abilities.
     * 
     * @return the abilities
     */
    public Pair<String, Collection<Ability>> getAbilities() {
	return abilities;
    }

    /**
     * Sets the value of abilities to that of the parameter.
     * 
     * @param abilities
     *            the abilities to set
     */
    public void setAbilities(Pair<String, Collection<Ability>> abilities) {
	this.abilities = abilities;
	titledPane.setText(abilities.getX());
	update();
    }

    @Override
    public void update() {
	this.views = new ConcretePair[abilities.getY().size()];
	ObservableList<AnchorPane> viewsList = FXCollections.observableArrayList();
	int nViews = 0;
	for (Ability ability : abilities.getY()) {
	    ConcretePair<AnchorPane, AbilityViewController> pair = FXUtil.abilityView(ability);
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
