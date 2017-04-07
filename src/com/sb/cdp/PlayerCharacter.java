package com.sb.cdp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sb.cdp.ability.Ability;
import com.sb.cdp.magic.God;
import com.sb.cdp.magic.Magic;

public class PlayerCharacter implements LibraryPermissionHolder, Cloneable {
    public static final String ENDURANCE = "Endurance";
    public static final String MANA = "Mana";
    public static final String KARMA = "Karma";
    public static int baseEndurance = 1;
    public static int baseMana = 0;
    public static int baseKarma = 0;
    public static int xpPerLevel = 10;

    private String name;
    private Set<CharacterType> characterTypes;
    private LawAlignment lawAlignment;
    private MoralAlignment moralALignment;
    private int xp;
    private int nAbilityPoints;
    private String description;
    private Map<String, Integer> stats;
    private LinkedHashSet<God> gods;
    private String background;
    private List<Ability> abilities;
    private List<Ability> specialAbilities;
    private Set<Magic> spells;

    private Set<Library> allowedLibraries;
    private String note;

    public PlayerCharacter(String name) {
	this.name = name;

	characterTypes = new LinkedHashSet<>();
	gods = new LinkedHashSet<>();
	abilities = new LinkedList<>();
	specialAbilities = new LinkedList<>();
	spells = new LinkedHashSet<>();

	resetStats();

	allowedLibraries = new HashSet<>();
	note = "";
    }

    public void addAbility(Ability ability) {
	abilities.add(ability);
    }

    public void addSpecialAbility(Ability ability) {
	specialAbilities.add(ability);
    }

    @Override
    public void allow(Library allowedLibrary) {
	allowedLibraries.add(allowedLibrary);
    }

    @Override
    public PlayerCharacter clone() {
	PlayerCharacter clone = new PlayerCharacter(null);
	copy(clone);
	return clone;
    }

    /**
     * Copies this PlayerCharacter's properties to the specified PlayerCharacter.
     * This is a shallow clone in which the lists are cloned but not their content (content is
     * shared across instances).
     * 
     * @param clone
     */
    public void copy(PlayerCharacter clone) {
	clone.name = name;
	clone.characterTypes.clear();
	clone.characterTypes.addAll(characterTypes);
	clone.lawAlignment = lawAlignment;
	clone.moralALignment = moralALignment;
	clone.xp = xp;
	clone.nAbilityPoints = nAbilityPoints;
	clone.description = description;
	clone.stats.clear();
	clone.stats.putAll(stats);
	clone.gods.clear();
	clone.gods.addAll(gods);
	clone.background = background;
	clone.abilities.clear();
	clone.abilities.addAll(abilities);
	clone.specialAbilities.clear();
	clone.specialAbilities.addAll(specialAbilities);
	clone.spells.clear();
	clone.spells.addAll(spells);
	clone.allowedLibraries.clear();
	clone.allowedLibraries.addAll(allowedLibraries);
	clone.note = note;
    }

    /**
     * Returns the abilities.
     * 
     * @return the abilities
     */
    public List<Ability> getAbilities() {
	return abilities;
    }

    @Override
    public Set<Library> getAllowedLibraries() {
	return allowedLibraries;
    }

    public int getAvailableAbilityPoints() {
	int points = nAbilityPoints;
	for (Ability ability : abilities)
	    points -= ability.getCost();
	for (Ability specialAbility : specialAbilities)
	    points -= specialAbility.getCost();
	return points;
    }

    /**
     * Returns the background.
     * 
     * @return the background
     */
    public String getBackground() {
	return background;
    }

    /**
     * Returns the classes.
     * 
     * @return the classes
     */
    public Set<CharacterType> getCharacterTypes() {
	return characterTypes;
    }

    /**
     * Returns the description.
     * 
     * @return the description
     */
    public String getDescription() {
	return description;
    }

    /**
     * Returns the gods.
     * 
     * @return the gods
     */
    public LinkedHashSet<God> getGods() {
	return gods;
    }

    /**
     * Returns the lawAlignment.
     * 
     * @return the lawAlignment
     */
    public LawAlignment getLawAlignment() {
	return lawAlignment;
    }

    public int getLevel() {
	return xp / xpPerLevel;
    }

    /**
     * Returns the moralALignment.
     * 
     * @return the moralALignment
     */
    public MoralAlignment getMoralALignment() {
	return moralALignment;
    }

    /**
     * Returns the nAbilityPoints.
     * 
     * @return the nAbilityPoints
     */
    public int getnAbilityPoints() {
	return nAbilityPoints;
    }

    /**
     * Returns the name.
     * 
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * Returns the note.
     * 
     * @return the note
     */
    public String getNote() {
	return note;
    }

    /**
     * Returns the specialAbilities.
     * 
     * @return the specialAbilities
     */
    public List<Ability> getSpecialAbilities() {
	return specialAbilities;
    }

    /**
     * Returns the spells.
     * 
     * @return the spells
     */
    public Set<Magic> getSpells() {
	return spells;
    }

    public int getStat(String stat) {
	if (hasStat(stat))
	    return stats.get(stat);
	return 0;
    }

    /**
     * Returns the stats.
     * 
     * @return the stats
     */
    public Map<String, Integer> getStats() {
	return stats;
    }

    /**
     * Returns the xp.
     * 
     * @return the xp
     */
    public int getXp() {
	return xp;
    }

    public boolean hasStat(String stat) {
	return stats.containsKey(stat);
    }

    public void removeAbility(Ability ability) {
	abilities.remove(ability.getName());
    }

    public void removeSpecialAbility(Ability ability) {
	specialAbilities.remove(ability.getName());
    }

    /**
     * Resets the character's stats.
     */
    private void resetStats() {
	stats = new HashMap<>();
	stats.put(ENDURANCE, baseEndurance);
	stats.put(MANA, baseMana);
	stats.put(KARMA, baseKarma);
    }

    public void setAbilies(List<Ability> abilities) {
	this.abilities = abilities;
	resetStats();
    }

    /**
     * Sets the value of abilities to that of the parameter.
     * 
     * @param abilities
     *            the abilities to set
     */
    public void setAbilities(List<Ability> abilities) {
	this.abilities = abilities;
    }

    @Override
    public void setAllowedLibraries(Set<Library> allowedLibraries) {
	this.allowedLibraries = allowedLibraries;
    }

    /**
     * Sets the value of background to that of the parameter.
     * 
     * @param background
     *            the background to set
     */
    public void setBackground(String background) {
	this.background = background;
    }

    /**
     * Sets the value of classes to that of the parameter.
     * 
     * @param classes
     *            the classes to set
     */
    public void setCharacterTypes(Set<CharacterType> classes) {
	this.characterTypes = classes;
    }

    /**
     * Sets the value of description to that of the parameter.
     * 
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
	this.description = description;
    }

    /**
     * Sets the value of gods to that of the parameter.
     * 
     * @param gods
     *            the gods to set
     */
    public void setGods(LinkedHashSet<God> gods) {
	this.gods = gods;
    }

    /**
     * Sets the value of lawAlignment to that of the parameter.
     * 
     * @param lawAlignment
     *            the lawAlignment to set
     */
    public void setLawAlignment(LawAlignment lawAlignment) {
	this.lawAlignment = lawAlignment;
    }

    /**
     * Sets the value of moralALignment to that of the parameter.
     * 
     * @param moralALignment
     *            the moralALignment to set
     */
    public void setMoralALignment(MoralAlignment moralALignment) {
	this.moralALignment = moralALignment;
    }

    /**
     * Sets the value of nAbilityPoints to that of the parameter.
     * 
     * @param nAbilityPoints
     *            the nAbilityPoints to set
     */
    public void setnAbilityPoints(int nAbilityPoints) {
	this.nAbilityPoints = nAbilityPoints;
    }

    /**
     * Sets the value of name to that of the parameter.
     * 
     * @param name
     *            the name to set
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * Sets the value of note to that of the parameter.
     * 
     * @param note
     *            the note to set
     */
    public void setNote(String note) {
	this.note = note;
    }

    public void setSpecialAbilies(List<Ability> abilities) {
	this.specialAbilities = abilities;
	resetStats();
    }

    /**
     * Sets the value of specialAbilities to that of the parameter.
     * 
     * @param specialAbilities
     *            the specialAbilities to set
     */
    public void setSpecialAbilities(List<Ability> specialAbilities) {
	this.specialAbilities = specialAbilities;
    }

    /**
     * Sets the value of spells to that of the parameter.
     * 
     * @param spells
     *            the spells to set
     */
    public void setSpells(Set<Magic> spells) {
	this.spells = spells;
    }

    public void setStat(String stat, int value) {
	stats.put(stat, value);
    }

    /**
     * Sets the value of stats to that of the parameter.
     * 
     * @param stats
     *            the stats to set
     */
    public void setStats(Map<String, Integer> stats) {
	this.stats = stats;
    }

    /**
     * Sets the value of xp to that of the parameter.
     * 
     * @param xp
     *            the xp to set
     */
    public void setXp(int xp) {
	this.xp = xp;
    }
}
