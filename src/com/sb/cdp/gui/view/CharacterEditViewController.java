package com.sb.cdp.gui.view;

import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import com.sb.cdp.CharacterType;
import com.sb.cdp.CharacterType.Classification;
import com.sb.cdp.DesktopApplication;
import com.sb.cdp.LawAlignment;
import com.sb.cdp.Library;
import com.sb.cdp.MoralAlignment;
import com.sb.cdp.PlayerCharacter;
import com.sb.cdp.RPG;
import com.sb.cdp.User;
import com.sb.cdp.ability.Ability;
import com.sb.cdp.gui.Effects;
import com.sb.cdp.gui.FXUtil;
import com.sb.cdp.gui.InvalidFieldsException;
import com.sb.cdp.magic.God;
import com.sb.util.ConcretePair;
import com.sb.util.Regexs;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;

public class CharacterEditViewController implements Controller {
    // BUTTONS MENU //
    @FXML
    private ButtonBar buttonBar;
    @FXML
    private Button submit;
    @FXML
    private Button draft;

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
    private Label level;
    @FXML
    private TextField abilityPoints;

    @FXML
    private TextArea description;
    // ABILITIES TAB //
    @FXML
    private Button modifyAbilities;
    private Button modifySpecialAbilities;
    private ConcretePair<TitledListView<AbilityView>, AbilityLibraryViewController> normalAbilities;
    private ConcretePair<TitledListView<AbilityView>, AbilityLibraryViewController> specialAbilities;

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
    private User user;
    /**
     * Modifications made to the UI will be applied to the tmp character.
     */
    private PlayerCharacter tmp;

    /**
     * The RPG used to populate the choice boxes.
     */
    private RPG rpg;

    /**
     * A list of all the invalid fields.
     */
    private InvalidFieldsException invalidFields = null;

    public CharacterEditViewController() {}

    @FXML
    private void addClassChoice() {
	ChoiceBox newChoice = new ChoiceBox<>();
	setClassChoices(newChoice);
	addToPane(newChoice, classPane);
	classes.add(newChoice);
    }

    @FXML
    private void addGodChoice() {
	ChoiceBox newChoice = new ChoiceBox<>();
	setGodChoices(newChoice);
	addToPane(newChoice, godPane);
	gods.add(newChoice);
    }

    @FXML
    private void addRaceChoice() {
	ChoiceBox newChoice = new ChoiceBox<>();
	setRaceChoices(newChoice);
	addToPane(newChoice, racePane);
	races.add(newChoice);
    }

    private void addToPane(Node node, FlowPane pane) {
	pane.getChildren().add(pane.getChildren().size() - 2, node); // -2 to account for the control buttons.
    }

    /**
     * Applies UI info to the tmp.
     */
    private void applyToTmp() throws InvalidFieldsException {
	validateFields();

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
	tmp.setDescription(description.getText());
	// Ability tabs reflects in real time on the tmp.
	// Magic tab
	tmp.getGods().clear();
	for (ChoiceBox<God> god : gods)
	    tmp.getGods().add(god.getValue());
	// Rest of magic tab is reflected in real time on the tmp.
	// Notes tab
	tmp.setNote(notes.getText());
    }

    @FXML
    public void cancel() {
	DesktopApplication.get().getRootContext().precedent();
    }

    /**
     * Saves the data as a draft character.
     */
    @FXML
    public void draft() {
	clearInvalidFieldsEffects();
	try {
	    applyToTmp();
	    if (user != null)
		user.addAsDraft(tmp);
	    DesktopApplication.get().getRootContext().precedent(true);
	} catch (InvalidFieldsException e) {
	    handleInvalidFieldException(e);
	}
    }

    public PlayerCharacter getPlayerCharacter() {
	return pc;
    }

    public RPG getRpg() {
	return rpg;
    }

    private void handleInvalidFieldException(InvalidFieldsException e) {
	System.out.println("handleInvalidFieldException()");
	this.invalidFields = e;

	// Alert the user
	Alert alert = new Alert(AlertType.ERROR);
	alert.setTitle(String.format("Champ%1$s invalide%1$s", e.getInvalidFields().size() > 1 ? "s" : ""));
	alert.setHeaderText("Il y a "
		+ (e.getInvalidFields().size() > 1 ? "des erreurs dans certains champs."
			: "une erreur dans un champ."));

	StringBuilder sb = new StringBuilder();
	Label alertLabel = new Label();
	for (ConcretePair<String, ?> msg : e.getInvalidFields())
	    sb.append(msg.getX() + "\n");
	alertLabel.setText(sb.toString());
	alertLabel.setWrapText(true);
	alert.getDialogPane().setContent(alertLabel);
	alert.show();

	// Give a red glow to the invalid fields
	for (ConcretePair<?, TextField> field : e.getInvalidFields())
	    field.getY().setEffect(Effects.glow(Color.RED));
    }

    /**
     * Clears all effects placed on the invalid fields.
     */
    private void clearInvalidFieldsEffects() {
	if (invalidFields != null)
	    for (ConcretePair<?, TextField> field : invalidFields.getInvalidFields())
		field.getY().setEffect(null);
    }

    /**
     * Initializes GUI elements
     */
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

	description.setWrapText(true);
	notes.setWrapText(true);

	modifySpecialAbilities = new Button("Modifier Abilités Spcéciales");
	modifySpecialAbilities.setPrefWidth(Double.MAX_VALUE);
	modifySpecialAbilities.setOnAction((e) -> modifySpecialAbilities());

	normalAbilities = FXUtil.abilityLibraryView(null);
	specialAbilities = FXUtil.abilityLibraryView(null);
	abilitiesBox.getChildren().add(normalAbilities.getX());
	abilitiesBox.getChildren().add(modifySpecialAbilities);
	abilitiesBox.getChildren().add(specialAbilities.getX());

	initializeButtonBar();
    }

    private void initializeButtonBar() {
	buttonBar.getButtons().clear();
	if (user != null) {
	    submit.setText("Soumettre");
	    buttonBar.getButtons().add(submit);
	    buttonBar.getButtons().add(draft);
	} else {
	    submit.setText("Confirmer");
	    buttonBar.getButtons().add(submit);
	}
	buttonBar.getButtons().add(cancel);
    }

    private void initializeOptions() { // FIXME #1 might be the cause of the empty boxes
	if (rpg == null)
	    throw new IllegalStateException("Cannot initialize the options while the rpg is null.");

	for (ChoiceBox cb : races)
	    setRaceChoices(cb);
	for (ChoiceBox cb : classes)
	    setClassChoices(cb);
	for (ChoiceBox cb : gods)
	    setGodChoices(cb);
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
	// TODO add listener to change on xp to set level label
	addXp.setText("+" + rpg.getParameters().getXpPerEvent());
	abilityPoints.setText(Integer.toString(tmp.getnAbilityPoints()));
	level.setText(Integer.toString(tmp.getLevel()));

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

	{ // Abilities
	    Library<Ability> abilities = new Library<>("Abilités", Ability.class);
	    Library<Ability> specialAbilities = new Library<>("Abilités spéciales", Ability.class);
	    for (Ability ability : tmp.getAbilities())
		abilities.add(ability);
	    for (Ability ability : tmp.getSpecialAbilities())
		specialAbilities.add(ability);
	    this.normalAbilities.getY().setAbilities(abilities);
	    this.specialAbilities.getY().setAbilities(specialAbilities);
	}

	{ // Magic
	    int nGods = 0;
	    for (God god : tmp.getGods()) {
		if (nGods != 0)
		    addGodChoice();
		gods.getLast().setValue(god);
		nGods++;
	    }
	}

	// Notes
	notes.setText(tmp.getNote());
    }

    @FXML
    public void modifyAbilities() {
	ConcretePair<AbilityListEditView, AbilityListEditViewController> view = FXUtil.abilityListEditView(rpg, user,
		tmp,
		"Abilités", PlayerCharacter::getAbilities, PlayerCharacter::setAbilities);
	DesktopApplication.get().getRootContext().enter(view);
    }

    public void modifySpecialAbilities() {
	ConcretePair<AbilityListEditView, AbilityListEditViewController> view = FXUtil.abilityListEditView(rpg, user,
		tmp,
		"Abilités Spéciales", PlayerCharacter::getSpecialAbilities, PlayerCharacter::setSpecialAbilities);
	DesktopApplication.get().getRootContext().enter(view);
    }

    @FXML
    private void removeClassChoice() {
	if (!classes.isEmpty()) {
	    classes.removeLast();
	    removeFromPane(classPane);
	}
    }

    private void removeFromPane(FlowPane pane) {
	pane.getChildren().remove(pane.getChildren().size() - 3); // Account for the control buttons
    }

    @FXML
    private void removeGodChoice() {
	if (!gods.isEmpty()) {
	    gods.removeLast();
	    removeFromPane(godPane);
	}
    }

    @FXML
    private void removeRaceChoice() {
	if (!races.isEmpty()) {
	    races.removeLast();
	    removeFromPane(racePane);
	}
    }

    private void setClassChoices(ChoiceBox choiceBox) {
	choiceBox.setItems(FXCollections.observableArrayList(rpg.getCharacterTypes().get(Classification.CLASS)));
    }

    private void setGodChoices(ChoiceBox choiceBox) {
	choiceBox.setItems(FXCollections.observableArrayList(rpg.getGods().values()));
    }

    public void setPlayerCharacter(PlayerCharacter pc) {
	if (pc == null)
	    pc = new PlayerCharacter("");
	this.pc = pc;
	this.tmp = this.pc.clone();
	initializePlayerCharacterInfo();
    }

    private void setRaceChoices(ChoiceBox choiceBox) {
	choiceBox.setItems(FXCollections.observableArrayList(rpg.getCharacterTypes().get(Classification.RACE)));
    }

    public void setRpg(RPG rpg) {
	this.rpg = rpg;
	initializeOptions();
    }

    public void setUser(User user) {
	this.user = user;
	initializeButtonBar();
    }

    @FXML
    public void submit() {
	clearInvalidFieldsEffects();
	try {
	    applyToTmp();
	    if (user != null)
		user.addAsPending(tmp);
	    else
		tmp.copy(pc);
	    DesktopApplication.get().getRootContext().precedent(true);
	} catch (InvalidFieldsException e) {
	    handleInvalidFieldException(e);
	}
    }

    @Override
    public void update() {
	initializeOptions();
	initializePlayerCharacterInfo();
	initializeButtonBar();
    }

    private void validateFields() throws InvalidFieldsException {
	LinkedList<ConcretePair<String, TextField>> invalidFields = new LinkedList<>();

	if (name.getText().isEmpty())
	    invalidFields.add(new ConcretePair("Le nom ne peut pas être vide", name));
	if (xp.getText().isEmpty())
	    invalidFields.add(new ConcretePair("Le champ xp ne peut pas être vide", xp));
	else if (!Regexs.INTEGER.matcher(xp.getText()).find())
	    invalidFields.add(new ConcretePair("La quantité d'xp doit être un entier.", xp));

	if (abilityPoints.getText().isEmpty())
	    invalidFields.add(
		    new ConcretePair("Le champ Points d'abilités ne peut pas être vide", abilityPoints));
	else if (!Regexs.INTEGER.matcher(abilityPoints.getText()).find())
	    invalidFields.add(new ConcretePair("La quantité de points d'abilités doit être un entier.", abilityPoints));

	if (!invalidFields.isEmpty())
	    throw new InvalidFieldsException(invalidFields);
    }
}
