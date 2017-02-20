package com.sb.cdp.io;

import java.io.IOException;
import java.io.Writer;

import com.sb.cdp.ability.Ability;

public class AbilityIO {

    public static int minDots = 2;

    public static void printInline(Iterable<Ability> abilities, Writer out) throws IOException {
	int neededDots = getHighestNameLength(abilities) + minDots;
	for (Ability ability : abilities) {
	    out.write(ability.getName());
	    for (int dots = ability.getName().length(); dots < neededDots; dots++)
		out.write('.');
	    out.write(" Coût: " + ability.getCost());
	    out.write(" Classe(s): ");
	    for (int i = 0; i < ability.getClasses().length; i++) {
		out.write(ability.getClasses()[i].getName());
		if ((i + 1) < ability.getClasses().length)
		    out.write(", ");
	    }
	    out.write(" Prérequis: ");
	}
    }

    private static int getHighestNameLength(Iterable<Ability> abilities) {
	int highestLength = 0;
	for (Ability ability : abilities)
	    if (highestLength < ability.getName().length())
		highestLength = ability.getName().length();
	return highestLength;
    }
}
