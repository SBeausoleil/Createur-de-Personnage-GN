package com.sb.idl.ability;

import com.sb.idl.PlayerCharacter;

public class OtherCondition implements Condition {

    private String description;

    public OtherCondition(String description) {
	this.description = description;
    }

    @Override
    public boolean accept(PlayerCharacter pc) {
	return true;
    }

    @Override
    public String describe() {
	return description;
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
