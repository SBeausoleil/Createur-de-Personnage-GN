package com.sb.cdp.gui;

import java.io.IOException;

import com.sb.cdp.DesktopApplication;
import com.sb.cdp.Library;
import com.sb.cdp.PlayerCharacter;
import com.sb.cdp.RPG;
import com.sb.cdp.User;
import com.sb.cdp.ability.Ability;
import com.sb.cdp.gui.view.AbilityLibraryViewController;
import com.sb.cdp.gui.view.AbilityViewController;
import com.sb.cdp.gui.view.CharacterEditViewController;
import com.sb.cdp.gui.view.RootLayoutController;
import com.sb.cdp.gui.view.UserCharacterViewController;
import com.sb.cdp.gui.view.UserEditViewController;
import com.sb.cdp.gui.view.UserViewController;
import com.sb.util.ConfirmationModel;
import com.sb.util.Pair;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * A helper class aiding the loading of views and their controller.
 * 
 * @author Samuel Beausoleil
 *
 */
public final class FXUtil {
    private FXUtil() {}

    public static Pair<AnchorPane, UserEditViewController> userEditView(User user) {
	try {
	    Pair<AnchorPane, UserEditViewController> pair = new Pair<>();
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(DesktopApplication.class.getResource("gui/view/UserEditView.fxml"));
	    pair.setX((AnchorPane) loader.load());
	    pair.setY(loader.getController());
	    return pair;
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
    }

    public static Pair<BorderPane, RootLayoutController> rootLayout() {
	try {
	    Pair<BorderPane, RootLayoutController> pair = new Pair<>();
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(DesktopApplication.class.getResource("gui/view/RootLayout.fxml"));
	    pair.setX((BorderPane) loader.load());
	    pair.setY(loader.getController());
	    return pair;
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
    }

    public static Pair<AnchorPane, AbilityLibraryViewController> abilityLibraryView(Library<?, Ability> library) {
	try {
	    Pair<AnchorPane, AbilityLibraryViewController> pair = new Pair<>();
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(DesktopApplication.class.getResource("gui/view/AbilityLibraryView.fxml"));
	    pair.setX((AnchorPane) loader.load());
	    pair.setY(loader.getController());
	    pair.getY().setAbilities(library);
	    return pair;
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
    }

    public static Pair<AnchorPane, CharacterEditViewController> characterEditView(RPG rpg, PlayerCharacter pc) {
	try {
	    Pair<AnchorPane, CharacterEditViewController> pair = new Pair<>();
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(DesktopApplication.class.getResource("gui/view/CharacterEditView.fxml"));
	    pair.setX(loader.load());
	    CharacterEditViewController controller = loader.getController();
	    pair.setY(controller);
	    controller.setRpg(rpg);
	    controller.setPlayerCharacter(pc);
	    return pair;
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
    }

    public static Pair<VBox, UserViewController> userView(User user) {
	try {
	    Pair<VBox, UserViewController> pair = new Pair<>();
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(DesktopApplication.class.getResource("gui/view/UserView.fxml"));
	    pair.setX(loader.load());
	    pair.setY(loader.getController());
	    pair.getY().setUser(user);
	    return pair;
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
    }

    public static Pair<AnchorPane, UserCharacterViewController> userCharacterView(
	    ConfirmationModel<PlayerCharacter> cm) {
	try {
	    Pair<AnchorPane, UserCharacterViewController> pair = new Pair<>();
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(DesktopApplication.class.getResource("gui/view/UserCharacterView.fxml"));
	    pair.setX(loader.load());
	    UserCharacterViewController controller = loader.getController();
	    controller.setUserCharacter(cm);
	    pair.setY(controller);
	    return pair;
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
    }

    public static Pair<AnchorPane, AbilityViewController> abilityView(Ability ability) {
	try {
	    Pair<AnchorPane, AbilityViewController> pair = new Pair<>();
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(DesktopApplication.class.getResource("gui/view/AbilityView.fxml"));
	    pair.setX(loader.load());
	    AbilityViewController controller = loader.getController();
	    controller.setAbility(ability);
	    pair.setY(controller);
	    return pair;
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
    }
}
