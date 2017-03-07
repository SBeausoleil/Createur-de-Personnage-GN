package com.sb.util;

/**
 * A functional interface representing a getter within an object.
 * 
 * @author Samuel Beausoleil
 *
 * @param <E> The object the method is called on
 * @param <T> The return type of the getter
 */
//@FunctionalInterface
public interface Getter<E, T> {
    public T get(E obj);
}
