package com.sb.util;

import com.sb.cdp.ability.Condition;
import com.sb.cdp.ability.OtherCondition;

public final class ArrayUtil {
    private ArrayUtil() {}
    
    public static String join(Object[] objs, CharSequence separator) {
	StringBuilder sb = new StringBuilder();
	for (int i = 0; i < objs.length; i++) {
	    sb.append(objs[i].toString());
	    if (i + 1 < objs.length)
		sb.append(separator);
	}
	return sb.toString();
    }
    
    public static <E> String join(E[] objs, Getter<E, CharSequence> getter, CharSequence separator) {
	StringBuilder sb = new StringBuilder();
	for (int i = 0; i < objs.length; i++) {
	    sb.append(getter.get(objs[i]));
	    if (i + 1 < objs.length)
		sb.append(separator);
	}
	return sb.toString();
    }
    
    // TEST
    public static void main(String[] args) {
	Condition[] conditions = new Condition[] {new OtherCondition("Hello condition"), new OtherCondition("Test condition")};
	String str = join(conditions, com.sb.cdp.ability.Condition::describe, ", ");
	System.out.println(str);
    }
}
