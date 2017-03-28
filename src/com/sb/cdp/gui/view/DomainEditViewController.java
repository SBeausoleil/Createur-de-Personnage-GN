package com.sb.cdp.gui.view;

import com.sb.cdp.magic.Domain;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DomainEditViewController implements Controller {
    @FXML
    private TextField name;
    @FXML
    private TextField magicType;
    @FXML
    private TextArea description;
    @FXML
    private Button spells;

    @FXML
    private Button confirm;
    @FXML
    private Button delete;
    @FXML
    private Button cancel;

    private Domain domain;

    public DomainEditViewController() {}

    @FXML
    private void initialize() {
	description.setWrapText(true);
    }

    /**
     * Returns the domain.
     * 
     * @return the domain
     */
    public Domain getDomain() {
	return domain;
    }

    /**
     * Sets the value of domain to that of the parameter.
     * 
     * @param domain
     *            the domain to set
     */
    public void setDomain(Domain domain) {
	this.domain = domain;
	update();
    }

    @Override
    public void update() {
	if (domain != null) {
	    name.setText(domain.getName());
	    magicType.setText(domain.getMagicType());
	    description.setText(domain.getDescription());
	} else {
	    name.setText("");
	    magicType.setText("");
	    description.setText("");
	}
    }
}
