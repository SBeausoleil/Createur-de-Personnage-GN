package com.sb.cdp.ability;

import java.util.Arrays;

import com.sb.cdp.PlayerCharacter;

public class Ability {
    private String name;
    private int cost;
    private Condition[] prerequisites;
    private String description;

    public Ability(String name) {
	this.name = name;
    }

    public Ability(String name, int cost, Condition[] prerequisites, String description) {
	this.name = name;
	this.cost = cost;
	this.prerequisites = prerequisites;
	this.description = description;
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
     * Returns the cost.
     * 
     * @return the cost
     */
    public int getCost() {
	return cost;
    }

    /**
     * Sets the value of cost to that of the parameter.
     * 
     * @param cost
     *            the cost to set
     */
    public void setCost(int cost) {
	this.cost = cost;
    }

    /**
     * Returns the prerequisites.
     * 
     * @return the prerequisites
     */
    public Condition[] getPrerequisites() {
	return prerequisites;
    }

    /**
     * Sets the value of prerequisites to that of the parameter.
     * 
     * @param prerequisites
     *            the prerequisites to set
     */
    public void setPrerequisites(Condition[] prerequisites) {
	this.prerequisites = prerequisites;
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

    public boolean accept(PlayerCharacter pc) {
	boolean accepted = false;
	// TODO deal with classes & races
	if (accepted && prerequisites != null) {
	    for (Condition prequesite : prerequisites)
		if (!prequesite.accept(pc)) {
		    accepted = false;
		    break;
		}
	}
	return accepted;
    }

    public boolean equals(Ability ability) {
	return name.equals(ability.getName());
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "Ability [name=" + name + ", cost=" + cost + ", prerequisites="
		+ Arrays.toString(prerequisites) + ", description=" + description + "]";
    }
}
