package com.sb.cdp;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import com.sb.cdp.ability.Ability;
import com.sb.cdp.magic.DomainLibrary;
import com.sb.cdp.magic.God;

public class RPG implements Serializable {
    private static final long serialVersionUID = -1582657998207199401L;

    private String name;

    private CharacterTypePool characterTypes;
    /**
     * A map of all the registered ability libraries.
     * Map key: the name of the library.
     */
    private Map<String, Library<String, Ability>> abilityLibraries;
    /**
     * A map of all the registered Magic libraries.
     * Map key: the magic type of the DomainLibrary.
     * Map value: the libraries linked to this magic type
     */
    private Map<String, Set<DomainLibrary>> domainLibraries;
    private Map<String, User> users;
    private Map<String, God> gods;
    
    private final RPGParameters PARAMETERS;

    public RPG(String name) {
	this(name, new RPGParameters());
    }

    public RPG(String name, RPGParameters parameters) {
	this.name = name;
	this.PARAMETERS = parameters;
	characterTypes = new CharacterTypePool();
	abilityLibraries = new TreeMap<>();
	domainLibraries = new TreeMap<>();
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
     * Returns the domainLibraries.
     * Be careful when modifying the Map or it's underlying set when using this method. If you wish
     * to insert new data, use the {@link #registerDomainLibrary(DomainLibrary)} method instead.
     * 
     * @return the domainLibraries
     */
    public Map<String, Set<DomainLibrary>> getDomainLibraries() {
	return domainLibraries;
    }

    public void registerDomainLibrary(DomainLibrary dl) {
	Set<DomainLibrary> registeredLibraries = domainLibraries.get(dl.getMagicType());
	if (registeredLibraries == null) {
	    registeredLibraries = new TreeSet<>();
	    domainLibraries.put(dl.getMagicType(), registeredLibraries);
	}
	registeredLibraries.add(dl);
    }

    /**
     * Returns the parameters.
     * @return the parameters.
     */
    public RPGParameters getParameters() {
        return PARAMETERS;
    }
}
