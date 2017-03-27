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

	stats = new HashMap<>();
	stats.put(ENDURANCE, baseEndurance);
	stats.put(MANA, baseMana);
	stats.put(KARMA, baseKarma);

	allowedLibraries = new HashSet<>();
	note = "";
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
     * Sets the value of name to that of the parameter.
     * 
     * @param name
     *            the name to set
     */
    public void setName(String name) {
	this.name = name;
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
     * Sets the value of classes to that of the parameter.
     * 
     * @param classes
     *            the classes to set
     */
    public void setCharacterTypes(Set<CharacterType> classes) {
	this.characterTypes = classes;
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
     * Sets the value of moralALignment to that of the parameter.
     * 
     * @param moralALignment
     *            the moralALignment to set
     */
    public void setMoralALignment(MoralAlignment moralALignment) {
	this.moralALignment = moralALignment;
    }

    /**
     * Returns the lawAlignment.
     * 
     * @return the lawAlignment
     */
    public LawAlignment getLawAlignment() {
	return lawAlignment;
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
     * Returns the xp.
     * 
     * @return the xp
     */
    public int getXp() {
	return xp;
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

    /**
     * Returns the nAbilityPoints.
     * 
     * @return the nAbilityPoints
     */
    public int getnAbilityPoints() {
	return nAbilityPoints;
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
     * Returns the stats.
     * 
     * @return the stats
     */
    public Map<String, Integer> getStats() {
	return stats;
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
     * Returns the gods.
     * 
     * @return the gods
     */
    public LinkedHashSet<God> getGods() {
	return gods;
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
     * Returns the background.
     * 
     * @return the background
     */
    public String getBackground() {
	return background;
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
     * Returns the abilities.
     * 
     * @return the abilities
     */
    public List<Ability> getAbilities() {
	return abilities;
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

    /**
     * Returns the specialAbilities.
     * 
     * @return the specialAbilities
     */
    public List<Ability> getSpecialAbilities() {
	return specialAbilities;
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

    public void addAbility(Ability ability) {
	abilities.add(ability);
    }

    public void removeAbility(Ability ability) {
	abilities.remove(ability.getName());
    }

    public void addSpecialAbility(Ability ability) {
	specialAbilities.add(ability);
    }

    public void removeSpecialAbility(Ability ability) {
	specialAbilities.remove(ability.getName());
    }

    public int getLevel() {
	return xp / xpPerLevel;
    }

    public int getAvailableAbilityPoints() {
	int points = nAbilityPoints;
	for (Ability ability : abilities)
	    points -= ability.getCost();
	for (Ability specialAbility : specialAbilities)
	    points -= specialAbility.getCost();
	return points;
    }

    public int getStat(String stat) {
	if (hasStat(stat))
	    return stats.get(stat);
	return 0;
    }

    public void setStat(String stat, int value) {
	stats.put(stat, value);
    }

    public boolean hasStat(String stat) {
	return stats.containsKey(stat);
    }

    /**
     * Returns the spells.
     * 
     * @return the spells
     */
    public Set<Magic> getSpells() {
	return spells;
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

    @Override
    public Set<Library> getAllowedLibraries() {
	return allowedLibraries;
    }

    @Override
    public void setAllowedLibraries(Set<Library> allowedLibraries) {
	this.allowedLibraries = allowedLibraries;
    }

    @Override
    public void allow(Library allowedLibrary) {
	allowedLibraries.add(allowedLibrary);
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
     * Sets the value of note to that of the parameter.
     * 
     * @param note
     *            the note to set
     */
    public void setNote(String note) {
	this.note = note;
    }

    @Override
    public PlayerCharacter clone() {
	PlayerCharacter clone = new PlayerCharacter(null);
	clone(clone);
	return clone;
    }

    /**
     * Copies this PlayerCharacter's properties to the specified PlayerCharacter.
     * This is a shallow clone in which the lists are cloned but not their content (content is shared across instances).
     * @param clone
     */
    public void clone(PlayerCharacter clone) {
	clone.name = name;
	clone.characterTypes.addAll(characterTypes);
	clone.lawAlignment = lawAlignment;
	clone.moralALignment = moralALignment;
	clone.xp = xp;
	clone.nAbilityPoints = nAbilityPoints;
	clone.stats.putAll(stats);
	clone.gods.addAll(gods);
	clone.background = background;
	clone.abilities.addAll(abilities);
	clone.specialAbilities.addAll(specialAbilities);
	clone.spells.addAll(spells);
	clone.allowedLibraries.addAll(allowedLibraries);
	clone.note = note;
    }
}
