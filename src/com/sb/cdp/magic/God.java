package com.sb.cdp.magic;

public class God {
    private String name;
    private Domain[] domains;
    private String symbol;
    private String description;

    public God(String name, Domain[] domains, String symbol, String description) {
	this.name = name;
	this.domains = domains;
	this.symbol = symbol;
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
    public Domain[] getDomains() {
	return domains;
    }

    /**
     * Sets the value of domains to that of the parameter.
     * 
     * @param domains
     *            the domains to set
     */
    public void setDomains(Domain[] domains) {
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

    /**
     * Returns the symbol.
     * 
     * @return the symbol
     */
    public String getSymbol() {
	return symbol;
    }

    /**
     * Sets the value of symbol to that of the parameter.
     * 
     * @param symbol
     *            the symbol to set
     */
    public void setSymbol(String symbol) {
	this.symbol = symbol;
    }
    
    @Override
    public String toString() {
	return name;
    }
}
