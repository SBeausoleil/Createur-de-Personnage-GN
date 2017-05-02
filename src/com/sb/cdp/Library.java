package com.sb.cdp;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import com.sb.util.Getter;
import com.sb.util.Pair;

public class Library<V> implements Collection<V>, Comparable<Library>, Pair<String, Collection<V>>, Serializable {
    private static final long serialVersionUID = 6935193505353456112L;
    // TODO add some form of version control to allow merging of libraries and updating etc...

    private String name;
    private Collection<V> data;
    private boolean isPublic;
    private final Class<V> VALUE_TYPE;

    public Library(String name, Class<V> valueType) {
	this(name, valueType, false, new LinkedList<V>());
    }

    public Library(String name, Class<V> valueType, boolean isPublic) {
	this(name, valueType, isPublic, new LinkedList<V>());
    }
    
    public Library(String name, Class<V> valueType, boolean isPublic, Collection<V> data) {
	this.name = name;
	this.VALUE_TYPE = valueType;
	this.isPublic = isPublic;
	this.data = data;
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

    @Override
    public int compareTo(Library lib) {
	int comparison = name.compareTo(lib.name);
	if (comparison != 0)
	    return comparison;
	comparison = VALUE_TYPE.getName().compareTo(lib.getValueType().getName());
	if (comparison != 0)
	    return comparison;
	return Integer.compare(size(), lib.size());
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "Library [name=" + name + "]";
    }

    @Override
    public String getX() {
	return name;
    }

    @Override
    public void setX(String x) {
	this.name = x;
    }

    @Override
    public Collection<V> getY() {
	return this;
    }

    @Override
    public void setY(Collection<V> y) {
	throw new UnsupportedOperationException();
    }

    /**
     * Returns the vALUE_TYPE.
     * @return the vALUE_TYPE
     */
    public Class<V> getValueType() {
        return VALUE_TYPE;
    }

    /**
     * Returns the isPublic.
     * @return the isPublic
     */
    public boolean isPublic() {
        return isPublic;
    }

    /**
     * Sets the value of isPublic to that of the parameter.
     * @param isPublic the isPublic to set
     */
    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }
    
    /**
     * Returns the first object who's return value from the getter matches the searched parameter.
     * @param getter
     * @param searched
     * @return
     */
    public <T> V search(Getter<V, T> getter, T searched) {
	for (V obj : this)
	    if (getter.get(obj).equals(searched))
		return obj;
	return null;
    }

    /**
     * @param arg0
     * @return
     * @see java.util.Collection#add(java.lang.Object)
     */
    @Override
    public boolean add(V arg0) {
	return data.add(arg0);
    }

    /**
     * @param arg0
     * @return
     * @see java.util.Collection#addAll(java.util.Collection)
     */
    @Override
    public boolean addAll(Collection<? extends V> arg0) {
	return data.addAll(arg0);
    }

    /**
     * 
     * @see java.util.Collection#clear()
     */
    @Override
    public void clear() {
	data.clear();
    }

    /**
     * @param arg0
     * @return
     * @see java.util.Collection#contains(java.lang.Object)
     */
    @Override
    public boolean contains(Object arg0) {
	return data.contains(arg0);
    }

    /**
     * @param arg0
     * @return
     * @see java.util.Collection#containsAll(java.util.Collection)
     */
    @Override
    public boolean containsAll(Collection<?> arg0) {
	return data.containsAll(arg0);
    }

    /**
     * @param arg0
     * @return
     * @see java.util.Collection#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object arg0) {
	return data.equals(arg0);
    }

    /**
     * @return
     * @see java.util.Collection#hashCode()
     */
    @Override
    public int hashCode() {
	return data.hashCode();
    }

    /**
     * @return
     * @see java.util.Collection#isEmpty()
     */
    @Override
    public boolean isEmpty() {
	return data.isEmpty();
    }

    /**
     * @return
     * @see java.util.Collection#iterator()
     */
    @Override
    public Iterator<V> iterator() {
	return data.iterator();
    }

    /**
     * @param arg0
     * @return
     * @see java.util.Collection#remove(java.lang.Object)
     */
    @Override
    public boolean remove(Object arg0) {
	return data.remove(arg0);
    }

    /**
     * @param arg0
     * @return
     * @see java.util.Collection#removeAll(java.util.Collection)
     */
    @Override
    public boolean removeAll(Collection<?> arg0) {
	return data.removeAll(arg0);
    }

    /**
     * @param arg0
     * @return
     * @see java.util.Collection#retainAll(java.util.Collection)
     */
    @Override
    public boolean retainAll(Collection<?> arg0) {
	return data.retainAll(arg0);
    }

    /**
     * @return
     * @see java.util.Collection#size()
     */
    @Override
    public int size() {
	return data.size();
    }

    /**
     * @return
     * @see java.util.Collection#toArray()
     */
    @Override
    public Object[] toArray() {
	return data.toArray();
    }

    /**
     * @param arg0
     * @return
     * @see java.util.Collection#toArray(java.lang.Object[])
     */
    @Override
    public <T> T[] toArray(T[] arg0) {
	return data.toArray(arg0);
    }

    /**
     * Returns the data.
     * @return the data
     */
    public Collection<V> getData() {
        return data;
    }

    /**
     * Sets the value of data to that of the parameter.
     * @param data the data to set
     */
    public void setData(Collection<V> data) {
        this.data = data;
    }
}
