package com.sb.cdp;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.sb.cdp.ability.Ability;
import com.sb.cdp.gui.FXUtil;
import com.sb.cdp.gui.view.AbilityLibraryViewController;
import com.sb.cdp.gui.view.RootLayoutController;
import com.sb.cdp.idl.Initializer;
import com.sb.util.Pair;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class DesktopApplication extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    private User user;
    private RPG idl;

    // Called by the superclass
    public DesktopApplication() {
	idl = null;
	try {
	    idl = Initializer.initialize();
	} catch (FileNotFoundException e) {
	    System.err.println("One of the files needed for the initialization of the RPG has not been found.");
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	if (idl == null)
	    System.exit(1);
    }

    // Called by the superclass Application after the constructor
    @Override
    public void start(Stage primaryStage) throws Exception {
	this.primaryStage = primaryStage;
	primaryStage.setTitle("Gestionnaire de GN");

	initRootLayout();
	initAbilityLibraryView();
    }

    private void initAbilityLibraryView() {
	Library<String, Ability> generalAbilities = idl.getAbilityLibraries().get("Générale");
	rootLayout.setCenter(FXUtil.abilityLibraryView(generalAbilities).getX());

    }

    private void initRootLayout() {
	Pair<BorderPane, RootLayoutController> rootLayoutPair = FXUtil.rootLayout();
	this.rootLayout = rootLayoutPair.getX();
	// TODO do stuff to the controller
	Scene scene = new Scene(rootLayout);
	primaryStage.setScene(scene);
	primaryStage.show();
    }

    public static void main(String[] args) {
	launch(args);
    }

}
