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

    public static PlayerClass get(String className) {
	PlayerClass pc = CLASSES.get(className);
	if (pc == null) {
	    pc = new PlayerClass(className);
	    CLASSES.put(className, pc);
	}
	return pc;
    }
}
