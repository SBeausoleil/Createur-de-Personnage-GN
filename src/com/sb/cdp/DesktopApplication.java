package com.sb.cdp;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.sb.cdp.ability.Ability;
import com.sb.cdp.gui.view.AbilityLibraryViewController;
import com.sb.cdp.gui.view.UserEditViewController;
import com.sb.cdp.idl.Initialize;

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
	    idl = Initialize.initialize();
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
	//initUserEditPage();
	initAbilityLibraryView();
    }

    private void initAbilityLibraryView() {
	try {
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(DesktopApplication.class.getResource("gui/view/AbilityLibraryView.fxml"));
	    AnchorPane abilityLibraryView = (AnchorPane) loader.load();

	    AbilityLibraryViewController controller = loader.getController();
	    System.out.println("Controller: " + controller);
	    //System.out.println(idl);
	    //System.out.println(idl.getAbilityLibraries());
	    Library<String, Ability> generalAbilities = idl.getAbilityLibraries().get("Générale");
	    //System.out.println(generalAbilities);
	    controller.setAbilities(generalAbilities); 
	    rootLayout.setCenter(abilityLibraryView);
	} catch (IOException e) {
	    System.err.println("UserEditView.fxml is missing.");
	    throw new RuntimeException(e);
	}
    }

    private void initUserEditPage() {
	try {
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(DesktopApplication.class.getResource("gui/view/UserEditView.fxml"));
	    AnchorPane userEditPage = (AnchorPane) loader.load();

	    UserEditViewController controller = loader.getController();
	    controller.setUser(user);
	    rootLayout.setCenter(userEditPage);
	} catch (IOException e) {
	    System.err.println("UserEditView.fxml is missing.");
	    throw new RuntimeException(e);
	}

    }

    private void initRootLayout() {
	try {
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(DesktopApplication.class.getResource("gui/view/RootLayout.fxml"));
	    rootLayout = (BorderPane) loader.load();

	    Scene scene = new Scene(rootLayout);
	    primaryStage.setScene(scene);
	    primaryStage.show();
	} catch (IOException e) {
	    System.err.println("Critical error: RootLayout.fxml is missing");
	}
    }

    public static void main(String[] args) {
	launch(args);
    }

}
