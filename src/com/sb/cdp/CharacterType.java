package com.sb.cdp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CharacterType {

    public static enum Classification {
	RACE, CLASS;
    }

    private static final Map<Classification, Map<String, CharacterType>> REGISTERED = new HashMap<>();

    /**
     * Initializes the REGISTERED map to hold the appropriate buckets for each existing
     * Classification.
     * 
     * @see #REGISTERED
     * @see #Classification
     */
    static {
	for (Classification classification : Classification.values())
	    REGISTERED.put(classification, new HashMap<>());
    }

    private final String name;
    private String description;
    private Classification classification;

    private CharacterType(String name, Classification classification) {
	this.name = name;
	this.classification = classification;
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
     * Returns the description.
     * 
     * @return the description
     */
    public String getDescription() {
	return description;
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
     * Returns an array of all the existing character types with this name.
     * If no types exist with this name, an empty array is returned.
     * 
     * @param typeName
     * @return an array of all the existing character types with this name.
     */
    public static CharacterType[] find(String typeName) {
	Set<CharacterType> found = new HashSet<>();

	for (Map<String, CharacterType> subregistry : REGISTERED.values()) {
	    CharacterType foundType = subregistry.get(typeName);
	    if (foundType != null)
		found.add(foundType);
	}

	return found.toArray(new CharacterType[found.size()]);
    }

    /**
     * Returns a PlayerClass with the specified name.
     * 
     * @param typeName
     *            the name of the player class. Not case sensitive.
     * @returna a PlayerClass with the specified name
     */
    public static CharacterType get(String typeName, Classification classification) {
	CharacterType type = REGISTERED.get(classification).get(typeName);
	if (type == null) {
	    type = new CharacterType(typeName, classification);
	    REGISTERED.get(classification).put(typeName, type);
	}
	return type;
    }

    /**
     * Adds a CharacterType to the registered ones.
     * Use this function with great caution, as it may overwrite previous values.
     * 
     * @param name
     * @param classification
     * @param description
     * @return the newly created CharacterType.
     */
    public static CharacterType put(String name, Classification classification, String description) {
	CharacterType type = new CharacterType(name, classification);
	type.setDescription(description);
	REGISTERED.get(classification).put(name, type);
	return type;
    }

    /**
     * Returns the type.
     * 
     * @return the type
     */
    public Classification getClassification() {
	return classification;
    }

    /**
     * Sets the value of type to that of the parameter.
     * 
     * @param type
     *            the type to set
     */
    public void setClassification(Classification type) {
	this.classification = type;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "CharacterType [name=" + name + ", description=" + description + ", classification=" + classification
		+ "]";
    }
}
