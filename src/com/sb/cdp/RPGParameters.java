package com.sb.cdp;

import java.io.Serializable;

public class RPGParameters implements Serializable {
    private static final long serialVersionUID = -1617400151267455455L;
    
    private int xpPerLevel;
    private int xpPerEvent;
    private int startingAbilityPoints;
    private int abilityPointsPerLevel;
    

    /**
     * Returns the xpPerLevel.
     * @return the xpPerLevel
     */
    public int getXpPerLevel() {
        return xpPerLevel;
    }

    /**
     * Sets the value of xpPerLevel to that of the parameter.
     * @param xpPerLevel the xpPerLevel to set
     */
    public void setXpPerLevel(int xpPerLevel) {
        this.xpPerLevel = xpPerLevel;
    }

    /**
     * Returns the startingAbilityPoints.
     * @return the startingAbilityPoints
     */
    public int getStartingAbilityPoints() {
        return startingAbilityPoints;
    }

    /**
     * Sets the value of startingAbilityPoints to that of the parameter.
     * @param startingAbilityPoints the startingAbilityPoints to set
     */
    public void setStartingAbilityPoints(int startingAbilityPoints) {
        this.startingAbilityPoints = startingAbilityPoints;
    }

    /**
     * Returns the abilityPointsPerLevel.
     * @return the abilityPointsPerLevel
     */
    public int getAbilityPointsPerLevel() {
        return abilityPointsPerLevel;
    }

    /**
     * Sets the value of abilityPointsPerLevel to that of the parameter.
     * @param abilityPointsPerLevel the abilityPointsPerLevel to set
     */
    public void setAbilityPointsPerLevel(int abilityPointsPerLevel) {
        this.abilityPointsPerLevel = abilityPointsPerLevel;
    }

    /**
     * Returns the xpPerEvent.
     * @return the xpPerEvent
     */
    public int getXpPerEvent() {
        return xpPerEvent;
    }

    /**
     * Sets the value of xpPerEvent to that of the parameter.
     * @param xpPerEvent the xpPerEvent to set
     */
    public void setXpPerEvent(int xpPerEvent) {
        this.xpPerEvent = xpPerEvent;
    }

    
}
