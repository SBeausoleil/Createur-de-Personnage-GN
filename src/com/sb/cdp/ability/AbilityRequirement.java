package com.sb.cdp.ability;

import com.sb.cdp.PlayerCharacter;

public class AbilityRequirement implements Condition {

    private Ability[] neededAbilities;

    public AbilityRequirement(Ability[] neededAbilities) {
	this.neededAbilities = neededAbilities;
    }

    @Override
    public boolean accept(PlayerCharacter pc) {
	for (Ability ability : neededAbilities)
	    if (!pc.getAbilities().containsKey(ability.getName())
		    && !pc.getSpecialAbilities().containsKey(ability.getName()))
		return false;
	return true;
    }

    /**
     * Returns the abilities.
     * 
     * @return the abilities
     */
    public Ability[] getNeededAbilities() {
	return neededAbilities;
    }

    /**
     * Sets the value of abilities to that of the parameter.
     * 
     * @param abilities
     *            the abilities to set
     */
    public void setNeededAbilities(Ability[] abilities) {
	this.neededAbilities = abilities;
    }

    @Override
    public String describe() {
	StringBuilder sb = new StringBuilder("Habilité(s) requises: ");
	return sb.toString();
    }

}
