package com.sb.cdp.gui.view;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

import com.sb.cdp.DesktopApplication;
import com.sb.cdp.Library;
import com.sb.cdp.PlayerCharacter;
import com.sb.cdp.RPG;
import com.sb.cdp.User;
import com.sb.cdp.ability.Ability;
import com.sb.cdp.gui.Effects;
import com.sb.util.ConcretePair;
import com.sb.util.Getter;
import com.sb.util.Pair;
import com.sb.util.Setter;

import javafx.scene.paint.Color;

/**
 * Controls an AbilityListEditView.
 * This allows editing a PlayerCharacter's ability list. The list which is edited depends upon the
 * getter and setter methods that are provided.
 * 
 * @author Samuel Beausoleil
 */
public class AbilityListEditViewController implements Controller {

    private AbilityListEditView view;

    private User user;
    private PlayerCharacter pc;
    private LinkedHashSet<Pair<String, Collection<Ability>>> availableLibraries;
    private String pcSectionTitle;
    private Getter<PlayerCharacter, List<Ability>> getter;
    private Setter<PlayerCharacter, List<Ability>> setter;

    // Ability transfer
    private boolean fromPlayer;
    private Ability transferedAbility;

    public AbilityListEditViewController(AbilityListEditView view) {
	availableLibraries = new LinkedHashSet<>();
	this.view = view;
	pcSectionTitle = "Abilités";
	initialize();
    }

    private void initialize() {
	view.confirm.setOnAction((e) -> confirm());
	view.cancel.setOnAction((e) -> DesktopApplication.get().getRootContext().precedent(false));
    }

    private void initializeExtended() {
	if (availableLibraries == null)
	    return;

	view.availableLibraries.setLibraries(availableLibraries);

	// Drag & Drop section
	System.out.println("setting D&D extended.abilities...");
	for (AbilityLibraryViewController libraryController : view.availableLibraries.getLibrariesControllers()) {
	    for (Pair<AbilityView, AbilityViewController> abilityPair : libraryController.getViews()) {
		// Drag start
		abilityPair.getX().setOnDragDetected((event) -> {
		    System.out.println("extended.ability.dragDetected");
		    fromPlayer = false;
		    transferedAbility = abilityPair.getY().getAbility();
		    event.consume();
		});
	    }
	}
	System.out.println("setting D&D character.abilities...");
	// FIXME all following drag operations are not detected
	for (Pair<AbilityView, AbilityViewController> abilityPair : view.characterAbilities.getViews()) { 
	    abilityPair.getX().setOnDragDetected((event) -> {
		System.out.println("character.abilities.dragDetected");
		fromPlayer = true;
		transferedAbility = abilityPair.getY().getAbility();
		event.consume();
	    });
	}
	System.out.println("setting D&D libraries.layout entered...");
	view.availableLibraries.getLayout().setOnDragEntered((event) -> {
	    System.out.println("libraries.layout.onDragEntered");
	    if (fromPlayer) // Make the border green
		view.availableLibraries.getLayout().setEffect(Effects.glow(Color.GREEN));
	    else // Make the border red
		view.availableLibraries.getLayout().setEffect(Effects.glow(Color.RED));
	    event.consume();
	});
	System.out.println("setting D&D libraries.layout exited...");
	view.availableLibraries.getLayout().setOnDragExited((event) -> {
	    System.out.println("libraries.layout.onDragExited");
	    view.availableLibraries.getLayout().setEffect(null);
	    event.consume();
	});
	System.out.println("setting D&D libraries.layout dropped...");
	view.availableLibraries.getLayout().setOnDragDropped((event) -> {
	    System.out.println("libraries.layout.onDragDropped");
	    if (fromPlayer && transferedAbility != null) {
		view.characterAbilities.getAbilities().getY().remove(transferedAbility); // Remove from the character
		//view.abilitiesController.getView(). // Remove from the displayed list
		resetDragAndDrop();
	    }
	    event.consume();
	});
	System.out.println("setting D&D character.view entered...");
	view.characterAbilities.getView().setOnDragEntered((event) -> {
	    System.out.println("characterAbilities.view.onDragEntered");
	    if (!fromPlayer) // Make the border green
		view.availableLibraries.getLayout().setEffect(Effects.glow(Color.GREEN));
	    else // Make the border red
		view.availableLibraries.getLayout().setEffect(Effects.glow(Color.RED));
	    event.consume();
	});
	System.out.println("setting D&D character.view dropped...");
	view.characterAbilities.getView().setOnDragDropped((event) -> {
	    if (!fromPlayer && transferedAbility != null) {
		view.characterAbilities.getAbilities().getY().add(transferedAbility);
		resetDragAndDrop();
	    }
	    event.consume();
	});
    }

    private void resetDragAndDrop() {
	fromPlayer = false;
	transferedAbility = null;
    }

    @Override
    public void update() {
	initializeExtended();
	initializeCharacter();
    }

    public void confirm() {
	// TESTME
	Pair<String, Collection<Ability>> abilities = view.characterAbilities.getAbilities();
	LinkedList<Ability> abilitiesList = new LinkedList<>();
	abilitiesList.addAll(abilities.getY());
	setter.set(pc, abilitiesList);
	DesktopApplication.get().getRootContext().precedent(true); // FIXME #1 somehow this changes the number of classes and races in the precedent CharacterEditView
    }

    /**
     * Returns the view.
     * 
     * @return the view
     */
    public AbilityListEditView getView() {
	return view;
    }

    /**
     * Sets the value of view to that of the parameter.
     * 
     * @param view
     *            the view to set
     */
    public void setView(AbilityListEditView view) {
	this.view = view;
    }

    /**
     * Returns the user.
     * 
     * @return the user
     */
    public User getUser() {
	return user;
    }

    /**
     * Sets the value of user to that of the parameter.
     * 
     * @param user
     *            the user to set
     */
    public void setUser(User user) {
	this.user = user;
	Pair<String, Collection<Ability>>[] allowedLibraries = user.getAllowedLibrariesFor(Ability.class);
	for (Pair<String, Collection<Ability>> lib : allowedLibraries)
	    availableLibraries.add(lib);
    }

    /**
     * Returns the pc.
     * 
     * @return the pc
     */
    public PlayerCharacter getPc() {
	return pc;
    }

    /**
     * Sets the value of pc to that of the parameter.
     * 
     * @param pc
     *            the pc to set
     */
    public void setPc(PlayerCharacter pc) {
	this.pc = pc;
	for (Pair<String, Collection<Ability>> lib : pc.getAllowedLibrariesFor(Ability.class))
	    availableLibraries.add(lib);
	initializeCharacter();
    }

    private void initializeCharacter() {
	if (pc == null)
	    return;

	updateAvailablePoints();
	ConcretePair<String, Collection<Ability>> abilities = new ConcretePair(pcSectionTitle, getter.get(pc));
	view.characterAbilities.setAbilities(abilities);
	view.characterAbilities.getView().setCollapsible(false);

	updateAvailableAbilities();
    }

    private void updateAvailableAbilities() {
	for (AbilityLibraryViewController libraryController : view.availableLibraries.getLibrariesControllers())
	    for (Pair<AbilityView, AbilityViewController> view : libraryController.getViews())
		view.getX().setDisable(!view.getY().getAbility().accept(pc));
    }

    /**
     * Updates the displayed amount of available points.
     */
    private void updateAvailablePoints() {
	if (pc != null)
	    view.nAbilityPoints.setText("Points disponibles: " + pc.getAvailableAbilityPoints());
	else
	    view.nAbilityPoints.setText("");
    }

    /**
     * Returns the availableLibraries.
     * 
     * @return the availableLibraries
     */
    public LinkedHashSet<Pair<String, Collection<Ability>>> getAvailableLibraries() {
	return availableLibraries;
    }

    public void extractPublicAbilities(RPG rpg) {
	extractPublicAbilities(rpg.getAbilityLibraries().values());
    }

    public void extractPublicAbilities(Iterable<Library<Ability>> libs) {
	for (Library<Ability> lib : libs)
	    if (lib.isPublic())
		availableLibraries.add(lib);
	update();
    }

    /**
     * Sets the value of getter to that of the parameter.
     * 
     * @param getter
     *            the getter to set
     */
    public void setGetter(Getter<PlayerCharacter, List<Ability>> getter) {
	this.getter = getter;
    }

    /**
     * Sets the value of setter to that of the parameter.
     * 
     * @param setter
     *            the setter to set
     */
    public void setSetter(Setter<PlayerCharacter, List<Ability>> setter) {
	this.setter = setter;
    }

    /**
     * Returns the pcSectionTitle.
     * 
     * @return the pcSectionTitle
     */
    public String getPcSectionTitle() {
	return pcSectionTitle;
    }

    /**
     * Sets the value of pcSectionTitle to that of the parameter.
     * 
     * @param pcSectionTitle
     *            the pcSectionTitle to set
     */
    public void setPcSectionTitle(String pcSectionTitle) {
	this.pcSectionTitle = pcSectionTitle;
    }
}
