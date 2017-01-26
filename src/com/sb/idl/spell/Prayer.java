package com.sb.idl.spell;

public class Prayer extends Spell<Prayer> {

    private God god;

    public Prayer(God god) {
	this.god = god;
    }

    /**
     * Returns the god.
     * @return the god
     */
    public God getGod() {
        return god;
    }

    /**
     * Sets the value of god to that of the parameter.
     * @param god the god to set
     */
    public void setGod(God god) {
        this.god = god;
    }
    
}
