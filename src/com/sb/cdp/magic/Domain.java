package com.sb.cdp.magic;

import java.util.Set;
import java.util.TreeSet;

public class Domain<SpellType extends Magic> {
    private String name;
    private String description;
    private Set<SpellType> spells;

    public Domain(String name) {
	this.name = name;
	this.description = "";
	spells = new TreeSet<>();
    }
    
    public Domain(String name, String description) {
	this.name = name;
	this.description = description;
	spells = new TreeSet<>();
    }
    
    public Domain(String name, String description, Set<SpellType> spells) {
	this.name = name;
	this.description = description;
	this.spells = spells;
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
     * Returns the spells.
     * 
     * @return the spells
     */
    public Set<SpellType> getSpells() {
	return spells;
    }

    /**
     * Sets the value of spells to that of the parameter.
     * 
     * @param spells
     *            the spells to set
     */
    public void setSpells(Set<SpellType> spells) {
	this.spells = spells;
    }

    /**
     * Returns the description.
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of description to that of the parameter.
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
