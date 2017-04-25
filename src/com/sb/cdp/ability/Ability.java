package com.sb.cdp.ability;

import java.util.Arrays;

import com.sb.cdp.PlayerCharacter;

/**
 * An ability owned by a PlayerCharacter.
 * 
 * @author Samuel Beausoleil
 */
public class Ability {
    private String name;
    private int cost;
    private CharacterTypeCondition[] characterTypeConditions;
    private Condition[] prerequisites;
    private String description;
    private int maxTimesTaken;

    public Ability(String name) {
	this(name, 1, null, null, null);
    }

    public Ability(String name, int cost, CharacterTypeCondition[] characterTypeConditions, Condition[] prerequisites,
	    String description) {
	this.name = name;
	this.cost = cost;
	this.characterTypeConditions = characterTypeConditions;
	this.prerequisites = prerequisites;
	this.description = description;
	maxTimesTaken = 1;
    }

    public boolean accept(PlayerCharacter pc) {
	if (pc.getAvailableAbilityPoints() < cost)
	    return false;

	boolean accepted = checkNumberTimesTaken(pc) <= maxTimesTaken;

	if (accepted && prerequisites != null) {
	    for (Condition prequesite : prerequisites)
		if (!prequesite.accept(pc)) {
		    accepted = false;
		    break;
		}
	}
	debug(accepted, pc);
	return accepted;
    }

    private void debug(boolean accepted, PlayerCharacter pc) {
	System.out.println(name + ": " + (accepted ? "accepted" : "not accepted"));
	System.out.println("Times taken: " + checkNumberTimesTaken(pc) + " (" + maxTimesTaken + ")");
	for (Condition prerequisite : prerequisites) {
	    accepted = prerequisite.accept(pc);
	    System.out.println(prerequisite.getClass().getSimpleName() + " : " + prerequisite.describe());
	    System.out.println(accepted);
	}
	System.out.println();
    }

    /**
     * Checks the number of times this ability is present in the pc's normal abilities list.
     * 
     * @param pc
     * @return
     */
    public int checkNumberTimesTaken(PlayerCharacter pc) {
	int nTimes = 0;
	for (Ability ability : pc.getAbilities())
	    if (this.equals(ability))
		nTimes++;
	return nTimes;
    }

    public boolean equals(Ability ability) {
	return name.equals(ability.getName());
    }

    /**
     * Returns the characterTypeConditions.
     * 
     * @return the characterTypeConditions
     */
    public CharacterTypeCondition[] getCharacterTypeConditions() {
	return characterTypeConditions;
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
     * Returns the description.
     * 
     * @return the description
     */
    public String getDescription() {
	return description;
    }

    /**
     * Returns the maxTimesTaken.
     * 
     * @return the maxTimesTaken
     */
    public int getMaxTimesTaken() {
	return maxTimesTaken;
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
     * Returns the prerequisites.
     * 
     * @return the prerequisites
     */
    public Condition[] getPrerequisites() {
	return prerequisites;
    }

    /**
     * Sets the value of characterTypeConditions to that of the parameter.
     * 
     * @param characterTypeConditions
     *            the characterTypeConditions to set
     */
    public void setCharacterTypeConditions(CharacterTypeCondition[] characterTypeConditions) {
	this.characterTypeConditions = characterTypeConditions;
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
     * Sets the value of description to that of the parameter.
     * 
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
	this.description = description;
    }

    /**
     * Sets the value of maxTimesTaken to that of the parameter.
     * 
     * @param maxTimesTaken
     *            the maxTimesTaken to set
     */
    public void setMaxTimesTaken(int maxTimesTaken) {
	this.maxTimesTaken = maxTimesTaken;
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
     * Sets the value of prerequisites to that of the parameter.
     * 
     * @param prerequisites
     *            the prerequisites to set
     */
    public void setPrerequisites(Condition[] prerequisites) {
	this.prerequisites = prerequisites;
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
