package com.sb.cdp;

import java.util.HashMap;

public class PlayerClass {

    private static final HashMap<String, PlayerClass> CLASSES = new HashMap<>();

    private final String name;
    private String description;

    private PlayerClass(String name) {
	this.name = name;
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
     * Returns a PlayerClass with the specified name.
     * @param className the name of the player class. Not case sensitive.
     * @returna a PlayerClass with the specified name
     */
    public static PlayerClass get(String className) {
	PlayerClass pc = CLASSES.get(className.toLowerCase());
	if (pc == null) {
	    pc = new PlayerClass(className);
	    CLASSES.put(className.toLowerCase(), pc);
	}
	return pc;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "PlayerClass [name=" + name + ", description=" + description + "]";
    }
}
