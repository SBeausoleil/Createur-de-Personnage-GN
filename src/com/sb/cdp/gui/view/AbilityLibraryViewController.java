package com.sb.cdp.gui.view;

import java.util.Collection;

import com.sb.cdp.ability.Ability;
import com.sb.cdp.gui.FXUtil;
import com.sb.util.ConcretePair;
import com.sb.util.Pair;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class AbilityLibraryViewController implements Controller {
    private TitledListView<AbilityView> view;

    private ObservableList<AbilityView> items;

    private Pair<String, Collection<Ability>> abilities;

    private ConcretePair<AbilityView, AbilityViewController>[] views;

    public AbilityLibraryViewController() {}

    public AbilityLibraryViewController(TitledListView view) {
	this.view = view;
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
	    view.setText(abilities.getX());
	update();
    }

    @Override
    public void update() {
	if (abilities != null && view != null) {
	    this.views = new ConcretePair[abilities.getY().size()];
	    items = FXCollections.observableArrayList();
	    int nViews = 0;
	    ConcretePair<AbilityView, AbilityViewController> pair;
	    for (Ability ability : abilities.getY()) {
		pair = FXUtil.abilityView(ability);
		items.add(pair.getX());
		this.views[nViews++] = pair;
	    }
	    view.getList().setItems(items);
	} else if (view != null) {
	    view.setText("");
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
     * @return the list of AbilityView.
     */
    public ListView<AbilityView> getList() {
	return view.getList();
    }

    /**
     * Returns the view.
     * @return the view
     */
    public TitledListView<AbilityView> getView() {
        return view;
    }
    
    /**
     * Removes an ability from the library and the view.
     * @param ability
     * @return true if library was modified.
     */
    public boolean remove(Ability ability) {
	view.getList().getItems().remove(ability);
	// TODO finish
	return false;
    }
}
