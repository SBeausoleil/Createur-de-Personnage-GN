package com.sb.cdp.gui.view;

import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import com.sb.cdp.CharacterType;
import com.sb.cdp.CharacterType.Classification;
import com.sb.cdp.LawAlignment;
import com.sb.cdp.Library;
import com.sb.cdp.MoralAlignment;
import com.sb.cdp.PlayerCharacter;
import com.sb.cdp.RPG;
import com.sb.cdp.ability.Ability;
import com.sb.cdp.gui.FXUtil;
import com.sb.cdp.magic.God;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

// TESTME
public class CharacterEditViewController implements Updateable {

    // BUTTONS MENU //
    @FXML
    private Button confirm;
    @FXML
    private Button delete;
    @FXML
    private Button cancel;

    // GENERAL TAB //
    @FXML
    private TextField name;
    @FXML
    private ChoiceBox firstRaceChoice;
    /**
     * Accomodates all the race choice boxes.
     */
    private LinkedList<ChoiceBox> races;
    @FXML
    private Button addRace;
    @FXML
    private Button removeRace;
    @FXML
    private FlowPane racePane;
    @FXML
    private ChoiceBox firstClassChoice;
    /**
     * Accomodates all the class choice boxes.
     */
    private LinkedList<ChoiceBox> classes;
    @FXML
    private Button addClass;
    @FXML
    private Button removeClass;
    @FXML
    private FlowPane classPane;
    @FXML
    private ChoiceBox lawAlignment;
    @FXML
    private ChoiceBox moralAlignment;
    @FXML
    private TextField xp;
    @FXML
    private Button addXp;
    @FXML
    private TextField abilityPoints;
    @FXML
    private TextArea description;

    // ABILITIES TAB //
    @FXML
    private Button modifyAbilities;
    @FXML
    private VBox abilitiesBox;

    // STATS TAB //
    @FXML
    private TableView statsTable;
    @FXML
    private TableColumn properties;
    @FXML
    private TableColumn values;

    // MAGIC TAB //
    @FXML
    private ChoiceBox firstGodChoice;
    /**
     * Accomodates all the god choice boxes.
     */
    private LinkedList<ChoiceBox> gods;
    @FXML
    private Button addGod;
    @FXML
    private Button removeGod;
    @FXML
    private FlowPane godPane;
    @FXML
    private Button modifyMagic;
    @FXML
    private ListView magicList;

    // DATA OBJECTS //
    /**
     * The Player Character center to this view.
     */
    private PlayerCharacter pc;
    /**
     * The RPG used to populate the choice boxes.
     */
    private RPG rpg;

    public CharacterEditViewController() {}

    @FXML
    private void initialize() {
	modifyAbilities.setPrefWidth(Double.MAX_VALUE);
	modifyMagic.setPrefWidth(Double.MAX_VALUE);

	races = new LinkedList<>();
	races.add(firstRaceChoice);
	classes = new LinkedList<>();
	classes.add(firstClassChoice);
	gods = new LinkedList<>();
	gods.add(firstGodChoice);
	
	lawAlignment.setItems(FXCollections.observableArrayList(LawAlignment.values()));
	moralAlignment.setItems(FXCollections.observableArrayList(MoralAlignment.values()));
    }

    public void setRpg(RPG rpg) {
	this.rpg = rpg;
	initializeOptions();
    }

    public RPG getRpg() {
	return rpg;
    }

    @FXML
    private void addRaceChoice() {
	ChoiceBox newChoice = new ChoiceBox<>();
	setRaceChoices(newChoice);
	addToPane(newChoice, racePane);
	races.add(newChoice);
    }

    @FXML
    private void removeRaceChoice() {
	if (!races.isEmpty()) {
	    races.removeLast();
	    removeFromPane(racePane);
	}
    }

    @FXML
    private void addClassChoice() {
	ChoiceBox newChoice = new ChoiceBox<>();
	setClassChoices(newChoice);
	addToPane(newChoice, classPane);
	classes.add(newChoice);
    }

    @FXML
    private void removeClassChoice() {
	if (!classes.isEmpty()) {
	    classes.removeLast();
	    removeFromPane(classPane);
	}
    }

    @FXML
    private void addGodChoice() {
	ChoiceBox newChoice = new ChoiceBox<>();
	setGodChoices(newChoice);
	addToPane(newChoice, godPane);
	gods.add(newChoice);
    }

    @FXML
    private void removeGodChoice() {
	if (!gods.isEmpty()) {
	    gods.removeLast();
	    removeFromPane(godPane);
	}
    }

    private void addToPane(Node node, FlowPane pane) {
	// Do not switch this line and the next. If you do, add "- 1" to "coll.size()" argument. This keeps the new ChoiceBox before the "+" and "-".
	pane.getChildren().add(pane.getChildren().size() - 2, node);
    }

    private void removeFromPane(FlowPane pane) {
	pane.getChildren().remove(pane.getChildren().size() - 3);
    }

    private void initializeOptions() {
	if (rpg == null)
	    throw new IllegalStateException("Cannot initialize the options while the rpg is null.");

	for (ChoiceBox cb : races)
	    setRaceChoices(cb);
	for (ChoiceBox cb : classes)
	    setClassChoices(cb);
	for (ChoiceBox cb : gods)
	    setGodChoices(cb);
    }

    private void setClassChoices(ChoiceBox choiceBox) {
	choiceBox.setItems(FXCollections.observableArrayList(rpg.getCharacterTypes().get(Classification.CLASS)));
    }

    private void setRaceChoices(ChoiceBox choiceBox) {
	choiceBox.setItems(FXCollections.observableArrayList(rpg.getCharacterTypes().get(Classification.RACE)));
    }

    private void setGodChoices(ChoiceBox choiceBox) {
	choiceBox.setItems(FXCollections.observableArrayList(rpg.getGods().values()));
    }

    public void setPlayerCharacter(PlayerCharacter pc) {
	this.pc = pc;
	initializePlayerCharacterInfo();
    }

    private void initializePlayerCharacterInfo() {
	// TODO
	if (pc != null) {
	    name.setText(pc.getName());

	    { // set the race(s) and class(es)
		int nRace = 0;
		int nClass = 0;
		for (CharacterType type : pc.getCharacterTypes()) {
		    switch (type.getClassification()) {
		    case RACE:
			if (nRace != 0)
			    addRaceChoice();
			races.getLast().setValue(type);
			nRace++;
			break;
		    case CLASS:
			if (nClass != 0)
			    addClassChoice();
			classes.getLast().setValue(type);
			nClass++;
			break;
		    }
		}
	    }
	    // Set the alignment
	    lawAlignment.setValue(pc.getLawAlignment());
	    moralAlignment.setValue(pc.getMoralALignment());

	    // XP
	    xp.setText(Integer.toString(pc.getXp()));

	    // Stats
	    properties.setCellValueFactory(
		    new Callback<TableColumn.CellDataFeatures<Map.Entry<String, Integer>, String>, SimpleStringProperty>() {

			@Override
			public SimpleStringProperty call(CellDataFeatures<Entry<String, Integer>, String> cell) {
			    return new SimpleStringProperty(cell.getValue().getKey());
			}

		    });
	    values.setCellValueFactory(
		    new Callback<TableColumn.CellDataFeatures<Map.Entry<String, Integer>, String>, SimpleStringProperty>() {

			@Override
			public SimpleStringProperty call(CellDataFeatures<Entry<String, Integer>, String> cell) {
			    return new SimpleStringProperty(cell.getValue().getValue().toString());
			}

		    });
	    statsTable.setItems(FXCollections.observableArrayList(pc.getStats().entrySet()));

	    // Abilities
	    Library<String, Ability> abilities = new Library<>("Abilités", Ability.class);
	    Library<String, Ability> specialAbilities = new Library<>("Abilités spéciales", Ability.class);

	    for (Ability ability : pc.getAbilities())
		abilities.put(ability.getName(), ability);
	    for (Ability ability : pc.getSpecialAbilities())
		specialAbilities.put(ability.getName(), ability);

	    abilitiesBox.getChildren().add(FXUtil.abilityLibraryView(abilities).getX());
	    abilitiesBox.getChildren().add(FXUtil.abilityLibraryView(specialAbilities).getX());

	    // Magic
	    int nGods = 0;
	    for (God god : pc.getGods()) {
		if (nGods != 0)
		    addGodChoice();
		gods.getLast().setValue(god);
		nGods++;
	    }

	} else {
	    name.setText("");
	    // TODO
	}
    }

    public PlayerCharacter getPlayerCharacter() {
	return pc;
    }

    @Override
    public void update() {
	initializeOptions();
	initializePlayerCharacterInfo();
    }
}
