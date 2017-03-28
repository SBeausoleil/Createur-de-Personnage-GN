package com.sb.cdp.magic;

import java.util.Set;
import java.util.TreeSet;

public class Domain implements Comparable<Domain> {
    private String name;
    private String magicType;
    private String description;
    private Set<Magic> spells;

    public Domain(String name, String magicType) {
	this(name, magicType, "", new TreeSet<Magic>());
    }

    public Domain(String name, String magicType, String description) {
	this(name, magicType, description, new TreeSet<Magic>());
    }

    public Domain(String name, String magicType, String description, Set<Magic> spells) {
	this.name = name;
	this.magicType = magicType;
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
     * Returns the type.
     * 
     * @return the type
     */
    public String getMagicType() {
	return magicType;
    }

    /**
     * Sets the value of type to that of the parameter.
     * 
     * @param type
     *            the type to set
     */
    public void setMagicType(String magicType) {
	this.magicType = magicType;
    }

    @Override
    public int compareTo(Domain d) {
	// Compare by name
	int comparison = name.compareTo(d.name);
	if (comparison != 0)
	    return comparison;
	// Compare by magic type
	comparison = magicType.compareTo(d.magicType);
	if (comparison != 0)
	    return comparison;
	// Compare by description
	comparison = description.compareTo(d.description);
	if (comparison != 0)
	    return comparison;
	// Compare by spell number
	return Integer.compare(spells.size(), d.spells.size());
    }
}
