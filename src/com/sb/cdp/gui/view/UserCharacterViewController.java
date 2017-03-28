package com.sb.cdp.gui.view;

import com.sb.cdp.DesktopApplication;
import com.sb.cdp.PlayerCharacter;
import com.sb.cdp.gui.FXUtil;
import com.sb.util.ConfirmationModel;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class UserCharacterViewController implements Controller {

    @FXML
    private AnchorPane pane;
    @FXML
    private Label characterName;
    @FXML
    private Button confirmed;
    @FXML
    private Button submitted;
    @FXML
    private ButtonBar bar;

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

	confirmed.setDisable(userCharacter.getConfirmed() == null);
	submitted.setDisable(userCharacter.getPending() == null);
    }

    /**
     * Returns the bar.
     * 
     * @return the bar
     */
    public ButtonBar getBar() {
	return bar;
    }

    @FXML
    private void modifyConfirmed() {
	DesktopApplication.get().getRootContext().enter(
		FXUtil.characterEditView(DesktopApplication.get().getRpg(), userCharacter.getConfirmed()));
    }

    @FXML
    private void modifyPending() {
	DesktopApplication.get().getRootContext().enter(
		FXUtil.characterEditView(DesktopApplication.get().getRpg(), userCharacter.getPending()));
    }
}
