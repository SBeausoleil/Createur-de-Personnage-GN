package com.sb.util;

import java.util.ArrayList;
import java.util.Arrays;

public final class ClassUtil {

    private ClassUtil() {}

    /**
     * Checks if a given class is an instance or a subclass of given one.
     * 
     * @param type
     *            the class to check as a subclass
     * @param superClass
     *            the class to check as the superclass
     * @return true if the given type is an instance or a subclass of the given superclass
     */
    public static boolean instanceOf(Class<?> type, Class<?> superClass) {
	while (type != null) {
	    if (type == superClass)
		return true;
	    // Check interfaces
	    for (Class implementedInterface : type.getInterfaces())
		if (implementedInterface == superClass)
		    return true;
	    // Assign the superclass to the type variable
	    type = type.getSuperclass();
	}
	return false;
    }

    /**
     * Checks if a given object is an instance or a subbclass of the given class.
     * @param obj
     * @param superClass
     * @return true if the given object is an instance or a subclass of the given superclass
     */
    public static boolean instanceOf(Object obj, Class<?> superClass) {
	return instanceOf(obj.getClass(), superClass);
    }
    
    /**
     * Returns an array containing the instances of the given class.
     * @param objects the array of instances that will be checked for instances
     * @param clazz
     * @return an array containing the instances of clazz.
     */
    public static <E> E[] keep(Object[] objects, Class<E> clazz) {
	ArrayList<E> list = new ArrayList<>();
	for (Object obj : objects)
	    if (instanceOf(obj, clazz))
		list.add((E) obj);
	E[] result = (E[]) list.toArray();
	return result;
    }
    
    /**
     * Returns an array without the instances of the given class
     * @param objects the array of instances that will be filtered for instances of clazz
     * @param clazz the class to remove from the array
     * @return an array without any instances of clazz.
     */
    public static Object[] filter(Object[] objects, Class<?> clazz) {
	ArrayList<Object> list = new ArrayList<>();
	for (Object obj : objects)
	    if (!instanceOf(obj, clazz))
		list.add(obj);
	return list.toArray();
    }
    
    public static void main(String[] args) {
	System.out.println(instanceOf(Integer.class, Integer.class));
	System.out.println(instanceOf(Integer.class, Number.class));
	
	
	Number[] numbers = new Number[] { new Integer(0), new Double(0), new Integer(2) };
	System.out.println(Arrays.toString(keep(numbers, Integer.class)));
    }
}
