package com.sb.idl.ability;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Deprecated
public class RawAbility implements Serializable {

    private static final long serialVersionUID = 7399192258158490588L;

    private String name;
    private int cost;
    private String classes;
    private String prerequisites;

    private String description;

    public RawAbility(String name, int cost, String classes, String prerequisites, String description) {
	this.name = name;
	this.cost = cost;
	this.classes = classes;
	this.prerequisites = prerequisites;
	this.description = description;
    }

    /**
     * Returns the classes.
     * 
     * @return the classes
     */
    public String getClasses() {
	return classes;
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
     * Returns the description.
     * 
     * @return the description
     */
    public String getDescription() {
	return description;
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
     * Returns the prerequisites.
     * 
     * @return the prerequisites
     */
    public String getPrerequisites() {
	return prerequisites;
    }

    /**
     * Sets the value of classes to that of the parameter.
     * 
     * @param classes
     *            the classes to set
     */
    public void setClasses(String classes) {
	this.classes = classes;
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
     * Sets the value of description to that of the parameter.
     * 
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
	this.description = description;
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
     * Sets the value of prerequisites to that of the parameter.
     * 
     * @param prerequisites
     *            the prerequisites to set
     */
    public void setPrerequisites(String prerequisites) {
	this.prerequisites = prerequisites;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "RawAbility [name=" + name + ", cost=" + cost + ", classes=" + classes + ", prerequisites="
		+ prerequisites + ", description=" + description + "]";
    }
}
