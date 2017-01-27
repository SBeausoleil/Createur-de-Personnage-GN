package com.sb.cdp.spell;

import java.util.Set;

public class Domain<SpellType extends Spell> {
    private String name;
    private Set<SpellType> spells;

    public Domain(String name, Set<SpellType> spells) {
	this.name = name;
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
}
