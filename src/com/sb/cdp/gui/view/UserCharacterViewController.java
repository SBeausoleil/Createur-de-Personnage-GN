package com.sb.cdp.gui.view;

import com.sb.cdp.PlayerCharacter;
import com.sb.util.ConfirmationModel;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class UserCharacterViewController implements Updateable {

    @FXML
    private AnchorPane pane;
    @FXML
    private Label characterName;
    @FXML
    private Button confirmed;
    @FXML
    private Button submitted;

    private ConfirmationModel<PlayerCharacter> userCharacter;

    public UserCharacterViewController() {}

    public void setUserCharacter(ConfirmationModel<PlayerCharacter> userCharacter) {
	this.userCharacter = userCharacter;
	update();
    }

    @Override
    public void update() {
	if (userCharacter == null || userCharacter.getActive() == null)
	    throw new IllegalStateException(new NullPointerException(
		    "Cannot call update() on a UserCharacterViewController if the userCharacter or both of it's models is/are null."));

	characterName.setText(userCharacter.getActive().getName());

	if (userCharacter.getPending() == null && pane.getChildren().contains(submitted))
	    pane.getChildren().remove(submitted);
	else if (userCharacter.getPending() != null && !pane.getChildren().contains(submitted))
	    pane.getChildren().add(submitted);
    }
}
