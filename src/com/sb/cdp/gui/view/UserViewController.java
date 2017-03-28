package com.sb.cdp.gui.view;

import com.sb.cdp.PlayerCharacter;
import com.sb.cdp.User;
import com.sb.cdp.gui.FXUtil;
import com.sb.util.ConfirmationModel;
import com.sb.util.Pair;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

public class UserViewController implements Updateable {

    @FXML
    private Label name;
    @FXML
    private ListView<AnchorPane> characters;

    private User user;

    @Override
    public void update() {
	name.setText(user.getFirstName() + " " + user.getLastName());

	characters.getItems().clear();
	ObservableList<AnchorPane> views = FXCollections.observableArrayList();
	for (ConfirmationModel<PlayerCharacter> cm : user.getCharacters().values()) {
	    Pair<AnchorPane, UserCharacterViewController> pair = FXUtil.userCharacterView(cm);
	    Button delete = new Button("Supprimer");
	    delete.setOnAction((e) -> {
		user.getCharacters().remove(cm.getActive().getName());
		views.remove(pair.getX());
	    });
	    pair.getY().getBar().getButtons().add(delete);
	    views.add(pair.getX());
	}
	characters.setItems(views);
    }

    /**
     * Returns the user.
     * 
     * @return the user
     */
    public User getUser() {
	return user;
    }

    /**
     * Sets the value of user to that of the parameter.
     * 
     * @param user
     *            the user to set
     */
    public void setUser(User user) {
	this.user = user;
	update();
    }

}