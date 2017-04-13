package com.sb.cdp.gui.view;

import com.sb.cdp.ability.Ability;
import com.sb.cdp.ability.AbilityUtil;
import com.sb.cdp.ability.Condition;
import com.sb.util.ArrayUtil;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class AbilityViewController implements Controller {
    private AbilityView view;

    private Ability ability;

    public AbilityViewController() {}

    public AbilityViewController(AbilityView view) {
	setView(view);
    }

    @FXML
    private void initialize() {
	// Wrap the description text
	view.description.wrappingWidthProperty().bind(view.widthProperty());
    }

    @Override
    public void update() {
	if (ability != null) {
	    view.name.setText(ability.getName());
	    view.cost.setText(Integer.toString(ability.getCost()));
	    view.classRaceRequirements.setText(
		    AbilityUtil.characterTypeConditionsToString(ability.getCharacterTypeConditions()));
	    view.otherRequirements.setText(ArrayUtil.join(ability.getPrerequisites(), Condition::describe, ", "));
	    view.description.setText(ability.getDescription());
	}
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

    /**
     * Sets the value of view to that of the parameter.
     * 
     * @param view
     *            the view to set
     */
    public void setView(AbilityView view) {
	this.view = view;
	update();
    }
}
