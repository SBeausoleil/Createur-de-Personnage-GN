package com.sb.cdp.gui.view;

import com.sb.cdp.magic.Magic;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class MagicViewController implements Controller {

    @FXML
    private Label name;
    @FXML
    private Label magicType;
    @FXML
    private Label domain;
    @FXML
    private Label level;
    @FXML
    private Label duration;
    @FXML
    private Label cost;
    @FXML
    private Label casting;
    @FXML
    private Label range;
    @FXML
    private Text description;

    @FXML
    private AnchorPane anchorPane;

    private Magic magic;

    public MagicViewController() {}

    @FXML
    private void initialize() {
	// Wrap the description text
	description.wrappingWidthProperty().bind(anchorPane.widthProperty());
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
	name.setText(magic.getName());
	magicType.setText(magic.getDomain() != null ? magic.getDomain().getMagicType() : "");
	domain.setText(magic.getDomain() != null ? magic.getDomain().getName() : "");
	level.setText(Integer.toString(magic.getLevel()));
	duration.setText(magic.getDuration());
	cost.setText(magic.getCost());
	casting.setText(magic.getCasting());
	range.setText(magic.getRange());
	description.setText(magic.getDescription());
    }
}
