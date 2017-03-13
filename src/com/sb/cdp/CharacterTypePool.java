package com.sb.cdp;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.sb.cdp.CharacterType.Classification;

public class CharacterTypePool {
    private final Map<Classification, Map<String, CharacterType>> REGISTERED = new HashMap<>();

    /**
     * Creates a new instance of CharacterTypePool.<br/>
     * Initializes the REGISTERED map to hold the appropriate buckets for each existing
     * Classification.
     * 
     * @see #REGISTERED
     * @see CharacterType.Classification
     */
    public CharacterTypePool() {
	for (Classification classification : Classification.values())
	    REGISTERED.put(classification, new HashMap<>());
    }

    /**
     * Returns an array of all the existing character types with this name.<br/>
     * If no types exist with this name, an empty array is returned.
     * 
     * @param typeName
     * @return an array of all the existing character types with this name.
     */
    public CharacterType[] find(String typeName) {
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
    public CharacterType get(String typeName, Classification classification) {
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
    public CharacterType put(String name, Classification classification, String description) {
	CharacterType type = new CharacterType(name, classification);
	type.setDescription(description);
	REGISTERED.get(classification).put(name, type);
	return type;
    }
    
    public Collection<CharacterType> get(Classification classification) {
	return REGISTERED.get(classification).values();
    }
}
