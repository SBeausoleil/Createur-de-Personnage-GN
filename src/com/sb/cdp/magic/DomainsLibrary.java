package com.sb.cdp.magic;

import java.util.Collection;

import com.sb.cdp.Library;

/**
 * A library used to store Domain objects of the same magic type.
 * 
 * @author Samuel Beausoleil
 *
 */
public class DomainsLibrary extends Library<Domain> {
    private static final long serialVersionUID = -6971690397040689024L;
    
    private String magicType;

    public DomainsLibrary(String name, String magicType) {
	super(name, Domain.class);
	this.magicType = magicType;
    }

    /**
     * Returns the magicType.
     * 
     * @return the magicType
     */
    public String getMagicType() {
	return magicType;
    }

    /**
     * Sets the value of magicType to that of the parameter.
     * 
     * @param magicType
     *            the magicType to set
     */
    public void setMagicType(String magicType) {
	this.magicType = magicType;
    }

    @Override
    public boolean add(Domain value) {
	checkType(value);
	return super.add(value);
    }

    @Override
    public boolean addAll(Collection<? extends Domain> coll) {
	for (Domain dom : coll) {
	    checkType(dom);
	    super.add(dom);
	}
	return true;
    }

    private void checkType(Domain value) {
	if (magicType != null && !value.getMagicType().equals(magicType))
	    throw new IllegalArgumentException("The received domain's magic type (" + value.getMagicType()
		    + ") does not match the accepted type (" + magicType + ")");
    }
}
