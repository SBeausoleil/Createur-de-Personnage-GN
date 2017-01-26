package com.sb.idl.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import com.sb.idl.ability.AbilityParser;
import com.sb.idl.ability.RawAbility;

public class RawAbilities {

    public static int additionalDots = 4;

    public static void printInline(RawAbility[] abilities, BufferedWriter out) throws IOException {
	int neededDots = getHighestNameLength(abilities) + additionalDots;
	for (RawAbility ability : abilities) {
	    out.write(ability.getName());
	    for (int dots = ability.getName().length(); dots < neededDots; dots++)
		out.write('.');
	    out.write(" Coût: ");
	    out.write(Integer.toString(ability.getCost()));
	    out.write(" Classes: " + ability.getClasses());
	    out.write(" Prérequis: " + ability.getPrerequisites());
	    out.write(" Description: " + ability.getDescription());
	    out.newLine();
	}
    }

    public static void printNice(RawAbility[] abilities, BufferedWriter out) throws IOException {
	int neededDots = getHighestNameLength(abilities) + additionalDots;
	for (RawAbility ability : abilities) {
	    out.write(ability.getName());
	    for (int dots = ability.getName().length(); dots < neededDots; dots++)
		out.write('.');
	    out.write(" Coût: ");
	    out.write(Integer.toString(ability.getCost()));
	    out.newLine();
	    out.write("Classes: " + ability.getClasses());
	    out.newLine();
	    out.write("Prérequis: " + ability.getPrerequisites());
	    out.newLine();
	    out.write("Description: " + ability.getDescription());
	    out.newLine();
	    out.newLine();
	}
    }

    private static int getHighestNameLength(RawAbility[] abilities) {
	int highestLength = 0;
	for (RawAbility ability : abilities)
	    if (highestLength < ability.getName().length())
		highestLength = ability.getName().length();
	return highestLength;
    }

    public static void main(String[] args) {
	RawAbility[] abilities = AbilityParser.parseRawAbilities(AbilityParser.INLINE_FILE);
	File nicePrintFile = new File("Inline_Abilities.txt");
	try (BufferedWriter out = new BufferedWriter(new PrintWriter(nicePrintFile))) {
	    printInline(abilities, out);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
