package com.sb.cdp.gui.view;

import java.util.ArrayList;
import java.util.Collection;

import com.sb.cdp.DesktopApplication;
import com.sb.cdp.Library;
import com.sb.cdp.ability.Ability;
import com.sb.cdp.gui.Context;
import com.sb.cdp.gui.FXUtil;
import com.sb.util.Pair;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;

public class RootLayoutController implements Controller {
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

    private Context context;

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

	// Create the context
	context = new Context(layout::setCenter);
    }

    @FXML
    private void handleCharacters() {
	context.clear();
	context.enter(FXUtil.userView(DesktopApplication.get().getUser()));
    }

    @FXML
    private void handleAbilities() {
	context.clear();
	
	Collection<Library<String, Ability>> abilities = DesktopApplication.get().getRpg().getAbilityLibraries().values();
	ArrayList<Pair<String,Collection<Ability>>> pairs = new ArrayList<>(abilities.size());
	for (Library<String, Ability> lib : abilities)
	    pairs.add(lib);
	
	context.enter(FXUtil.extendedAbilityLibraryView(pairs));
	
//	FXUtil.extendedAbilityLibraryObserver.printReport();
//	System.out.println();
//	FXUtil.abilityLibraryObserver.printReport();
//	System.out.println();
//	FXUtil.abilityObserver.printReport();
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
	System.exit(0);
    }

    /**
     * Returns the context.
     * 
     * @return the context
     */
    public Context getContext() {
	return context;
    }

    @Override
    public void update() {}
}
