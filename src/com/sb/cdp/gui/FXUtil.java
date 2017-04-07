package com.sb.cdp.gui;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import com.sb.cdp.Library;
import com.sb.cdp.PlayerCharacter;
import com.sb.cdp.RPG;
import com.sb.cdp.User;
import com.sb.cdp.ability.Ability;
import com.sb.cdp.gui.view.AbilityLibraryViewController;
import com.sb.cdp.gui.view.AbilityListEditView;
import com.sb.cdp.gui.view.AbilityListEditViewController;
import com.sb.cdp.gui.view.AbilityViewController;
import com.sb.cdp.gui.view.CharacterEditViewController;
import com.sb.cdp.gui.view.ExtendedAbilityLibraryViewController;
import com.sb.cdp.gui.view.RootLayoutController;
import com.sb.cdp.gui.view.UserCharacterViewController;
import com.sb.cdp.gui.view.UserEditViewController;
import com.sb.cdp.gui.view.UserViewController;
import com.sb.util.DraftModel;
import com.sb.util.Getter;
import com.sb.util.Pair;
import com.sb.util.Setter;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * A helper class aiding the loading of views and their controller.
 * 
 * @author Samuel Beausoleil
 */
public final class FXUtil {
    private FXUtil() {}

    public static Pair<AnchorPane, UserEditViewController> userEditView(User user) {
	try {
	    Pair<AnchorPane, UserEditViewController> pair = new Pair<>();
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(FXUtil.class.getResource("view/UserEditView.fxml"));
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
	    loader.setLocation(FXUtil.class.getResource("view/RootLayout.fxml"));
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
	    loader.setLocation(FXUtil.class.getResource("view/AbilityLibraryView.fxml"));
	    pair.setX((AnchorPane) loader.load());
	    pair.setY(loader.getController());
	    pair.getY().setAbilities(library);
	    return pair;
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
    }

    public static Pair<AnchorPane, CharacterEditViewController> characterEditView(RPG rpg, PlayerCharacter pc,
	    User user) {
	try {
	    Pair<AnchorPane, CharacterEditViewController> pair = new Pair<>();
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(FXUtil.class.getResource("view/CharacterEditView.fxml"));
	    pair.setX(loader.load());
	    CharacterEditViewController controller = loader.getController();
	    pair.setY(controller);
	    controller.setRpg(rpg);
	    controller.setPlayerCharacter(pc);
	    controller.setUser(user);
	    return pair;
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
    }

    public static Pair<VBox, UserViewController> userView(User user) {
	try {
	    Pair<VBox, UserViewController> pair = new Pair<>();
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(FXUtil.class.getResource("view/UserView.fxml"));
	    pair.setX(loader.load());
	    pair.setY(loader.getController());
	    pair.getY().setUser(user);
	    return pair;
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
    }

    public static Pair<AnchorPane, UserCharacterViewController> userCharacterView(
	    DraftModel<PlayerCharacter> cm, User user) {
	try {
	    Pair<AnchorPane, UserCharacterViewController> pair = new Pair<>();
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(FXUtil.class.getResource("view/UserCharacterView.fxml"));
	    pair.setX(loader.load());
	    UserCharacterViewController controller = loader.getController();
	    controller.setUserCharacter(cm);
	    controller.setUser(user);
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
	    loader.setLocation(FXUtil.class.getResource("view/AbilityView.fxml"));
	    pair.setX(loader.load());
	    AbilityViewController controller = loader.getController();
	    controller.setAbility(ability);
	    pair.setY(controller);
	    return pair;
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
    }

    public static Pair<AnchorPane, ExtendedAbilityLibraryViewController> extendedAbilityLibraryView(
	    Collection<Library<String, Ability>> collection) {
	try {
	    Pair<AnchorPane, ExtendedAbilityLibraryViewController> pair = new Pair<>();
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(FXUtil.class.getResource("view/ExtendedAbilityLibraryView.fxml"));
	    pair.setX(loader.load());
	    pair.setY(loader.getController());
	    pair.getY().setLibraries(collection);
	    return pair;
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
    }

    public static Pair<AbilityListEditView, AbilityListEditViewController> abilityListEditView(RPG rpg, User user,
	    PlayerCharacter pc, String pcSectionTitle, Getter<PlayerCharacter, List<Ability>> getter,
	    Setter<PlayerCharacter, List<Ability>> setter) {

	AbilityListEditView view = new AbilityListEditView();
	AbilityListEditViewController controller = new AbilityListEditViewController(view);

	controller.extractPublicAbilities(rpg);
	controller.setUser(user);
	controller.setPcSectionTitle(pcSectionTitle);
	controller.setGetter(getter);
	controller.setSetter(setter);
	controller.setPc(pc);

	return new Pair<>(view, controller);
    }
}
