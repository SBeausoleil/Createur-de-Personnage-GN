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
    private ChoiceBox<CharacterType> firstRaceChoice;
    /**
     * Accomodates all the race choice boxes.
     */
    private LinkedList<ChoiceBox<CharacterType>> races;
    @FXML
    private Button addRace;
    @FXML
    private Button removeRace;
    @FXML
    private FlowPane racePane;
    @FXML
    private ChoiceBox<CharacterType> firstClassChoice;
    /**
     * Accomodates all the class choice boxes.
     */
    private LinkedList<ChoiceBox<CharacterType>> classes;
    @FXML
    private Button addClass;
    @FXML
    private Button removeClass;
    @FXML
    private FlowPane classPane;
    @FXML
    private ChoiceBox<LawAlignment> lawAlignment;
    @FXML
    private ChoiceBox<MoralAlignment> moralAlignment;
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

    // NOTES TAB //
    @FXML
    private TextArea notes;

    // DATA OBJECTS //
    /**
     * The Player Character center to this view.
     */
    private PlayerCharacter pc;
    /**
     * Modifications made to the UI will be applied to the tmp character.
     */
    private PlayerCharacter tmp;
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
	if (pc == null)
	    throw new IllegalArgumentException("pc may not be null");
	this.pc = pc;
	this.tmp = this.pc.clone();
	initializePlayerCharacterInfo();
    }

    private void initializePlayerCharacterInfo() {
	name.setText(tmp.getName());

	{ // set the race(s) and class(es)
	    int nRace = 0;
	    int nClass = 0;
	    for (CharacterType type : tmp.getCharacterTypes()) {
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
	lawAlignment.setValue(tmp.getLawAlignment());
	moralAlignment.setValue(tmp.getMoralALignment());

	// XP
	xp.setText(Integer.toString(tmp.getXp()));

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
	statsTable.setItems(FXCollections.observableArrayList(tmp.getStats().entrySet()));

	// Abilities
	Library<String, Ability> abilities = new Library<>("Abilités", Ability.class);
	Library<String, Ability> specialAbilities = new Library<>("Abilités spéciales", Ability.class);

	for (Ability ability : tmp.getAbilities())
	    abilities.put(ability.getName(), ability);
	for (Ability ability : tmp.getSpecialAbilities())
	    specialAbilities.put(ability.getName(), ability);

	abilitiesBox.getChildren().add(FXUtil.abilityLibraryView(abilities).getX());
	abilitiesBox.getChildren().add(FXUtil.abilityLibraryView(specialAbilities).getX());

	// Magic
	int nGods = 0;
	for (God god : tmp.getGods()) {
	    if (nGods != 0)
		addGodChoice();
	    gods.getLast().setValue(god);
	    nGods++;
	}

	// Notes
	notes.setText(tmp.getNote());

    }

    public PlayerCharacter getPlayerCharacter() {
	return pc;
    }

    @Override
    public void update() {
	initializeOptions();
	initializePlayerCharacterInfo();
    }

    /**
     * Applies UI info to the pc.
     * Checks first for validity of entered data.
     */
    @FXML
    private void confirm() {
	try {
	    tmp.setName(name.getText());
	    tmp.getCharacterTypes().clear();
	    for (ChoiceBox<CharacterType> ct : classes)
		tmp.getCharacterTypes().add(ct.getValue());
	    for (ChoiceBox<CharacterType> ct : races)
		tmp.getCharacterTypes().add(ct.getValue());
	    tmp.setLawAlignment(lawAlignment.getValue());
	    tmp.setMoralALignment(moralAlignment.getValue());
	    tmp.setXp(Integer.parseInt(xp.getText()));
	    tmp.setnAbilityPoints(Integer.parseInt(abilityPoints.getText()));
	    // Ability tabs reflects in real time on the tmp.
	    // Magic tab
	    tmp.getGods().clear();
	    for (ChoiceBox<God> god : gods)
		tmp.getGods().add(god.getValue());
	    // Rest of magic tab is reflected in real time on the tmp.
	    // Notes tab
	    tmp.setNote(notes.getText());
	    
	    tmp.clone(pc);
	} catch (Throwable e) {
	    
	}
    }
}
