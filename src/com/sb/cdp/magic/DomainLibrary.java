package com.sb.cdp.magic;

import java.util.Map;
import java.util.function.BiFunction;

import com.sb.cdp.Library;

public class DomainLibrary extends Library<String, Domain> {

    private String magicType;

    public DomainLibrary(String name, String magicType) {
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
    public Domain put(String key, Domain value) {
	checkType(value);
	return super.put(key, value);
    }

    @Override
    public void putAll(Map<? extends String, ? extends Domain> map) {
	for (Entry<? extends String, ? extends Domain> entry : map.entrySet())
	    put(entry.getKey(), entry.getValue());
    }

    @Override
    public Domain putIfAbsent(String key, Domain value) {
	checkType(value);
	return super.putIfAbsent(key, value);
    }

    @Override
    public boolean replace(String key, Domain oldValue, Domain newValue) {
	checkType(newValue);
	return super.replace(key, oldValue, newValue);
    }

    @Override
    public Domain replace(String key, Domain value) {
	checkType(value);
	return super.replace(key, value);
    }

    @Override
    public void replaceAll(BiFunction<? super String, ? super Domain, ? extends Domain> function) {
	for (Map.Entry<String, Domain> entry : getData().entrySet()) {
	    Domain newValue = function.apply(entry.getKey(), entry.getValue());
	    checkType(newValue);
	    entry.setValue(newValue);
	}
    }

    private void checkType(Domain value) {
	if (magicType != null && !value.getMagicType().equals(magicType))
	    throw new IllegalArgumentException("The received domain's magic type (" + value.getMagicType()
		    + ") does not match the accepted type (" + magicType + ")");
    }
}
