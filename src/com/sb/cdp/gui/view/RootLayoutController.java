package com.sb.cdp.gui.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;

public class RootLayoutController {
    @FXML
    private Button myCharacters;
    @FXML
    private TitledPane rpg;
    @FXML
    private Button abilities;
    @FXML
    private Button magic;
    @FXML
    private Button domains;
    @FXML
    private Button gods;
    @FXML
    private Button save;
    @FXML
    private Button exit;

    public RootLayoutController() {}

    @FXML
    private void initialize() {
	// Setting the buttons' width to Double.MAX_VALUE makes them take all the space available in their VBox cell.
	myCharacters.setPrefWidth(Double.MAX_VALUE);
	rpg.setPrefWidth(Double.MAX_VALUE);
	abilities.setPrefWidth(Double.MAX_VALUE);
	magic.setPrefWidth(Double.MAX_VALUE);
	domains.setPrefWidth(Double.MAX_VALUE);
	gods.setPrefWidth(Double.MAX_VALUE);
	save.setPrefWidth(Double.MAX_VALUE);
	exit.setPrefWidth(Double.MAX_VALUE);
    }
    
    @FXML
    private void handleCharacters() {
	// TODO
    }
    
    @FXML
    private void handleAbilities() {
	// TODO
    }
    
    @FXML
    private void handleMagic() {
	// TODO
    }
    
    @FXML
    private void handleDomains() {
	// TODO
    }

    @FXML
    private void handleGods() {
	// TODO
    }
        
    @FXML
    private void handleSave() {
	// TODO
    }

    @FXML
    private void handleExit() {
	// TODO
	System.exit(0);
    }
}
