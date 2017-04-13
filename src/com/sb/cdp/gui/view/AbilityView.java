package com.sb.cdp.gui.view;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

// Is a class and not a .fxml for major performance reasons
public class AbilityView extends AnchorPane {
    Label name;
    Label cost;
    Label classRaceRequirements;
    Label otherRequirements;
    Text description;

    public AbilityView() {
	prefWidth(400);

	name = new Label("NAME");
	name.setStyle("-fx-font-weight: bold;");
	//name.layoutXProperty().set(14);
	//name.layoutYProperty().set(14);
	// TODO make the name's font bold
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
	setLeftAnchor(description, 5d);
	setTopAnchor(description, 52d);
    }
}
