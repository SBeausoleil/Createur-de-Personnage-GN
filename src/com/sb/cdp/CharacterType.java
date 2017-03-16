package com.sb.cdp;

public class CharacterType {

    public static enum Classification {
	RACE, CLASS;
    }

    private final String name;
    private String description;
    private Classification classification;

    protected CharacterType(String name, Classification classification) {
	this.name = name;
	this.classification = classification;
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
     * Returns the type.
     * 
     * @return the type
     */
    public Classification getClassification() {
	return classification;
    }

    /**
     * Sets the value of type to that of the parameter.
     * 
     * @param type
     *            the type to set
     */
    public void setClassification(Classification type) {
	this.classification = type;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return name;
    }
}
