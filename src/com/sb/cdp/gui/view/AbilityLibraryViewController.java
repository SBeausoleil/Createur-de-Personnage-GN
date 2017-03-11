package com.sb.cdp.gui.view;

import java.io.IOException;

import com.sb.cdp.DesktopApplication;
import com.sb.cdp.Library;
import com.sb.cdp.ability.Ability;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
     * @param ability
     * @return
     * @throws IOException
     */
    private AnchorPane makeAbilityView(Ability ability) throws IOException {
	FXMLLoader loader = new FXMLLoader();
	loader.setLocation(DesktopApplication.class.getResource("gui/view/AbilityView.fxml"));
	AnchorPane abilityView = (AnchorPane) loader.load();

	AbilityViewController controller = loader.getController();
	controller.setAbility(ability);
	return abilityView;
    }

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
	for (Ability ability : abilities.values()) {
	    try {
		views.add(makeAbilityView(ability));
	    } catch (IOException e) {
		throw new RuntimeException(e);
	    }
	}
	list.setItems(views);
    }
}
