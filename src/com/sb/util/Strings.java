package com.sb.util;

public class Strings {
    public static boolean isNumber(String text, int offset, boolean allowDecimal, boolean allowNegative) {
	char[] chars = text.toCharArray();
	boolean alreadyHasNegative = false;
	boolean alreadyHasDecimal = false;
	for (int i = 0; i < text.length(); i++) {
	    if (chars[i] == '-' && allowNegative && i == 0 && offset == 0 && !alreadyHasNegative) { // Negative
		alreadyHasNegative = true;
	    } else if (chars[i] == '.' && allowDecimal && !alreadyHasDecimal) { // Decimal
		alreadyHasDecimal = true;
	    } else if (!Character.isDigit(chars[i])) // Not a number
		return false;
	}
	return true;
    }
}
