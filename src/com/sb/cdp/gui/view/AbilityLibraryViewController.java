package com.sb.cdp.gui.view;

import com.sb.cdp.Library;
import com.sb.cdp.ability.Ability;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

public class AbilityLibraryViewController {
    @FXML
    private TableView<Ability> table;
    @FXML
    private TableColumn<Ability, AnchorPane /* AbilityView.FXML */> column;

    private Library<String, Ability> abilities;

    public AbilityLibraryViewController() {}

    // !!! TESTME !!!
    @FXML
    private void initialize() {
	column.setCellValueFactory(new Callback<CellDataFeatures<Ability, AnchorPane>, ObservableValue<AnchorPane>>() {
	    @Override
	    public ObservableValue<AnchorPane> call(CellDataFeatures<Ability, AnchorPane> abilityCell) {
		// Create an AnchorPane for this ability
		// TODO
		/*FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("view/AbilityView.fxml"));
		rootLayout = (BorderPane) loader.load();*/
		return null;
	    }

	});
    }

    /**
     * Returns the abilities.
     * 
     * @return the abilities
     */
    public Library<String, Ability> getAbilities() {
	return abilities;
    }

    /**
     * Sets the value of abilities to that of the parameter.
     * 
     * @param abilities
     *            the abilities to set
     */
    public void setAbilities(Library<String, Ability> abilities) {
	this.abilities = abilities;
    }
}
