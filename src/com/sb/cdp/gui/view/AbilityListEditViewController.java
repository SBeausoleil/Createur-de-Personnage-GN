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

	view.extendedLibrariesController.setLibraries(availableLibraries);

	// Drag & Drop section
	for (AbilityLibraryViewController libraryController : view.extendedLibrariesController.getLibrariesControllers()) {
	    for (Pair<AbilityView, AbilityViewController> abilityPair : libraryController.getViews()) {
		// Drag start
		abilityPair.getX().setOnDragDetected((event) -> {
		    System.out.println("dragDetected");
		    fromPlayer = false;
		    transferedAbility = abilityPair.getY().getAbility();
		    event.consume();
		});
	    }
	}
	view.extendedLibrariesController.getLayout().setOnDragEntered((event) -> {
	    System.out.println("layout.onDragEntered");
	    if (fromPlayer) // Make the border green
		view.extendedLibrariesController.getLayout().setEffect(Effects.glow(Color.GREEN));
	    else // Make the border red
		view.extendedLibrariesController.getLayout().setEffect(Effects.glow(Color.RED));
	    event.consume();
	});
	view.extendedLibrariesController.getLayout().setOnDragExited((event) -> {
	    System.out.println("layout.onDragExited");
	    view.extendedLibrariesController.getLayout().setEffect(null);
	    event.consume();
	});
	view.extendedLibrariesController.getLayout().setOnDragDropped((event) -> {
	    System.out.println("layout.onDragDropped");
	    if (fromPlayer) {
		view.abilitiesController.getAbilities().getY().remove(transferedAbility); // Remove from the character
		//view.abilitiesController.getView(). // Remove from the displayed list
	    }
	});
    }

    @Override
    public void update() {
	initializeExtended();
	initializeCharacter();
    }

    public void confirm() {
	// TESTME
	Pair<String, Collection<Ability>> abilities = view.abilitiesController.getAbilities();
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
	view.abilitiesController.setAbilities(abilities);
	view.abilitiesController.getView().setCollapsible(false);

	updateAvailableAbilities();
    }

    private void updateAvailableAbilities() {
	for (AbilityLibraryViewController libraryController : view.extendedLibrariesController.getLibrariesControllers())
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
