package com.sb.cdp.gui.view;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

import com.sb.cdp.Library;
import com.sb.cdp.PlayerCharacter;
import com.sb.cdp.RPG;
import com.sb.cdp.User;
import com.sb.cdp.ability.Ability;
import com.sb.util.Getter;
import com.sb.util.Setter;

public class AbilityListEditViewController implements Controller {

    private AbilityListEditView view;

    private User user;
    private PlayerCharacter pc;
    private LinkedHashSet<Library<String, Ability>> publicAbilities;
    private String pcSectionTitle;
    private Getter<PlayerCharacter, List<Ability>> getter;
    private Setter<PlayerCharacter, List<Ability>> setter;

    public AbilityListEditViewController(AbilityListEditView view) {
	publicAbilities = new LinkedHashSet<>();
	this.view = view;
	pcSectionTitle = "Abilités";
    }

    private void initializeExtended() {
	view.librariesController.setLibraries(publicAbilities);
    }

    @Override
    public void update() {
	initializeExtended();
	initializeCharacter();
    }

    public void confirm() {
	// TESTME
	Library<?, Ability> abilities = view.abilitiesController.getAbilities();
	LinkedList<Ability> abilitiesList = new LinkedList<>();
	abilitiesList.addAll(abilities.values());
	setter.set(pc, abilitiesList);
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
	Library<String, Ability>[] allowedLibraries = (Library<String, Ability>[]) user.getAllowedLibrariesFor(
		Ability.class);
	for (Library<String, Ability> lib : allowedLibraries)
	    publicAbilities.add(lib);
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
	for (Library<String, Ability> lib : (Library<String, Ability>[]) pc.getAllowedLibrariesFor(Ability.class))
	    publicAbilities.add(lib);
	initializeCharacter();
    }

    private void initializeCharacter() {
	updateAvailablePoints();
	Library<String, Ability> abilities = new Library<>(pcSectionTitle, Ability.class);
	for (Ability ability : getter.get(pc))
	    abilities.put(ability.getName(), ability);
	view.abilitiesController.setAbilities(abilities);
    }

    /**
     * Updates the displayed amount of available points.
     */
    private void updateAvailablePoints() {
	view.nAbilityPoints.setText("Points disponibles: " + pc.getAvailableAbilityPoints());
    }

    /**
     * Returns the publicAbilities.
     * 
     * @return the publicAbilities
     */
    public LinkedHashSet<Library<String, Ability>> getPublicAbilities() {
	return publicAbilities;
    }

    public void extractPublicAbilities(RPG rpg) {
	extractPublicAbilities(rpg.getAbilityLibraries().values());
    }

    public void extractPublicAbilities(Iterable<Library<String, Ability>> libs) {
	for (Library<String, Ability> lib : libs)
	    if (lib.isPublic())
		publicAbilities.add(lib);
    }

    /**
     * Sets the value of getter to that of the parameter.
     * @param getter the getter to set
     */
    public void setGetter(Getter<PlayerCharacter, List<Ability>> getter) {
        this.getter = getter;
    }

    /**
     * Sets the value of setter to that of the parameter.
     * @param setter the setter to set
     */
    public void setSetter(Setter<PlayerCharacter, List<Ability>> setter) {
        this.setter = setter;
    }

    /**
     * Returns the pcSectionTitle.
     * @return the pcSectionTitle
     */
    public String getPcSectionTitle() {
        return pcSectionTitle;
    }

    /**
     * Sets the value of pcSectionTitle to that of the parameter.
     * @param pcSectionTitle the pcSectionTitle to set
     */
    public void setPcSectionTitle(String pcSectionTitle) {
        this.pcSectionTitle = pcSectionTitle;
    }
}
