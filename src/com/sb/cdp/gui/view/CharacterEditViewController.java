package com.sb.cdp.gui.view;

import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import com.sb.cdp.CharacterType;
import com.sb.cdp.CharacterType.Classification;
import com.sb.cdp.LawAlignment;
import com.sb.cdp.MoralAlignment;
import com.sb.cdp.PlayerCharacter;
import com.sb.cdp.RPG;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.util.Callback;

// TESTME
public class CharacterEditViewController {

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
    private ListView abilitiesList;

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
	racePane.getChildren().add(races.size(), newChoice); // Do not switch this line and the next. If you do, add "- 1" to "races.size()" argument. This keeps the new ChoiceBox before the "+" button.
	races.add(newChoice);
    }

    @FXML
    private void addClassChoice() {
	ChoiceBox newChoice = new ChoiceBox<>();
	setClassChoices(newChoice);
	classPane.getChildren().add(classes.size(), newChoice); // Do not switch this line and the next. If you do, add "- 1" to "classes.size()" argument. This keeps the new ChoiceBox before the "+" button.
	classes.add(newChoice);
    }

    @FXML
    private void addGodChoice() {
	ChoiceBox newChoice = new ChoiceBox<>();
	setGodChoices(newChoice);
	classPane.getChildren().add(gods.size(), newChoice); // Do not switch this line and the next. If you do, add "- 1" to "gods.size()" argument. This keeps the new ChoiceBox before the "+" button.
	gods.add(newChoice);
    }
    
    private void initializeOptions() {
	if (rpg == null)
	    throw new IllegalStateException("Cannot initialize the options while the rpg is null.");

	setRaceChoices(firstRaceChoice);
	setClassChoices(firstClassChoice);
	setGodChoices(firstGodChoice);
	lawAlignment.setItems(FXCollections.observableArrayList(LawAlignment.values()));
	moralAlignment.setItems(FXCollections.observableArrayList(MoralAlignment.values()));
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

	} else {
	    name.setText("");
	    // TODO
	}
    }

    public PlayerCharacter getPlayerCharacter() {
	return pc;
    }
}
