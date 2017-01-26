package com.sb.idl.ability;

import com.sb.idl.PlayerCharacter;
import com.sb.idl.PlayerClass;

public class Ability {
    private String name;
    private int cost;
    private PlayerClass[] classes;
    private Condition[] prerequisites;
    private String description;
    private StatBonus[] statBonuses;

    public Ability(String name, int cost, PlayerClass[] classes, Condition[] prerequisites, StatBonus[] statBonuses,
	    String description) {
	this.name = name;
	this.cost = cost;
	this.classes = classes;
	this.prerequisites = prerequisites;
	this.description = description;
	this.statBonuses = statBonuses;
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
     * Returns the classes.
     * 
     * @return the classes
     */
    public PlayerClass[] getClasses() {
	return classes;
    }

    /**
     * Sets the value of classes to that of the parameter.
     * 
     * @param classes
     *            the classes to set
     */
    public void setClasses(PlayerClass[] classes) {
	this.classes = classes;
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

    /**
     * Returns the statBonuses.
     * 
     * @return the statBonuses
     */
    public StatBonus[] getStatBonuses() {
	return statBonuses;
    }

    /**
     * Sets the value of statBonuses to that of the parameter.
     * 
     * @param statBonuses
     *            the statBonuses to set
     */
    public void setStatBonuses(StatBonus[] statBonuses) {
	this.statBonuses = statBonuses;
    }

    public void applyBonuses(PlayerCharacter pc) {
	for (StatBonus bonus : statBonuses)
	    bonus.apply(pc);
    }

    public void removeBonuses(PlayerCharacter pc) {
	for (StatBonus bonus : statBonuses)
	    bonus.remove(pc);
    }

    public boolean accept(PlayerCharacter pc) {
	boolean accepted = false;
	if (classes == null)
	    accepted = true;
	else {
	    outerLoop: for (PlayerClass neededClass : classes)
	    for (PlayerClass playerClass : pc.getClasses())
		if (neededClass.equals(playerClass)) {
		    accepted = true;
		    break outerLoop;
		}
	}
	if (accepted && prerequisites != null) {
	    for (Condition prequesite : prerequisites)
		if (!prequesite.accept(pc)) {
		    accepted = false;
		    break;
		}
	}
	return accepted;
    }
}
