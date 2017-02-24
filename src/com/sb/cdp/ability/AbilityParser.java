package com.sb.cdp.ability;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sb.cdp.CharacterType;
import com.sb.cdp.CharacterTypePool;

public class AbilityParser {

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

    public static String noTypeRequired = "général";
    public static String noPrerequisites = "aucun";

    public static CharacterType.Classification preferParsingAs = CharacterType.Classification.CLASS;

    public static Map<String, Ability> parseAbilities(File inlineFile, Map<String, Ability> abilities, CharacterTypePool ctPool)
	    throws FileNotFoundException, IOException {
	Set<RawAbility> rawAbilities = new LinkedHashSet<>();
	readRawAbilities(inlineFile, rawAbilities);

	if (abilities == null)
	    abilities = new HashMap<>((int) Math.ceil(rawAbilities.size() / 0.75)); // This prevents rehashing of the map's data and using more space than required.

	for (RawAbility raw : rawAbilities)
	    abilities.put(raw.name, new Ability(raw.name));

	for (RawAbility raw : rawAbilities)
	    parseAbility(raw, abilities, ctPool);
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

    private static void parseAbility(RawAbility raw, Map<String, Ability> abilities, CharacterTypePool ctPool) {
	Ability ability = new Ability(raw.name, raw.cost, null, raw.description);
	// Missing: Classes, Conditions, Bonuses
	// Classes
	Set<Condition> prerequisites = new HashSet<>();
	String[] elements = isolateElements(raw.classes);
	prerequisites.addAll(parseCharacterTypeConditions(elements, ctPool));

	// Conditions // TODO finish them
	elements = isolateElements(raw.prerequisites);
	if (elements.length != 1 && !elements[0].equalsIgnoreCase(noPrerequisites)) {
	    for (int i = 0; i < elements.length; i++)
		prerequisites.add(parseCondition(elements[i], abilities));
	}
	ability.setPrerequisites(prerequisites.toArray(new Condition[prerequisites.size()]));

	abilities.put(ability.getName(), ability);
    }

    /**
     * Parses the class and race elements of the RawAbility into appropriate
     * CharacterTypeConditions.
     * By default, returns a set of size zero to a maximum of the number of
     * CharacterType.Classificiation that exist.
     * 
     * @param elements
     * @see CharacterType.Classification
     * @return
     */
    private static Collection<CharacterTypeCondition> parseCharacterTypeConditions(String[] elements, CharacterTypePool ctPool) {
	HashMap<CharacterType.Classification, Set<CharacterType>> types = new HashMap<>();
	for (CharacterType.Classification classification : CharacterType.Classification.values())
	    types.put(classification, new HashSet<>());

	// Puts all the needed types in their bucket
	CharacterType current;
	for (String element : elements) {
	    current = select(ctPool.find(element), element, ctPool);
	    types.get(current.getClassification()).add(current);
	}

	// Transform the non-empty buckets into CharacterTypeCondition
	Collection<CharacterTypeCondition> conditions = new LinkedList<>();
	for (CharacterType.Classification classification : types.keySet()) {
	    Set<CharacterType> cts = types.get(classification);
	    if (!cts.isEmpty())
		conditions.add(new CharacterTypeCondition(cts));
	}

	return conditions;
    }

    /**
     * Selects the appropriate CharacterType.
     * If no CharacterType has been found, it creates a new CharacterType of the prefered
     * classification.
     * Otherwise if there is only one found, it returns it.
     * If there are more than one found: it will find the one with the prefered parsing
     * classification. If none are found of that classification, it returns the first.
     * 
     * @param found
     * @param type
     * @see #preferParsingAs
     * @return the appropriate CharacterType
     */
    private static CharacterType select(CharacterType[] found, String type, CharacterTypePool ctPool) {
	if (found == null || found.length == 0)
	    return ctPool.get(type, preferParsingAs);
	else if (found.length == 1)
	    return found[0];
	else {
	    for (CharacterType ct : found)
		if (ct.getClassification() == preferParsingAs)
		    return ct;
	    return found[0];
	}
    }

    private static Condition parseCondition(String str, Map<String, Ability> abilities) {
	Condition condition = null;
	Ability abilitySought = abilities.get(str);
	if (abilitySought != null)
	    condition = new AbilityRequirement(abilitySought);
	else
	    condition = new OtherCondition(str); // Lazy solution for now
	return condition;
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
}