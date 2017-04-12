package com.sb.cdp;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

import com.sb.util.Pair;

public class Library<K, V> implements Map<K, V>, Comparable<Library>, Pair<String, Collection<V>> {

    // TODO add some form of version control to allow merging of libraries and updating etc...

    private String name;
    private TreeMap<K, V> data;
    private boolean isPublic;
    private final Class<K> KEY_TYPE;
    private final Class<V> VALUE_TYPE;

    public Library(String name, Class<K> keyType, Class<V> valueType) {
	this.name = name;
	this.KEY_TYPE = keyType;
	this.VALUE_TYPE = valueType;
	data = new TreeMap<>();
    }

    public Library(String name, Class<K> keyType, Class<V> valueType, boolean isPublic) {
	this.name = name;
	this.KEY_TYPE = keyType;
	this.VALUE_TYPE = valueType;
	this.isPublic = isPublic;
	data = new TreeMap<>();
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
     * Returns the data.
     * 
     * @return the data
     */
    public Map<K, V> getData() {
	return data;
    }

    /**
     * Sets the value of data to that of the parameter.
     * 
     * @param data
     *            the data to set
     */
    public void setData(TreeMap<K, V> data) {
	this.data = data;
    }

    /**
     * @return
     * @see java.util.TreeMap#values()
     */
    @Override
    public Collection<V> values() {
	return data.values();
    }

    /**
     * Returns the isPublic.
     * 
     * @return the isPublic
     */
    public boolean isPublic() {
	return isPublic;
    }

    /**
     * Sets the value of isPublic to that of the parameter.
     * 
     * @param isPublic
     *            the isPublic to set
     */
    public void setPublic(boolean isPublic) {
	this.isPublic = isPublic;
    }

    /**
     * Returns the VALUE_TYPE.
     * 
     * @return the VALUE_TYPE
     */
    public Class<V> getValueType() {
	return VALUE_TYPE;
    }

    public Class<K> getKeyType() {
	return KEY_TYPE;
    }

    @Override
    public int compareTo(Library lib) {
	int comparison = name.compareTo(lib.name);
	if (comparison != 0)
	    return comparison;
	comparison = VALUE_TYPE.getName().compareTo(lib.getValueType().getName());
	if (comparison != 0)
	    return comparison;
	return Integer.compare(data.size(), lib.data.size());
    }

    /*
     * (non-Javadoc)
     * 
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
    public Entry<K, V> ceilingEntry(K key) {
	return data.ceilingEntry(key);
    }

    /**
     * @param key
     * @return
     * @see java.util.TreeMap#ceilingKey(java.lang.Object)
     */
    public K ceilingKey(K key) {
	return data.ceilingKey(key);
    }

    /**
     * @see java.util.TreeMap#clear()
     */
    @Override
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
    public Comparator<? super K> comparator() {
	return data.comparator();
    }

    /**
     * @param key
     * @param remappingFunction
     * @return
     * @see java.util.Map#compute(java.lang.Object, java.util.function.BiFunction)
     */
    @Override
    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
	return data.compute(key, remappingFunction);
    }

    /**
     * @param key
     * @param mappingFunction
     * @return
     * @see java.util.Map#computeIfAbsent(java.lang.Object, java.util.function.Function)
     */
    @Override
    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
	return data.computeIfAbsent(key, mappingFunction);
    }

    /**
     * @param key
     * @param remappingFunction
     * @return
     * @see java.util.Map#computeIfPresent(java.lang.Object, java.util.function.BiFunction)
     */
    @Override
    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
	return data.computeIfPresent(key, remappingFunction);
    }

    /**
     * @param key
     * @return
     * @see java.util.TreeMap#containsKey(java.lang.Object)
     */
    @Override
    public boolean containsKey(Object key) {
	return data.containsKey(key);
    }

    /**
     * @param value
     * @return
     * @see java.util.TreeMap#containsValue(java.lang.Object)
     */
    @Override
    public boolean containsValue(Object value) {
	return data.containsValue(value);
    }

    /**
     * @return
     * @see java.util.TreeMap#descendingKeySet()
     */
    public NavigableSet<K> descendingKeySet() {
	return data.descendingKeySet();
    }

    /**
     * @return
     * @see java.util.TreeMap#descendingMap()
     */
    public NavigableMap<K, V> descendingMap() {
	return data.descendingMap();
    }

    /**
     * @return
     * @see java.util.TreeMap#entrySet()
     */
    @Override
    public Set<Entry<K, V>> entrySet() {
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
    public Entry<K, V> firstEntry() {
	return data.firstEntry();
    }

    /**
     * @return
     * @see java.util.TreeMap#firstKey()
     */
    public K firstKey() {
	return data.firstKey();
    }

    /**
     * @param key
     * @return
     * @see java.util.TreeMap#floorEntry(java.lang.Object)
     */
    public Entry<K, V> floorEntry(K key) {
	return data.floorEntry(key);
    }

    /**
     * @param key
     * @return
     * @see java.util.TreeMap#floorKey(java.lang.Object)
     */
    public K floorKey(K key) {
	return data.floorKey(key);
    }

    /**
     * @param action
     * @see java.util.TreeMap#forEach(java.util.function.BiConsumer)
     */
    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
	data.forEach(action);
    }

    /**
     * @param key
     * @return
     * @see java.util.TreeMap#get(java.lang.Object)
     */
    @Override
    public V get(Object key) {
	return data.get(key);
    }

    /**
     * @param key
     * @param defaultValue
     * @return
     * @see java.util.Map#getOrDefault(java.lang.Object, java.lang.Object)
     */
    @Override
    public V getOrDefault(Object key, V defaultValue) {
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
    public NavigableMap<K, V> headMap(K toKey, boolean inclusive) {
	return data.headMap(toKey, inclusive);
    }

    /**
     * @param toKey
     * @return
     * @see java.util.TreeMap#headMap(java.lang.Object)
     */
    public SortedMap<K, V> headMap(K toKey) {
	return data.headMap(toKey);
    }

    /**
     * @param key
     * @return
     * @see java.util.TreeMap#higherEntry(java.lang.Object)
     */
    public Entry<K, V> higherEntry(K key) {
	return data.higherEntry(key);
    }

    /**
     * @param key
     * @return
     * @see java.util.TreeMap#higherKey(java.lang.Object)
     */
    public K higherKey(K key) {
	return data.higherKey(key);
    }

    /**
     * @return
     * @see java.util.AbstractMap#isEmpty()
     */
    @Override
    public boolean isEmpty() {
	return data.isEmpty();
    }

    /**
     * @return
     * @see java.util.TreeMap#keySet()
     */
    @Override
    public Set<K> keySet() {
	return data.keySet();
    }

    /**
     * @return
     * @see java.util.TreeMap#lastEntry()
     */
    public Entry<K, V> lastEntry() {
	return data.lastEntry();
    }

    /**
     * @return
     * @see java.util.TreeMap#lastKey()
     */
    public K lastKey() {
	return data.lastKey();
    }

    /**
     * @param key
     * @return
     * @see java.util.TreeMap#lowerEntry(java.lang.Object)
     */
    public Entry<K, V> lowerEntry(K key) {
	return data.lowerEntry(key);
    }

    /**
     * @param key
     * @return
     * @see java.util.TreeMap#lowerKey(java.lang.Object)
     */
    public K lowerKey(K key) {
	return data.lowerKey(key);
    }

    /**
     * @param key
     * @param value
     * @param remappingFunction
     * @return
     * @see java.util.Map#merge(java.lang.Object, java.lang.Object, java.util.function.BiFunction)
     */
    @Override
    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
	return data.merge(key, value, remappingFunction);
    }

    /**
     * @return
     * @see java.util.TreeMap#navigableKeySet()
     */
    public NavigableSet<K> navigableKeySet() {
	return data.navigableKeySet();
    }

    /**
     * @return
     * @see java.util.TreeMap#pollFirstEntry()
     */
    public Entry<K, V> pollFirstEntry() {
	return data.pollFirstEntry();
    }

    /**
     * @return
     * @see java.util.TreeMap#pollLastEntry()
     */
    public Entry<K, V> pollLastEntry() {
	return data.pollLastEntry();
    }

    /**
     * @param key
     * @param value
     * @return
     * @see java.util.TreeMap#put(java.lang.Object, java.lang.Object)
     */
    @Override
    public V put(K key, V value) {
	return data.put(key, value);
    }

    /**
     * @param map
     * @see java.util.TreeMap#putAll(java.util.Map)
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
	data.putAll(map);
    }

    /**
     * @param key
     * @param value
     * @return
     * @see java.util.Map#putIfAbsent(java.lang.Object, java.lang.Object)
     */
    @Override
    public V putIfAbsent(K key, V value) {
	return data.putIfAbsent(key, value);
    }

    /**
     * @param key
     * @param value
     * @return
     * @see java.util.Map#remove(java.lang.Object, java.lang.Object)
     */
    @Override
    public boolean remove(Object key, Object value) {
	return data.remove(key, value);
    }

    /**
     * @param key
     * @return
     * @see java.util.TreeMap#remove(java.lang.Object)
     */
    @Override
    public V remove(Object key) {
	return data.remove(key);
    }

    /**
     * @param key
     * @param oldValue
     * @param newValue
     * @return
     * @see java.util.TreeMap#replace(java.lang.Object, java.lang.Object, java.lang.Object)
     */
    @Override
    public boolean replace(K key, V oldValue, V newValue) {
	return data.replace(key, oldValue, newValue);
    }

    /**
     * @param key
     * @param value
     * @return
     * @see java.util.TreeMap#replace(java.lang.Object, java.lang.Object)
     */
    @Override
    public V replace(K key, V value) {
	return data.replace(key, value);
    }

    /**
     * @param function
     * @see java.util.TreeMap#replaceAll(java.util.function.BiFunction)
     */
    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
	data.replaceAll(function);
    }

    /**
     * @return
     * @see java.util.TreeMap#size()
     */
    @Override
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
    public NavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
	return data.subMap(fromKey, fromInclusive, toKey, toInclusive);
    }

    /**
     * @param fromKey
     * @param toKey
     * @return
     * @see java.util.TreeMap#subMap(java.lang.Object, java.lang.Object)
     */
    public SortedMap<K, V> subMap(K fromKey, K toKey) {
	return data.subMap(fromKey, toKey);
    }

    /**
     * @param fromKey
     * @param inclusive
     * @return
     * @see java.util.TreeMap#tailMap(java.lang.Object, boolean)
     */
    public NavigableMap<K, V> tailMap(K fromKey, boolean inclusive) {
	return data.tailMap(fromKey, inclusive);
    }

    /**
     * @param fromKey
     * @return
     * @see java.util.TreeMap#tailMap(java.lang.Object)
     */
    public SortedMap<K, V> tailMap(K fromKey) {
	return data.tailMap(fromKey);
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
	return data.values();
    }

    @Override
    public void setY(Collection<V> y) {
	throw new UnsupportedOperationException();
    }
}
