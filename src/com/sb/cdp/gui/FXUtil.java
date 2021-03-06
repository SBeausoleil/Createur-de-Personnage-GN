package com.sb.cdp.gui;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import com.sb.cdp.PlayerCharacter;
import com.sb.cdp.RPG;
import com.sb.cdp.User;
import com.sb.cdp.ability.Ability;
import com.sb.cdp.gui.view.AbilityLibraryViewController;
import com.sb.cdp.gui.view.AbilityListEditView;
import com.sb.cdp.gui.view.AbilityListEditViewController;
import com.sb.cdp.gui.view.AbilityView;
import com.sb.cdp.gui.view.AbilityViewController;
import com.sb.cdp.gui.view.CharacterEditViewController;
import com.sb.cdp.gui.view.ExtendedAbilityLibraryViewController;
import com.sb.cdp.gui.view.RootLayoutController;
import com.sb.cdp.gui.view.TitledListView;
import com.sb.cdp.gui.view.UserCharacterViewController;
import com.sb.cdp.gui.view.UserEditViewController;
import com.sb.cdp.gui.view.UserViewController;
import com.sb.util.ConcretePair;
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

    public static ConcretePair<TitledListView<AbilityView>, AbilityLibraryViewController> abilityLibraryView(
	    Pair<String, Collection<Ability>> library) {

	ConcretePair<TitledListView<AbilityView>, AbilityLibraryViewController> pair = new ConcretePair<>();
	pair.setX(new TitledListView());
	pair.setY(new AbilityLibraryViewController(pair.getX()));
	pair.getY().setAbilities(library);

	return pair;
    }


    public static ConcretePair<AbilityListEditView, AbilityListEditViewController> abilityListEditView(RPG rpg,
	    User user,
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

	return new ConcretePair<>(view, controller);
    }

    public static ConcretePair<AbilityView, AbilityViewController> abilityView(Ability ability) {
	ConcretePair<AbilityView, AbilityViewController> pair = new ConcretePair<>();
	AbilityView view = new AbilityView();
	AbilityViewController controller = new AbilityViewController(view);
	controller.setAbility(ability);
	pair.setX(view);
	pair.setY(controller);
	return pair;

    }

    public static ConcretePair<AnchorPane, CharacterEditViewController> characterEditView(RPG rpg, PlayerCharacter pc,
	    User user) {
	try {
	    ConcretePair<AnchorPane, CharacterEditViewController> pair = new ConcretePair<>();
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

    public static ConcretePair<VBox, ExtendedAbilityLibraryViewController> extendedAbilityLibraryView(
	    Collection<Pair<String, Collection<Ability>>> abilities) {

	try {
	    ConcretePair<VBox, ExtendedAbilityLibraryViewController> pair = new ConcretePair<>();
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(FXUtil.class.getResource("view/ExtendedAbilityLibraryView.fxml"));
	    pair.setX(loader.load());
	    pair.setY(loader.getController());
	    pair.getY().setLibraries(abilities);

	    return pair;
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
    }

    public static ConcretePair<BorderPane, RootLayoutController> rootLayout() {
	try {
	    ConcretePair<BorderPane, RootLayoutController> pair = new ConcretePair<>();
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(FXUtil.class.getResource("view/RootLayout.fxml"));
	    pair.setX((BorderPane) loader.load());
	    pair.setY(loader.getController());
	    return pair;
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
    }

    public static ConcretePair<AnchorPane, UserCharacterViewController> userCharacterView(
	    DraftModel<PlayerCharacter> cm, User user) {
	try {
	    ConcretePair<AnchorPane, UserCharacterViewController> pair = new ConcretePair<>();
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

    public static ConcretePair<AnchorPane, UserEditViewController> userEditView(User user) {
	try {
	    ConcretePair<AnchorPane, UserEditViewController> pair = new ConcretePair<>();
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(FXUtil.class.getResource("view/UserEditView.fxml"));
	    pair.setX((AnchorPane) loader.load());
	    pair.setY(loader.getController());
	    return pair;
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
    }

    public static ConcretePair<VBox, UserViewController> userView(User user) {
	try {
	    ConcretePair<VBox, UserViewController> pair = new ConcretePair<>();
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

    private FXUtil() {}
}
