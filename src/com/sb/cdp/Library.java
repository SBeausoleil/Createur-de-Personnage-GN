package com.sb.cdp;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Library<T, E> {

    // TODO add some form of version control to allow merging of libraries and updating etc...
    
    private String name;
    private TreeMap<T, E> data;
    
    public Library(String name) {
	this.name = name;
	data = new TreeMap<>();
    }

    /**
     * Returns the name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of name to that of the parameter.
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the data.
     * @return the data
     */
    public Map<T, E> getData() {
        return data;
    }

    /**
     * Sets the value of data to that of the parameter.
     * @param data the data to set
     */
    public void setData(TreeMap<T, E> data) {
        this.data = data;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "Library [name=" + name + ", data=" + data + "]";
    }

    // !!!--- Delegate methods to data after this point ---!!! //
    
    /**
     * @param key
     * @return
     * @see java.util.TreeMap#ceilingEntry(java.lang.Object)
     */
    public Entry<T, E> ceilingEntry(T key) {
	return data.ceilingEntry(key);
    }

    /**
     * @param key
     * @return
     * @see java.util.TreeMap#ceilingKey(java.lang.Object)
     */
    public T ceilingKey(T key) {
	return data.ceilingKey(key);
    }

    /**
     * 
     * @see java.util.TreeMap#clear()
     */
    public void clear() {
	data.clear();
    }

    /**
     * @return
     * @see java.util.TreeMap#clone()
     */
    @Override
    public Object clone() {
	return data.clone();
    }

    /**
     * @return
     * @see java.util.TreeMap#comparator()
     */
    public Comparator<? super T> comparator() {
	return data.comparator();
    }

    /**
     * @param key
     * @param remappingFunction
     * @return
     * @see java.util.Map#compute(java.lang.Object, java.util.function.BiFunction)
     */
    public E compute(T key, BiFunction<? super T, ? super E, ? extends E> remappingFunction) {
	return data.compute(key, remappingFunction);
    }

    /**
     * @param key
     * @param mappingFunction
     * @return
     * @see java.util.Map#computeIfAbsent(java.lang.Object, java.util.function.Function)
     */
    public E computeIfAbsent(T key, Function<? super T, ? extends E> mappingFunction) {
	return data.computeIfAbsent(key, mappingFunction);
    }

    /**
     * @param key
     * @param remappingFunction
     * @return
     * @see java.util.Map#computeIfPresent(java.lang.Object, java.util.function.BiFunction)
     */
    public E computeIfPresent(T key, BiFunction<? super T, ? super E, ? extends E> remappingFunction) {
	return data.computeIfPresent(key, remappingFunction);
    }

    /**
     * @param key
     * @return
     * @see java.util.TreeMap#containsKey(java.lang.Object)
     */
    public boolean containsKey(Object key) {
	return data.containsKey(key);
    }

    /**
     * @param value
     * @return
     * @see java.util.TreeMap#containsValue(java.lang.Object)
     */
    public boolean containsValue(Object value) {
	return data.containsValue(value);
    }

    /**
     * @return
     * @see java.util.TreeMap#descendingKeySet()
     */
    public NavigableSet<T> descendingKeySet() {
	return data.descendingKeySet();
    }

    /**
     * @return
     * @see java.util.TreeMap#descendingMap()
     */
    public NavigableMap<T, E> descendingMap() {
	return data.descendingMap();
    }

    /**
     * @return
     * @see java.util.TreeMap#entrySet()
     */
    public Set<Entry<T, E>> entrySet() {
	return data.entrySet();
    }

    /**
     * @param arg0
     * @return
     * @see java.util.AbstractMap#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object arg0) {
	return data.equals(arg0);
    }

    /**
     * @return
     * @see java.util.TreeMap#firstEntry()
     */
    public Entry<T, E> firstEntry() {
	return data.firstEntry();
    }

    /**
     * @return
     * @see java.util.TreeMap#firstKey()
     */
    public T firstKey() {
	return data.firstKey();
    }

    /**
     * @param key
     * @return
     * @see java.util.TreeMap#floorEntry(java.lang.Object)
     */
    public Entry<T, E> floorEntry(T key) {
	return data.floorEntry(key);
    }

    /**
     * @param key
     * @return
     * @see java.util.TreeMap#floorKey(java.lang.Object)
     */
    public T floorKey(T key) {
	return data.floorKey(key);
    }

    /**
     * @param action
     * @see java.util.TreeMap#forEach(java.util.function.BiConsumer)
     */
    public void forEach(BiConsumer<? super T, ? super E> action) {
	data.forEach(action);
    }

    /**
     * @param key
     * @return
     * @see java.util.TreeMap#get(java.lang.Object)
     */
    public E get(Object key) {
	return data.get(key);
    }

    /**
     * @param key
     * @param defaultValue
     * @return
     * @see java.util.Map#getOrDefault(java.lang.Object, java.lang.Object)
     */
    public E getOrDefault(Object key, E defaultValue) {
	return data.getOrDefault(key, defaultValue);
    }

    /**
     * @return
     * @see java.util.AbstractMap#hashCode()
     */
    @Override
    public int hashCode() {
	return data.hashCode();
    }

    /**
     * @param toKey
     * @param inclusive
     * @return
     * @see java.util.TreeMap#headMap(java.lang.Object, boolean)
     */
    public NavigableMap<T, E> headMap(T toKey, boolean inclusive) {
	return data.headMap(toKey, inclusive);
    }

    /**
     * @param toKey
     * @return
     * @see java.util.TreeMap#headMap(java.lang.Object)
     */
    public SortedMap<T, E> headMap(T toKey) {
	return data.headMap(toKey);
    }

    /**
     * @param key
     * @return
     * @see java.util.TreeMap#higherEntry(java.lang.Object)
     */
    public Entry<T, E> higherEntry(T key) {
	return data.higherEntry(key);
    }

    /**
     * @param key
     * @return
     * @see java.util.TreeMap#higherKey(java.lang.Object)
     */
    public T higherKey(T key) {
	return data.higherKey(key);
    }

    /**
     * @return
     * @see java.util.AbstractMap#isEmpty()
     */
    public boolean isEmpty() {
	return data.isEmpty();
    }

    /**
     * @return
     * @see java.util.TreeMap#keySet()
     */
    public Set<T> keySet() {
	return data.keySet();
    }

    /**
     * @return
     * @see java.util.TreeMap#lastEntry()
     */
    public Entry<T, E> lastEntry() {
	return data.lastEntry();
    }

    /**
     * @return
     * @see java.util.TreeMap#lastKey()
     */
    public T lastKey() {
	return data.lastKey();
    }

    /**
     * @param key
     * @return
     * @see java.util.TreeMap#lowerEntry(java.lang.Object)
     */
    public Entry<T, E> lowerEntry(T key) {
	return data.lowerEntry(key);
    }

    /**
     * @param key
     * @return
     * @see java.util.TreeMap#lowerKey(java.lang.Object)
     */
    public T lowerKey(T key) {
	return data.lowerKey(key);
    }

    /**
     * @param key
     * @param value
     * @param remappingFunction
     * @return
     * @see java.util.Map#merge(java.lang.Object, java.lang.Object, java.util.function.BiFunction)
     */
    public E merge(T key, E value, BiFunction<? super E, ? super E, ? extends E> remappingFunction) {
	return data.merge(key, value, remappingFunction);
    }

    /**
     * @return
     * @see java.util.TreeMap#navigableKeySet()
     */
    public NavigableSet<T> navigableKeySet() {
	return data.navigableKeySet();
    }

    /**
     * @return
     * @see java.util.TreeMap#pollFirstEntry()
     */
    public Entry<T, E> pollFirstEntry() {
	return data.pollFirstEntry();
    }

    /**
     * @return
     * @see java.util.TreeMap#pollLastEntry()
     */
    public Entry<T, E> pollLastEntry() {
	return data.pollLastEntry();
    }

    /**
     * @param key
     * @param value
     * @return
     * @see java.util.TreeMap#put(java.lang.Object, java.lang.Object)
     */
    public E put(T key, E value) {
	return data.put(key, value);
    }

    /**
     * @param map
     * @see java.util.TreeMap#putAll(java.util.Map)
     */
    public void putAll(Map<? extends T, ? extends E> map) {
	data.putAll(map);
    }

    /**
     * @param key
     * @param value
     * @return
     * @see java.util.Map#putIfAbsent(java.lang.Object, java.lang.Object)
     */
    public E putIfAbsent(T key, E value) {
	return data.putIfAbsent(key, value);
    }

    /**
     * @param key
     * @param value
     * @return
     * @see java.util.Map#remove(java.lang.Object, java.lang.Object)
     */
    public boolean remove(Object key, Object value) {
	return data.remove(key, value);
    }

    /**
     * @param key
     * @return
     * @see java.util.TreeMap#remove(java.lang.Object)
     */
    public E remove(Object key) {
	return data.remove(key);
    }

    /**
     * @param key
     * @param oldValue
     * @param newValue
     * @return
     * @see java.util.TreeMap#replace(java.lang.Object, java.lang.Object, java.lang.Object)
     */
    public boolean replace(T key, E oldValue, E newValue) {
	return data.replace(key, oldValue, newValue);
    }

    /**
     * @param key
     * @param value
     * @return
     * @see java.util.TreeMap#replace(java.lang.Object, java.lang.Object)
     */
    public E replace(T key, E value) {
	return data.replace(key, value);
    }

    /**
     * @param function
     * @see java.util.TreeMap#replaceAll(java.util.function.BiFunction)
     */
    public void replaceAll(BiFunction<? super T, ? super E, ? extends E> function) {
	data.replaceAll(function);
    }

    /**
     * @return
     * @see java.util.TreeMap#size()
     */
    public int size() {
	return data.size();
    }

    /**
     * @param fromKey
     * @param fromInclusive
     * @param toKey
     * @param toInclusive
     * @return
     * @see java.util.TreeMap#subMap(java.lang.Object, boolean, java.lang.Object, boolean)
     */
    public NavigableMap<T, E> subMap(T fromKey, boolean fromInclusive, T toKey, boolean toInclusive) {
	return data.subMap(fromKey, fromInclusive, toKey, toInclusive);
    }

    /**
     * @param fromKey
     * @param toKey
     * @return
     * @see java.util.TreeMap#subMap(java.lang.Object, java.lang.Object)
     */
    public SortedMap<T, E> subMap(T fromKey, T toKey) {
	return data.subMap(fromKey, toKey);
    }

    /**
     * @param fromKey
     * @param inclusive
     * @return
     * @see java.util.TreeMap#tailMap(java.lang.Object, boolean)
     */
    public NavigableMap<T, E> tailMap(T fromKey, boolean inclusive) {
	return data.tailMap(fromKey, inclusive);
    }

    /**
     * @param fromKey
     * @return
     * @see java.util.TreeMap#tailMap(java.lang.Object)
     */
    public SortedMap<T, E> tailMap(T fromKey) {
	return data.tailMap(fromKey);
    }

    /**
     * @return
     * @see java.util.TreeMap#values()
     */
    public Collection<E> values() {
	return data.values();
    }

}
