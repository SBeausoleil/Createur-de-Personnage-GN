package com.sb.cdp.magic;

public class Magic implements Comparable<Magic> {
    public static final float TOUCH = 0f;
    public static final float SELF = -1f;

    private String name;
    private int level;
    private String duration;
    private String casting;
    private String description;
    private String range;
    private String cost;
    private Domain domain;

    public Magic(String name) {
	this(name, 0, null, null, null, null, null, null);
    }

    public Magic(String name, int level, String duration, String casting, String description, String range,
	    String cost, Domain domain) {
	this.name = name;
	this.duration = duration;
	this.casting = casting;
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
    public String getDuration() {
	return duration;
    }

    /**
     * Sets the value of duration to that of the parameter.
     * 
     * @param duration
     *            the duration to set
     */
    public void setDuration(String duration) {
	this.duration = duration;
    }

    /**
     * Returns the castingTime.
     * 
     * @return the castingTime
     */
    public String getCasting() {
	return casting;
    }

    /**
     * Sets the value of castingTime to that of the parameter.
     * 
     * @param castingTime
     *            the castingTime to set
     */
    public void setCasting(String castingTime) {
	this.casting = castingTime;
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
    public String getRange() {
	return range;
    }

    /**
     * Sets the value of range to that of the parameter.
     * 
     * @param range
     *            the range to set
     */
    public void setRange(String range) {
	this.range = range;
    }

    /**
     * Returns the cost.
     * 
     * @return the cost
     */
    public String getCost() {
	return cost;
    }

    /**
     * Sets the value of cost to that of the parameter.
     * 
     * @param cost
     *            the cost to set
     */
    public void setCost(String cost) {
	this.cost = cost;
    }

    /**
     * Returns the domain.
     * 
     * @return the domain
     */
    public Domain getDomain() {
	return domain;
    }

    /**
     * Sets the value of domain to that of the parameter.
     * 
     * @param domain
     *            the domain to set
     */
    public void setDomain(Domain domain) {
	this.domain = domain;
    }

    /**
     * Returns the level.
     * 
     * @return the level
     */
    public int getLevel() {
	return level;
    }

    /**
     * Sets the value of level to that of the parameter.
     * 
     * @param level
     *            the level to set
     */
    public void setLevel(int level) {
	this.level = level;
    }

    public String getType() {
	if (domain != null)
	    return domain.getMagicType();
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "Magic [name=" + name + ", level=" + level + ", duration=" + duration + ", castingTime=" + casting
		+ ", description=" + description + ", range=" + range + ", cost=" + cost + ", domain="
		+ domain.getName() + "]";
    }

    @Override
    public int compareTo(Magic m) {
	int compareResult = Integer.compare(level, m.getLevel());
	if (compareResult == 0)
	    compareResult = name.compareTo(m.getName());
	return compareResult;
    }
}
