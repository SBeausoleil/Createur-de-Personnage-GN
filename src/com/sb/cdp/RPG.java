package com.sb.cdp;

import java.util.Map;

import com.sb.cdp.ability.Ability;
import com.sb.cdp.spell.Spell;

public class RPG {

    private String name;

    private CharacterTypePool characterTypes;
    private Map<String, Library<String, Ability>> abilityLibraries;
    private Map<String, Library<String, Spell<?>>> spellLibraries;
    private Map<String, User> users;

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
    public Map<String, Library<String, Spell<?>>> getSpellLibraries() {
	return spellLibraries;
    }

    /**
     * Sets the value of spellLibraries to that of the parameter.
     * 
     * @param spellLibraries
     *            the spellLibraries to set
     */
    public void setSpellLibraries(Map<String, Library<String, Spell<?>>> spellLibraries) {
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
}
