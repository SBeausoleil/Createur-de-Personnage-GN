package com.sb.cdp.gui.view;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

import com.sb.cdp.ability.Ability;
import com.sb.util.Strings;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AbilityEditViewController {

    @FXML
    private TextField name;
    @FXML
    private TextField cost;
    @FXML
    private TextField maximalTimesTaken;
    @FXML
    private TextArea description;

    @FXML
    private Button delete; // This button is visible depending upon the state (null or non-null) of allAbilities

    @FXML
    private AnchorPane anchorPane;

    private Ability ability;

    private Map<String, Ability> ownerLibrary;
    private Collection<Map<String, Ability>> allAbilities;

    public AbilityEditViewController() {}

    @FXML
    private void initialize() {
	description.setWrapText(true);
	delete.setVisible(allAbilities != null);
    }

    @FXML
    private void openConditions() {
	// TODO
    }

    @FXML
    private void handleConfirm() {
	if (handleSave()) {
	    // TODO exit edit context
	}
    }

    @FXML
    private boolean handleSave() {
	boolean saved = false;
	// Check if there already exists an ability with this name
	Ability alreadyExisting = ownerLibrary.get(name.getText());
	// If that already existing ability is not the currently modified one, popup an error message
	if (alreadyExisting != ability) {
	    // TODO popup error message warning that there already exists an ability under this name in the owner library
	} else {
	    TextField[] invalidFields = validate();
	    if (invalidFields.length == 0) {
		saved = true;
		if (ability == null)
		    ability = new Ability(name.getText());
		ability.setName(name.getText());
		ability.setCost(Integer.parseInt(cost.getText()));
		ability.setMaxTimesTaken(Integer.parseInt(maximalTimesTaken.getText()));
		ability.setDescription(description.getText());
	    } else {
		// TODO Make the border of the invalid fields red
	    }
	}
	return saved;
    }

    private TextField[] validate() {
	LinkedList<TextField> invalids = new LinkedList<>();
	// Cost must be an integer
	if (!Strings.isNumber(cost.getText(), 0, false, true))
	    invalids.add(cost);
	// MaximalTimesTaken must be an integer and not zero
	if (!Strings.isNumber(maximalTimesTaken.getText(), 0, false, true)
		|| Strings.isNumber(maximalTimesTaken.getText(), 0, false, true)
			&& Integer.parseInt(maximalTimesTaken.getText()) == 0)
	    invalids.add(cost);
	return invalids.toArray(new TextField[invalids.size()]);
    }

    @FXML
    private void handleDelete() {

    }

    @FXML
    private void handleCancel() {

    }

    /**
     * Returns the ability.
     * 
     * @return the ability
     */
    public Ability getAbility() {
	return ability;
    }

    /**
     * Sets the value of ability to that of the parameter.
     * 
     * @param ability
     *            the ability to set
     */
    public void setAbility(Ability ability) {
	this.ability = ability;
	showAbility();
    }

    private void showAbility() {
	if (ability != null) {
	    name.setText(ability.getName());
	    cost.setText(Integer.toString(ability.getCost()));
	    maximalTimesTaken.setText(Integer.toString(ability.getMaxTimesTaken()));
	    description.setText(ability.getDescription());
	} else {
	    name.setText("");
	    cost.setText("1");
	    maximalTimesTaken.setText("1");
	    description.setText("");
	}
    }

    /**
     * Returns the ownerLibrary.
     * 
     * @return the ownerLibrary
     */
    public Map<String, Ability> getOwnerLibrary() {
	return ownerLibrary;
    }

    /**
     * Sets the value of ownerLibrary to that of the parameter.
     * 
     * @param ownerLibrary
     *            the ownerLibrary to set
     */
    public void setOwnerLibrary(Map<String, Ability> ownerLibrary) {
	this.ownerLibrary = ownerLibrary;
    }

    /**
     * Returns the allAbilities.
     * 
     * @return the allAbilities
     */
    public Collection<Map<String, Ability>> getAllAbilities() {
	return allAbilities;
    }

    /**
     * Sets the value of allAbilities to that of the parameter.
     * 
     * @param allAbilities
     *            the allAbilities to set
     */
    public void setAllAbilities(Collection<Map<String, Ability>> allAbilities) {
	this.allAbilities = allAbilities;
	delete.setVisible(allAbilities != null);
    }
}
