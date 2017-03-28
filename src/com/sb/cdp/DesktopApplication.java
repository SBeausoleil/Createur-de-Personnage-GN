package com.sb.cdp;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.sb.cdp.CharacterType.Classification;
import com.sb.cdp.ability.Ability;
import com.sb.cdp.gui.FXUtil;
import com.sb.cdp.idl.Initializer;
import com.sb.util.DateUtil;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class DesktopApplication extends Application {

    private static DesktopApplication app;

    public static DesktopApplication get() {
	return app;
    }

    public static void main(String[] args) {
	launch(args);
    }

    private Stage primaryStage;
    private BorderPane rootLayout;

    private User user;

    private RPG rpg;

    // Called by the superclass
    public DesktopApplication() {
	app = this;

	rpg = null;
	try {
	    rpg = Initializer.initialize();
	} catch (FileNotFoundException e) {
	    System.err.println("One of the files needed for the initialization of the RPG has not been found.");
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	if (rpg == null)
	    System.exit(1);
	user = testUser();

    }

    /**
     * Returns the primaryStage.
     * 
     * @return the primaryStage
     */
    public Stage getPrimaryStage() {
	return primaryStage;
    }

    /**
     * Returns the rootLayout.
     * 
     * @return the rootLayout
     */
    public BorderPane getRootLayout() {
	return rootLayout;
    }

    /**
     * Returns the rpg.
     * 
     * @return the rpg
     */
    public RPG getRpg() {
	return rpg;
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
     * Sets the value of primaryStage to that of the parameter.
     * 
     * @param primaryStage
     *            the primaryStage to set
     */
    public void setPrimaryStage(Stage primaryStage) {
	this.primaryStage = primaryStage;
    }

    /**
     * Sets the value of rootLayout to that of the parameter.
     * 
     * @param rootLayout
     *            the rootLayout to set
     */
    public void setRootLayout(BorderPane rootLayout) {
	this.rootLayout = rootLayout;
    }

    /**
     * Sets the value of rpg to that of the parameter.
     * 
     * @param rpg
     *            the rpg to set
     */
    public void setRpg(RPG idl) {
	this.rpg = idl;
    }

    /**
     * Sets the value of user to that of the parameter.
     * 
     * @param user
     *            the user to set
     */
    public void setUser(User user) {
	this.user = user;
    }

    // Called by the superclass Application after the constructor
    @Override
    public void start(Stage primaryStage) throws Exception {
	this.primaryStage = primaryStage;
	primaryStage.setTitle("Arcane");

	this.rootLayout = FXUtil.rootLayout().getX();

	Scene scene = new Scene(rootLayout);
	primaryStage.setScene(scene);
	primaryStage.show();
    }

    private PlayerCharacter testCharacter1() {
	PlayerCharacter pc = new PlayerCharacter("Milo");
	pc.setLawAlignment(LawAlignment.LAWFUL);
	pc.setMoralALignment(MoralAlignment.NEUTRAL);
	pc.setXp(13);

	pc.getCharacterTypes().add(rpg.getCharacterTypes().get("Aventurier", Classification.CLASS));
	pc.getCharacterTypes().add(rpg.getCharacterTypes().get("Guerrier", Classification.CLASS));
	pc.getCharacterTypes().add(rpg.getCharacterTypes().get("Elf", Classification.RACE));
	pc.getCharacterTypes().add(rpg.getCharacterTypes().get("Humain", Classification.RACE));

	Library<String, Ability> lib = rpg.getAbilityLibraries().get(Initializer.ABILITY_LIBRARY);
	pc.getAbilities().add(lib.get("Arme courte"));
	pc.getAbilities().add(lib.get("Armures"));
	pc.getAbilities().add(lib.get("Esquive"));

	pc.getGods().add(rpg.getGods().get("Terra"));
	return pc;
    }

    private PlayerCharacter testCharacter2() {
	PlayerCharacter pc = new PlayerCharacter("Aela Stormborn");
	pc.setLawAlignment(LawAlignment.CHAOTIC);
	pc.setMoralALignment(MoralAlignment.EVIL);
	pc.setXp(55);

	pc.getCharacterTypes().add(rpg.getCharacterTypes().get("Guerrier", Classification.CLASS));
	pc.getCharacterTypes().add(rpg.getCharacterTypes().get("Barbare", Classification.RACE));

	Library<String, Ability> lib = rpg.getAbilityLibraries().get(Initializer.ABILITY_LIBRARY);
	pc.getAbilities().add(lib.get("Arme courte"));
	pc.getAbilities().add(lib.get("Armures"));
	pc.getAbilities().add(lib.get("Esquive"));

	pc.getSpecialAbilities().add(lib.get("Alchimie 1"));
	pc.getSpecialAbilities().add(lib.get("Alchimie 2"));
	pc.getSpecialAbilities().add(lib.get("Alchimie 3"));

	return pc;
    }

    private User testUser() {
	User user = new User("Samuel", "Beausoleil", DateUtil.parse("19/01/1996"), "samuel.beausoleil@hotmail.com");
	user.addAsConfirmed(testCharacter1());
	user.addAsPending(testCharacter2());
	return user;
    }
}
