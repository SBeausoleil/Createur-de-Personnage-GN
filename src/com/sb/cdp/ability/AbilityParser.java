package com.sb.cdp.ability;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sb.cdp.PlayerClass;

// TODO refactor to remove all traces of RawAbility
// TODO the mass parsing functions must be given an option to register an ability
public class AbilityParser {

    /**
     * A .txt file containing each abilities, each one taking a line.
     */
    public static final File INLINE_FILE = new File("Inline_Abilities.txt");

    /**
     * A very adaptable regex to capture groups of information within a potentially badly formatted
     * String.
     */
    public static final Pattern RAW_PARSING_PATTERN = Pattern.compile(
	    "^([^.0-9]+)([0-9]( à [0-9]+)?)?[ .]+[Cc].+=?:? ([0-9]+).*Classes? ?:(.+)[Pp]ré ?requis ?:(.+)Description ?:(.+)");

    public static final int NAME = 1;
    public static final int LEVEL = 2;
    public static final int UP_TO = 3;
    public static final int COST = 4;
    public static final int CLASSES = 5;
    public static final int PREREQUISITES = 6;
    public static final int DESCRIPTION = 7;

    public static String noClassRequired = "général";
    public static String noPrerequisites = "aucun";

    private static int extractLevel(String str) {
	if (str == null || str.isEmpty())
	    return -1;
	int index = 0;
	while (++index < str.length() && Character.isDigit(str.charAt(index)));
	return Integer.parseInt(str.substring(0, index));
    }

    private static int extractUpTo(String str) {
	if (str == null || str.isEmpty())
	    return -1;
	int index = str.length() - 1;
	while (Character.isDigit(str.charAt(--index)));
	return Integer.parseInt(str.substring(++index));
    }

    public static RawAbility[] parseRawAbilities(File inlinedAbilities) {
	LinkedHashSet<RawAbility> abilities = new LinkedHashSet<>();
	try (BufferedReader in = new BufferedReader(new FileReader(inlinedAbilities))) {
	    String line = null;
	    while ((line = in.readLine()) != null) {
		RawAbility[] abilitiesArray = parseRawAbility(line);
		for (RawAbility ability : abilitiesArray)
		    abilities.add(ability);
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return abilities.toArray(new RawAbility[abilities.size()]);
    }

    public static RawAbility[] parseRawAbility(String line) {
	Matcher match = RAW_PARSING_PATTERN.matcher(line);
	match.find();
	String[] captures = new String[match.groupCount() + 1]; // +1 because group[0] is not included in groupCount()

	for (int i = 0; i < captures.length; i++) {
	    captures[i] = match.group(i);
	    if (captures[i] != null)
		captures[i] = captures[i].trim();
	}

	RawAbility[] abilities = new RawAbility[captures[UP_TO] != null ? extractUpTo(captures[UP_TO]) : 1];
	for (int i = 0; i < abilities.length; i++) {
	    int level = captures[LEVEL] != null ? extractLevel(captures[LEVEL]) + i : 0;
	    String name = captures[NAME] + (level != 0 ? " " + level : "");
	    if (i > 0) // When generating abilities from a range, add the precedent level of the skill to the prerequisites list. // TODO Come back on this later on, I feel this may bring in some minor bugs in future runs...
		captures[PREREQUISITES] += captures[NAME] + (level - 1);
	    abilities[i] = new RawAbility(name, Integer.parseInt(captures[COST]), captures[CLASSES],
		    captures[PREREQUISITES], captures[DESCRIPTION]);
	}
	return abilities;
    }

    public static Ability parseAbility(RawAbility raw) {
	Ability ability = new Ability(raw.getName(), raw.getCost(), null, null, null, raw.getDescription());
	// Missing: Classes, Conditions, Bonuses
	// Classes
	String[] elements = isolateElements(raw.getClasses());
	PlayerClass[] classes = new PlayerClass[elements.length];
	if (classes.length == 1 && elements[0].equalsIgnoreCase(noClassRequired))
	    classes = null;
	else
	    for (int i = 0; i < classes.length; i++)
		classes[i] = PlayerClass.get(elements[i]);

	// Conditions // TODO finish once the two first TO DO tags are done.
	elements = isolateElements(raw.getPrerequisites());
	Condition[] prerequisites;
	if (elements.length == 1 && elements[0].equalsIgnoreCase(noPrerequisites))
	    prerequisites = null;
	else {
	    prerequisites = new Condition[elements.length];
	    for (int i = 0; i < prerequisites.length; i++) {
		
	    }
	}

	return ability;
    }

    private static String[] isolateElements(String str) {
	String[] words = str.split(",");
	for (int i = 0; i < words.length; i++)
	    words[i] = words[i].trim();
	return words;
    }

    public static void main(String[] args) {
	RawAbility[] abilities = parseRawAbilities(INLINE_FILE);
	for (RawAbility ability : abilities)
	    System.out.println(ability);
    }
}