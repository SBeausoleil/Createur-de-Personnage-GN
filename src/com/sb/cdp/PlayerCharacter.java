package com.sb.cdp;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.sb.cdp.ability.Ability;
import com.sb.cdp.spell.God;
import com.sb.cdp.spell.Prayer;
import com.sb.cdp.spell.Spell;

public class PlayerCharacter {
    public static final String ENDURANCE = "endurance";
    public static final String MANA = "mana";
    public static final String KARMA = "karma";
    public static int baseEndurance = 1;
    public static int baseMana = 0;
    public static int baseKarma = 0;
    public static int xpPerLevel = 10;
    
    private String name;
    private Set<PlayerClass> classes;
    private MoralAlignment moralALignment;
    private LawAlignment lawAlignment;
    private int xp;
    private int nAbilityPoints;
    private Map<String, Integer> stats;
    private Set<God> gods;
    private String background;
    private Map<String, Ability> abilities;
    private Map<String, Ability> specialAbilities;
    private Map<String, Spell> spells;
    private Map<String, Prayer> prayers;

    public PlayerCharacter(String name, Set<PlayerClass> classes, MoralAlignment moralALignment,
	    LawAlignment lawAlignment, int xp, int nAbilityPoints) {
	this.name = name;
	this.classes = classes;
	this.moralALignment = moralALignment;
	this.lawAlignment = lawAlignment;
	this.xp = xp;
	this.nAbilityPoints = nAbilityPoints;
	
	stats = new HashMap<>();
	stats.put(ENDURANCE, baseEndurance);
	stats.put(MANA, baseMana);
	stats.put(KARMA, baseKarma);
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
    public Set<PlayerClass> getClasses() {
	return classes;
    }

    /**
     * Sets the value of classes to that of the parameter.
     * 
     * @param classes
     *            the classes to set
     */
    public void setClasses(Set<PlayerClass> classes) {
	this.classes = classes;
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
    public Set<God> getGods() {
	return gods;
    }

    /**
     * Sets the value of gods to that of the parameter.
     * 
     * @param gods
     *            the gods to set
     */
    public void setGods(Set<God> gods) {
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
    public Map<String, Ability> getAbilities() {
	return abilities;
    }

    /**
     * Sets the value of abilities to that of the parameter.
     * 
     * @param abilities
     *            the abilities to set
     */
    public void setAbilities(Map<String, Ability> abilities) {
	this.abilities = abilities;
    }

    /**
     * Returns the specialAbilities.
     * 
     * @return the specialAbilities
     */
    public Map<String, Ability> getSpecialAbilities() {
	return specialAbilities;
    }

    /**
     * Sets the value of specialAbilities to that of the parameter.
     * 
     * @param specialAbilities
     *            the specialAbilities to set
     */
    public void setSpecialAbilities(Map<String, Ability> specialAbilities) {
	this.specialAbilities = specialAbilities;
    }

    /**
     * Returns the spells.
     * 
     * @return the spells
     */
    public Map<String, Spell> getSpells() {
	return spells;
    }

    /**
     * Sets the value of spells to that of the parameter.
     * 
     * @param spells
     *            the spells to set
     */
    public void setSpells(Map<String, Spell> spells) {
	this.spells = spells;
    }

    /**
     * Returns the prayers.
     * 
     * @return the prayers
     */
    public Map<String, Prayer> getPrayers() {
	return prayers;
    }

    /**
     * Sets the value of prayers to that of the parameter.
     * 
     * @param prayers
     *            the prayers to set
     */
    public void setPrayers(Map<String, Prayer> prayers) {
	this.prayers = prayers;
    }
    
    public void addAbility(Ability ability) {
	abilities.put(ability.getName(), ability);
    }
    
    public void removeAbility(Ability ability) {
	abilities.remove(ability.getName());
    }
    
    public void addSpecialAbility(Ability ability) {
	specialAbilities.put(ability.getName(), ability);
    }
    
    public void removeSpecialAbility(Ability ability) {
	specialAbilities.remove(ability.getName());
    }
    
    public int getLevel() {
	return xp / xpPerLevel;
    }
    
    public int getAvailableAbilityPoints() {
	int points = nAbilityPoints;
	for (Ability ability : abilities.values())
	    points -= ability.getCost();
	for (Ability specialAbility : specialAbilities.values())
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
}
