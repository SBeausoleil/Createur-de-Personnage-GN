package com.sb.cdp.gui.view;

import com.sb.cdp.DesktopApplication;
import com.sb.cdp.gui.FXUtil;
import com.sb.util.Pair;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class RootLayoutController {
    @FXML
    private Button myCharacters;
    @FXML
    private TitledPane rpgSection;
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

    @FXML
    private BorderPane layout;

    public RootLayoutController() {}

    @FXML
    private void initialize() {
	// Setting the buttons' width to Double.MAX_VALUE makes them take all the space available in their VBox cell.
	myCharacters.setPrefWidth(Double.MAX_VALUE);
	rpgSection.setPrefWidth(Double.MAX_VALUE);
	abilities.setPrefWidth(Double.MAX_VALUE);
	magic.setPrefWidth(Double.MAX_VALUE);
	domains.setPrefWidth(Double.MAX_VALUE);
	gods.setPrefWidth(Double.MAX_VALUE);
	save.setPrefWidth(Double.MAX_VALUE);
	exit.setPrefWidth(Double.MAX_VALUE);
    }

    @FXML
    private void handleCharacters() {
	Pair<VBox, UserViewController> pair = FXUtil.userView(DesktopApplication.get().getUser());
	layout.setCenter(pair.getX());
    }

    @FXML
    private void handleAbilities() {
	Pair<AnchorPane, ExtendedAbilityLibraryViewController> pair = FXUtil.extendedAbilityLibraryViewController(
		DesktopApplication.get().getRpg().getAbilityLibraries().values());
	layout.setCenter(pair.getX());
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
