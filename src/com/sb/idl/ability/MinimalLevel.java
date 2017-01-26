package com.sb.idl.ability;

import com.sb.idl.PlayerCharacter;

public class MinimalLevel implements Condition{
    private int requiredLevel;

    public MinimalLevel(int requiredLevel) {
	this.requiredLevel = requiredLevel;
    }

    /**
     * Returns the requiredLevel.
     * @return the requiredLevel
     */
    public int getRequiredLevel() {
        return requiredLevel;
    }

    /**
     * Sets the value of requiredLevel to that of the parameter.
     * @param requiredLevel the requiredLevel to set
     */
    public void setRequiredLevel(int requiredLevel) {
        this.requiredLevel = requiredLevel;
    }

    @Override
    public boolean accept(PlayerCharacter pc) {
	return pc.getLevel() >= requiredLevel;
    }

    @Override
    public String describe() {
	return "Niveau minimum: " + requiredLevel;
    }
    
}
