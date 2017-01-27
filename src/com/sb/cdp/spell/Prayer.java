package com.sb.cdp.spell;

public class Prayer extends Spell<Prayer> {

    private God god;

    

    public Prayer(String name, long duration, long castingTime, String description, float range, int cost,
	    Domain<Prayer> domain, God god) {
	super(name, duration, castingTime, description, range, cost, domain);
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
