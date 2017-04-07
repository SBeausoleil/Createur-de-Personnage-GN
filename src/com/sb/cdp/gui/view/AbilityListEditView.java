package com.sb.cdp.gui.view;

import java.util.LinkedList;

import com.sb.cdp.Library;
import com.sb.cdp.ability.Ability;
import com.sb.cdp.gui.FXUtil;
import com.sb.util.Pair;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AbilityListEditView extends VBox {
    Label nAbilityPoints;

    HBox horizontal;
    //private AnchorPane abilities;
    AbilityLibraryViewController abilitiesController;
    //private AnchorPane extendedAbilityLibraryView;
    ExtendedAbilityLibraryViewController librariesController;

    ButtonBar buttons;
    Button confirm;
    Button cancel;

    public AbilityListEditView() {
	nAbilityPoints = new Label("Points disponibles: ");
	getChildren().add(nAbilityPoints);
	horizontal = new HBox();
	getChildren().add(horizontal);

	Pair<AnchorPane, AbilityLibraryViewController> abilities = FXUtil.abilityLibraryView(new Library("", Ability.class));
	horizontal.getChildren().add(abilities.getX());

	LinkedList<Library<String, Ability>> tmp = new LinkedList();
	tmp.add(new Library("", Ability.class));
	Pair<AnchorPane, ExtendedAbilityLibraryViewController> extended = FXUtil.extendedAbilityLibraryView(tmp);
	horizontal.getChildren().add(extended.getX());
	librariesController = extended.getY();

	buttons = new ButtonBar();
	confirm = new Button("Confirmer");
	cancel = new Button("Annuller");
	buttons.getButtons().add(confirm);
	buttons.getButtons().add(cancel);
	getChildren().add(buttons);
    }
}
