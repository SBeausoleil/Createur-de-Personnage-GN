package com.sb.idl.spell;

public class God {
    private String name;
    private Domain<Prayer>[] domains;
    private String description;

    public God(String name, Domain<Prayer>[] domains, String description) {
	this.name = name;
	this.domains = domains;
	this.description = description;
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
     * Returns the domains.
     * 
     * @return the domains
     */
    public Domain<Prayer>[] getDomains() {
	return domains;
    }

    /**
     * Sets the value of domains to that of the parameter.
     * 
     * @param domains
     *            the domains to set
     */
    public void setDomains(Domain<Prayer>[] domains) {
	this.domains = domains;
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
}
