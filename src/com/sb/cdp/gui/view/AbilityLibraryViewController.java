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

public class AbilityLibraryViewController implements Controller {
    @FXML
    private TitledPane titledPane;
    @FXML
    private ListView<AbilityView> list;
    private ObservableList<AbilityView> items;

    private Pair<String, Collection<Ability>> abilities;

    private ConcretePair<AbilityView, AbilityViewController>[] views;

    public AbilityLibraryViewController() {}

    @FXML
    private void initialize() {
	list.setPrefWidth(Double.MAX_VALUE); // TESTME
    }

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
	if (abilities != null)
	    titledPane.setText(abilities.getX());
	update();
    }

    @Override
    public void update() {
	if (abilities != null) {
	    this.views = new ConcretePair[abilities.getY().size()];
	    items = FXCollections.observableArrayList();
	    int nViews = 0;
	    ConcretePair<AbilityView, AbilityViewController> pair;
	    for (Ability ability : abilities.getY()) {
		pair = FXUtil.abilityView(ability);
		items.add(pair.getX());
		this.views[nViews++] = pair;
	    }
	    list.setItems(items);
	} else {
	    titledPane.setText("");
	    if (items != null)
		items.clear();
	    views = null;
	}
    }

    /**
     * Returns the views.
     * 
     * @return the views
     */
    public Pair<AbilityView, AbilityViewController>[] getViews() {
	return views;
    }

    /**
     * Returns the list.
     * 
     * @return the list
     */
    public ListView<AbilityView> getList() {
	return list;
    }
}
