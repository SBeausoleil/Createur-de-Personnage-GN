package com.sb.cdp;

import java.util.Map;
import java.util.TreeMap;

import com.sb.cdp.ability.Ability;
import com.sb.cdp.magic.Domain;
import com.sb.cdp.magic.God;
import com.sb.cdp.magic.Prayer;
import com.sb.cdp.magic.Spell;

public class RPG {

    private String name;

    private CharacterTypePool characterTypes;
    private Map<String, Library<String, Ability>> abilityLibraries;
    private Map<String, Library<String, Domain<Spell>>> spellLibraries;
    private Map<String, Library<String, Domain<Prayer>>> prayerLibraries;
    private Map<String, User> users;
    private Map<String, God> gods;

    public RPG(String name) {
	this.name = name;
	characterTypes = new CharacterTypePool();
	abilityLibraries = new TreeMap<>();
	spellLibraries = new TreeMap<>();
	prayerLibraries = new TreeMap<>();
	users = new TreeMap<>();
	gods = new TreeMap<>();
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
     * Returns the characterTypes.
     * 
     * @return the characterTypes
     */
    public CharacterTypePool getCharacterTypes() {
	return characterTypes;
    }

    /**
     * Sets the value of characterTypes to that of the parameter.
     * 
     * @param characterTypes
     *            the characterTypes to set
     */
    public void setCharacterTypes(CharacterTypePool characterTypes) {
	this.characterTypes = characterTypes;
    }

    /**
     * Returns the abilityLibraries.
     * 
     * @return the abilityLibraries
     */
    public Map<String, Library<String, Ability>> getAbilityLibraries() {
	return abilityLibraries;
    }

    /**
     * Sets the value of abilityLibraries to that of the parameter.
     * 
     * @param abilityLibraries
     *            the abilityLibraries to set
     */
    public void setAbilityLibraries(Map<String, Library<String, Ability>> abilityLibraries) {
	this.abilityLibraries = abilityLibraries;
    }

    /**
     * Returns the spellLibraries.
     * 
     * @return the spellLibraries
     */
    public Map<String, Library<String, Domain<Spell>>> getSpellLibraries() {
	return spellLibraries;
    }

    /**
     * Sets the value of spellLibraries to that of the parameter.
     * 
     * @param spellLibraries
     *            the spellLibraries to set
     */
    public void setSpellLibraries(Map<String, Library<String, Domain<Spell>>> spellLibraries) {
	this.spellLibraries = spellLibraries;
    }

    /**
     * Returns the users.
     * 
     * @return the users
     */
    public Map<String, User> getUsers() {
	return users;
    }

    /**
     * Sets the value of users to that of the parameter.
     * 
     * @param users
     *            the users to set
     */
    public void setUsers(Map<String, User> users) {
	this.users = users;
    }

    /**
     * Returns the gods.
     * 
     * @return the gods
     */
    public Map<String, God> getGods() {
	return gods;
    }

    /**
     * Sets the value of gods to that of the parameter.
     * 
     * @param gods
     *            the gods to set
     */
    public void setGods(Map<String, God> gods) {
	this.gods = gods;
    }

    /**
     * Returns the prayerLibraries.
     * @return the prayerLibraries
     */
    public Map<String, Library<String, Domain<Prayer>>> getPrayerLibraries() {
	return prayerLibraries;
    }

    /**
     * Sets the value of prayerLibraries to that of the parameter.
     * @param prayerLibraries the prayerLibraries to set
     */
    public void setPrayerLibraries(Map<String, Library<String, Domain<Prayer>>> prayerLibraries) {
	this.prayerLibraries = prayerLibraries;
    }
}
