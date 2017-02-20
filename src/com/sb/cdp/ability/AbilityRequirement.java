package com.sb.cdp.ability;

import com.sb.cdp.PlayerCharacter;

public class AbilityRequirement implements Condition {

    private Ability neededAbility;

    public AbilityRequirement(Ability neededAbility) {
	this.neededAbility = neededAbility;
    }

    @Override
    public boolean accept(PlayerCharacter pc) {
	return pc.getAbilities().containsKey(neededAbility.getName())
		|| pc.getSpecialAbilities().containsKey(neededAbility.getName());
    }

    @Override
    public String describe() {
	return "Habilité requise: " + neededAbility.getName();
    }

    /**
     * Returns the neededAbility.
     * 
     * @return the neededAbility
     */
    public Ability getNeededAbility() {
	return neededAbility;
    }

    /**
     * Sets the value of neededAbility to that of the parameter.
     * 
     * @param neededAbility
     *            the neededAbility to set
     */
    public void setNeededAbility(Ability neededAbility) {
	this.neededAbility = neededAbility;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "AbilityRequirement [neededAbility=" + neededAbility.getName() + "]";
    }

}
