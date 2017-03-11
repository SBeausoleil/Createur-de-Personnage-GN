package com.sb.cdp.gui;

import java.io.IOException;

import com.sb.cdp.DesktopApplication;
import com.sb.cdp.Library;
import com.sb.cdp.User;
import com.sb.cdp.ability.Ability;
import com.sb.cdp.gui.view.AbilityLibraryViewController;
import com.sb.cdp.gui.view.RootLayoutController;
import com.sb.cdp.gui.view.UserEditViewController;
import com.sb.util.Pair;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public final class FXUtil {
    private FXUtil() {}

    public static Pair<AnchorPane, UserEditViewController> userEditView(User user) {
	try {
	    Pair<AnchorPane, UserEditViewController> pair = new Pair();
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
	    Pair<BorderPane, RootLayoutController> pair = new Pair();
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
}
