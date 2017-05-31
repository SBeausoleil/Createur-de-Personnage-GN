package com.sb.cdp.gui.view;

import java.util.Collection;
import java.util.LinkedList;

import com.sb.cdp.Library;
import com.sb.cdp.ability.Ability;
import com.sb.cdp.gui.FXUtil;
import com.sb.util.ConcretePair;
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
    AbilityLibraryViewController characterAbilities;
    //private AnchorPane extendedAbilityLibraryView;
    ExtendedAbilityLibraryViewController availableLibraries;

    ButtonBar buttons;
    Button confirm;
    Button cancel;

    public AbilityListEditView() {
	nAbilityPoints = new Label("Points disponibles: ");
	getChildren().add(nAbilityPoints);
	horizontal = new HBox();
	getChildren().add(horizontal);

	ConcretePair<AnchorPane, AbilityLibraryViewController> abilities = FXUtil.abilityLibraryView(new Library("", Ability.class));
	horizontal.getChildren().add(abilities.getX());
	characterAbilities = abilities.getY();

	LinkedList<Pair<String, Collection<Ability>>> abilityLibs = new LinkedList();
	ConcretePair<VBox, ExtendedAbilityLibraryViewController> extended = FXUtil.extendedAbilityLibraryView(abilityLibs);
	horizontal.getChildren().add(extended.getX());
	availableLibraries = extended.getY();

	buttons = new ButtonBar();
	confirm = new Button("Confirmer");
	cancel = new Button("Annuller");
	buttons.getButtons().add(confirm);
	buttons.getButtons().add(cancel);
	getChildren().add(buttons);
	
    }
}
