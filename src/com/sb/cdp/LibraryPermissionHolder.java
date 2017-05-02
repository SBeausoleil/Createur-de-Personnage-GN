package com.sb.cdp;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Set;

import com.sb.util.Pair;

public interface LibraryPermissionHolder {

    public Set<Library> getAllowedLibraries();

    public void setAllowedLibraries(Set<Library> allowedLibraries);

    public void allow(Library allowedLibrary);

    default <V> Pair<String, Collection<V>>[] getAllowedLibrariesFor(Class<V> valueType) {
	LinkedList<Pair<String, Collection<V>>> list = new LinkedList<>();
	for (Library lib : getAllowedLibraries())
	    if (lib.getValueType() == valueType)
		list.add(lib);
	return list.toArray(new Pair[list.size()]);
    }
}
