package com.sb.cdp.magic;

public class Prayer extends Magic<Prayer> {

    private God god;

    public Prayer(String name) {
	super(name);
    }

    public Prayer(String name, String duration, long castingTime, String description, String range, int cost,
	    Domain<Prayer> domain, God god) {
	super(name, 0, duration, castingTime, description, range, cost, domain);
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
