package com.sb.cdp.gui.view;

import com.sb.cdp.magic.Magic;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MagicEditViewController implements Controller {
    @FXML
    private TextField name;
    @FXML
    private TextField level;
    @FXML
    private TextField cost;
    @FXML
    private TextField duration;
    @FXML
    private TextField casting;
    @FXML
    private TextField range;
    @FXML
    private TextField domain;
    @FXML
    private TextField magicType;
    @FXML
    private TextArea description;

    @FXML
    private Button confirm;
    @FXML
    private Button delete;
    @FXML
    private Button cancel;

    private Magic magic;

    public MagicEditViewController() {}

    @FXML
    private void initialize() {
	description.setWrapText(true);
    }

    /**
     * Returns the magic.
     * 
     * @return the magic
     */
    public Magic getMagic() {
	return magic;
    }

    /**
     * Sets the value of magic to that of the parameter.
     * 
     * @param magic
     *            the magic to set
     */
    public void setMagic(Magic magic) {
	this.magic = magic;
	update();
    }

    @Override
    public void update() {
	if (magic != null) {
	    name.setText(magic.getName());
	    level.setText(Integer.toString(magic.getLevel()));
	    cost.setText(magic.getCost());
	    duration.setText(magic.getDuration());
	    casting.setText(magic.getCasting());
	    range.setText(magic.getRange());
	    domain.setText(magic.getDomain() != null ? magic.getDomain().getName() : "");
	    magicType.setText(magic.getDomain() != null ? magic.getDomain().getMagicType() : "");
	} else {
	    name.setText("");
	    level.setText("");
	    cost.setText("");
	    duration.setText("");
	    casting.setText("");
	    range.setText("");
	    domain.setText("");
	    magicType.setText("");
	}
    }
}
