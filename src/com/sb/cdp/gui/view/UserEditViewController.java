package com.sb.cdp.gui.view;

import java.time.LocalDate;

import com.sb.cdp.User;
import com.sb.util.DateUtil;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class UserEditViewController {
    
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField birthdayField;

    private User user;

    public UserEditViewController() {}

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {}

    public void setUser(User user) {
	this.user = user;
	showUser();
    }

    private void showUser() {
	if (user != null) {
	    firstNameField.setText(user.getFirstName());
	    lastNameField.setText(user.getLastName());
	    emailField.setText(user.getLastName());
	    birthdayField.setText(DateUtil.format(user.getBirthday()));
	} else {
	    firstNameField.setText("");
	    lastNameField.setText("");
	    emailField.setText("");
	    birthdayField.setText("");
	}
    }

    @FXML
    public void handleConfirm() {
	LocalDate birthday = DateUtil.parse(birthdayField.getText());
	if (birthday != null) {
	    if (user == null)
		user = new User(null, null, birthday, null);
	    user.setFirstName(firstNameField.getText());
	    user.setLastName(lastNameField.getText());
	    user.setEmail(emailField.getText());
	    user.setBirthday(birthday);
	} else {
	    // TODO Popup an error message regarding the date's formatting
	}
    }

    /**
     * Returns the user.
     * 
     * @return the user
     */
    public User getUser() {
	return user;
    }
}
