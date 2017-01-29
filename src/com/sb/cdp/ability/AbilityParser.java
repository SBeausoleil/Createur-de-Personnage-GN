package com.sb.cdp.ability;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sb.cdp.PlayerClass;

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

    public static Map<String, Ability> parseAbilities(File inlineFile, Map<String, Ability> abilities)
	    throws FileNotFoundException, IOException {
	if (abilities == null)
	    abilities = new HashMap<>();

	Set<RawAbility> rawAbilities = new LinkedHashSet<>();
	readRawAbilities(inlineFile, rawAbilities);

	for (RawAbility raw : rawAbilities)
	    abilities.put(raw.name, new Ability(raw.name));

	for (RawAbility raw : rawAbilities)
	    parseAbility(raw, abilities);
	return abilities;
    }

    private static void readRawAbilities(File inlineFile, Set<RawAbility> rawAbilities)
	    throws FileNotFoundException, IOException {
	BufferedReader in = new BufferedReader(new FileReader(inlineFile));
	String line = null;
	while ((line = in.readLine()) != null)
	    parseRawAbilities(line, rawAbilities);
	in.close();
    }

    private static void parseRawAbilities(String line, Set<RawAbility> rawAbilities) {
	Matcher match = RAW_PARSING_PATTERN.matcher(line);
	match.find();
	String[] captures = new String[match.groupCount() + 1]; // +1 because group[0] is not included in groupCount()

	for (int i = 0; i < captures.length; i++) {
	    captures[i] = match.group(i);
	    if (captures[i] != null)
		captures[i] = captures[i].trim();
	}

	final int N_ABILITIES = captures[UP_TO] != null ? extractUpTo(captures[UP_TO]) : 1;
	for (int i = 0; i < N_ABILITIES; i++) {
	    int level = captures[LEVEL] != null ? extractLevel(captures[LEVEL]) + i : 0;
	    String name = captures[NAME] + (level != 0 ? " " + level : "");
	    String prerequisites = captures[PREREQUISITES];
	    if (i > 0) // When generating abilities from a range, add the precedent level of the skill to the prerequisites list.
		prerequisites += captures[NAME] + " " + (level - 1);
	    rawAbilities.add(new RawAbility(name, Integer.parseInt(captures[COST]), captures[CLASSES],
		    prerequisites, captures[DESCRIPTION]));
	}
    }

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

    private static void parseAbility(RawAbility raw, Map<String, Ability> abilities) {
	Ability ability = new Ability(raw.name, raw.cost, null, null, null, raw.description);
	// Missing: Classes, Conditions, Bonuses
	// Classes
	String[] elements = isolateElements(raw.classes);
	PlayerClass[] classes = new PlayerClass[elements.length];
	if (classes.length == 1 && elements[0].equalsIgnoreCase(noClassRequired))
	    classes = null;
	else
	    for (int i = 0; i < classes.length; i++)
		classes[i] = PlayerClass.get(elements[i]);
	ability.setClasses(classes);

	// Conditions // TODO finish once the two first TO DO tags are done.
	elements = isolateElements(raw.prerequisites);
	Condition[] prerequisites;
	if (elements.length == 1 && elements[0].equalsIgnoreCase(noPrerequisites))
	    prerequisites = null;
	else {
	    prerequisites = new Condition[elements.length];
	    for (int i = 0; i < prerequisites.length; i++)
		prerequisites[i] = parseCondition(elements[i]);
	}
	ability.setPrerequisites(prerequisites);

	// TODO Parse through the description searching for bonuses

	abilities.put(ability.getName(), ability);
    }

    private static Condition parseCondition(String str) {
	// TODO finish
	return new OtherCondition(str); // Lazy solution for now
    }

    private static String[] isolateElements(String str) {
	String[] words = str.split(",");
	for (int i = 0; i < words.length; i++)
	    words[i] = words[i].trim();
	return words;
    }

    private static class RawAbility { // Ah, structs. How I wish we had them.
	String name;
	int cost;
	String classes;
	String prerequisites;
	String description;

	RawAbility(String name, int cost, String classes, String prerequisites, String description) {
	    this.name = name;
	    this.cost = cost;
	    this.classes = classes;
	    this.prerequisites = prerequisites;
	    this.description = description;
	}
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
	File inlineFile = new File("Inline_Abilities.txt");
	Map<String, Ability> abilities = parseAbilities(inlineFile, null);
	for (Ability ability : abilities.values())
	    System.out.println(ability);
    }
}