package com.sb.cdp;

import java.util.LinkedList;
import java.util.Set;

public interface LibraryPermissionHolder {

    public Set<Library> getAllowedLibraries();
    public void setAllowedLibraries(Set<Library> allowedLibraries);
    public void allow(Library allowedLibrary);
    
    default <E> Library<?, E>[] getAllowedLibrariesFor(Class<E> valueType) {
	LinkedList<Library<?, E>> list = new LinkedList<>();
	for (Library lib : getAllowedLibraries())
	    if (lib.getValueType() == valueType)
		list.add(lib);
	return (Library<?, E>[]) list.toArray();
    }
}
