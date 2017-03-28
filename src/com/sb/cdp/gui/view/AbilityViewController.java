package com.sb.cdp.gui.view;

import com.sb.cdp.ability.Ability;
import com.sb.cdp.ability.AbilityUtil;
import com.sb.cdp.ability.Condition;
import com.sb.util.ArrayUtil;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class AbilityViewController implements Controller {
    @FXML
    private Label name;
    @FXML
    private Label cost;
    @FXML
    private Label classRaceRequirements;
    @FXML
    private Label otherRequirements;
    @FXML
    private Text description;

    @FXML
    private AnchorPane anchorPane;

    private Ability ability;

    public AbilityViewController() {}

    @FXML
    private void initialize() {
	// Wrap the description text
	description.wrappingWidthProperty().bind(anchorPane.widthProperty());
    }

    @Override
    public void update() {
	name.setText(ability.getName());
	cost.setText(Integer.toString(ability.getCost()));
	classRaceRequirements.setText(
		AbilityUtil.characterTypeConditionsToString(ability.getCharacterTypeConditions()));
	otherRequirements.setText(ArrayUtil.join(ability.getPrerequisites(), Condition::describe, ", "));
	description.setText(ability.getDescription());
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
	update();
    }
}
