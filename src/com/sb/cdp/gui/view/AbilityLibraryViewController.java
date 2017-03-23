package com.sb.cdp.gui.view;

import com.sb.cdp.Library;
import com.sb.cdp.ability.Ability;
import com.sb.cdp.gui.FXUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;

public class AbilityLibraryViewController {
    @FXML
    private TitledPane titledPane;
    @FXML
    private ListView<AnchorPane> list;

    private Library<?, Ability> abilities;

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
	showAbilities();
    }

    private void showAbilities() {
	ObservableList<AnchorPane> views = FXCollections.observableArrayList();
	for (Ability ability : abilities.values())
	    views.add(FXUtil.abilityView(ability).getX());
	list.setItems(views);
    }
}
