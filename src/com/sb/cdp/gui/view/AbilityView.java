package com.sb.cdp.gui.view;

import com.sb.cdp.gui.ModifiedIntegerProperty;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

// Is a class and not a .fxml for major performance reasons (11x faster loading)
public class AbilityView extends AnchorPane {
    Label name;
    Label cost;
    Label classRaceRequirements;
    Label otherRequirements;
    Text description;

    public AbilityView() {
	final double PREF_WIDTH = 400;
	minWidth(200);
	prefWidth(PREF_WIDTH);
	prefHeight(100);

	name = new Label("NAME");
	name.setStyle("-fx-font-weight: bold;");
	getChildren().add(name);
	setLeftAnchor(name, 5d);
	setTopAnchor(name, 5d);

	cost = new Label("COST");
	getChildren().add(cost);
	setRightAnchor(cost, 5d);
	setTopAnchor(cost, 5d);

	classRaceRequirements = new Label("CLASS/RACE REQUIREMENTS");
	getChildren().add(classRaceRequirements);
	setLeftAnchor(classRaceRequirements, 5d);
	setTopAnchor(classRaceRequirements, 22d);

	otherRequirements = new Label("OTHER REQUIREMENTS");
	getChildren().add(otherRequirements);
	setLeftAnchor(otherRequirements, 5d);
	setTopAnchor(otherRequirements, 37d);

	description = new Text("DESCRIPTION");
	getChildren().add(description);
	final double DISTANCE_FROM_BORDER = 5;
	setLeftAnchor(description, DISTANCE_FROM_BORDER);
	setTopAnchor(description, 52d);
	// Wrap the description text
	//description.wrappingWidthProperty().bind(widthProperty()); // Fails because the anchorpane automatically resizes for it
	ModifiedIntegerProperty wrapProperty = new ModifiedIntegerProperty();
	wrapProperty.bind(widthProperty());
	wrapProperty.setModifier((int) -DISTANCE_FROM_BORDER * 2); // Compensate for the left anchor and simulate it on the right side
	description.wrappingWidthProperty().bind(wrapProperty);
	description.setTextAlignment(TextAlignment.JUSTIFY);
    }
}
