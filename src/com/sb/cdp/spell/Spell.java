package com.sb.cdp.spell;

public class Spell<SpellType extends Spell> {
    private String name;
    private long duration;
    /**
     * The time required to cast the spell.
     * It is either the number of words to be said (mage) or the length of the prayer (priest).
     */
    private long castingTime;
    private String description;
    private float range;
    private int cost;
    private Domain<SpellType> domain;

    public Spell(String name, long duration, long castingTime, String description, float range, int cost,
	    Domain<SpellType> domain) {
	this.name = name;
	this.duration = duration;
	this.castingTime = castingTime;
	this.description = description;
	this.range = range;
	this.cost = cost;
	this.domain = domain;
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
     * Returns the duration.
     * 
     * @return the duration
     */
    public long getDuration() {
	return duration;
    }

    /**
     * Sets the value of duration to that of the parameter.
     * 
     * @param duration
     *            the duration to set
     */
    public void setDuration(long duration) {
	this.duration = duration;
    }

    /**
     * Returns the castingTime.
     * 
     * @return the castingTime
     */
    public long getCastingTime() {
	return castingTime;
    }

    /**
     * Sets the value of castingTime to that of the parameter.
     * 
     * @param castingTime
     *            the castingTime to set
     */
    public void setCastingTime(long castingTime) {
	this.castingTime = castingTime;
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
     * Returns the range.
     * 
     * @return the range
     */
    public float getRange() {
	return range;
    }

    /**
     * Sets the value of range to that of the parameter.
     * 
     * @param range
     *            the range to set
     */
    public void setRange(float range) {
	this.range = range;
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
     * Returns the domain.
     * 
     * @return the domain
     */
    public Domain<SpellType> getDomain() {
	return domain;
    }

    /**
     * Sets the value of domain to that of the parameter.
     * 
     * @param domain
     *            the domain to set
     */
    public void setDomain(Domain<SpellType> domain) {
	this.domain = domain;
    }
}
