package com.sb.cdp.idl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sb.cdp.CharacterType;
import com.sb.cdp.CharacterTypePool;
import com.sb.cdp.Library;
import com.sb.cdp.ability.Ability;
import com.sb.cdp.ability.AbilityRequirement;
import com.sb.cdp.ability.CharacterTypeCondition;
import com.sb.cdp.ability.Condition;
import com.sb.cdp.ability.OtherCondition;

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

    /**
     * Parses the abilities present into a text file.
     * 
     * @param inlineFile
     * @param abilities
     *            a map of abilities. May be null. Will be modified if non-null.
     * @param ctPool
     *            a pool of existing CharacterTypes. Will be modified if a CharacterType is needed
     *            while parsing and does not exists yet.
     * @return a map containing all the abilities, news and olds.
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static Library<Ability> parseAbilities(File inlineFile, Library<Ability> abilities,
	    CharacterTypePool ctPool)
	    throws FileNotFoundException, IOException {
	Set<RawAbility> rawAbilities = new LinkedHashSet<>();
	readRawAbilities(inlineFile, rawAbilities);

	if (abilities == null)
	    abilities = new Library(Initializer.ABILITY_LIBRARY, Ability.class, true,
		    new ArrayList(rawAbilities.size()));

	HashMap<String, Ability> abilitiesMap = new HashMap<>((int) Math.ceil(rawAbilities.size() / 0.75)); // This prevents rehashing of the map's data and using more space than required.

	for (RawAbility raw : rawAbilities)
	    abilitiesMap.put(raw.name, new Ability(raw.name));

	for (RawAbility raw : rawAbilities)
	    parseAbility(raw, abilitiesMap, ctPool);

	// Add all the abilities from the abilitiesMap to the abilities library. Iterate over the values instead of collecting the values to allow finalization of the whole map later.
	for (Ability a : abilitiesMap.values())
	    abilities.add(a);

	Collections.sort((List<Ability>) abilities.getData());

	return abilities;
    }

    private static void readRawAbilities(File inlineFile, Set<RawAbility> rawAbilities)
	    throws FileNotFoundException, IOException {
	BufferedReader in = new BufferedReader(new FileReader(inlineFile));
	String line = null;
	while ((line = in.readLine()) != null) {
	    if (line.isEmpty())
		continue;
	    parseRawAbilities(line, rawAbilities);
	}
	in.close();
    }

    private static void parseRawAbilities(String line, Set<RawAbility> rawAbilities) {
	Matcher match = RAW_PARSING_PATTERN.matcher(line);
	if (!match.find())
	    throw new RuntimeException("Error when parsing a raw ability: match failed! \n Line: \"" + line + "\"");
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
	    if (i > 0) // When generating abilities from a range, add the precedent level of the skill to the prerequisites LIST.
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
	Ability ability = new Ability(raw.name, raw.cost, null, null, raw.description);
	// Missing: Classes, Conditions, Bonuses
	// Classes

	String[] elements = isolateElements(raw.classes);
	ability.setCharacterTypeConditions(parseCharacterTypeConditions(elements, ctPool));

	// Conditions // TODO finish them
	Set<Condition> prerequisites = new HashSet<>();
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
    private static CharacterTypeCondition[] parseCharacterTypeConditions(String[] elements,
	    CharacterTypePool ctPool) {
	HashMap<CharacterType.Classification, Set<CharacterType>> types = new HashMap<>();
	for (CharacterType.Classification classification : CharacterType.Classification.values())
	    types.put(classification, new HashSet<>());

	// IMPROVE come back on this. Going back on it, it looks weird.
	// Puts all the needed types in their bucket
	CharacterType current;
	for (String element : elements) {
	    current = select(ctPool.find(element), element, ctPool);
	    if (current != null)
		types.get(current.getClassification()).add(current);
	}

	// Transform the non-empty buckets into CharacterTypeCondition
	Collection<CharacterTypeCondition> conditions = new LinkedList<>();
	for (CharacterType.Classification classification : types.keySet()) {
	    Set<CharacterType> cts = types.get(classification);
	    if (!cts.isEmpty())
		conditions.add(new CharacterTypeCondition(cts));
	}

	return conditions.toArray(new CharacterTypeCondition[conditions.size()]);
    }

    /**
     * Selects the appropriate CharacterType.
     * If no CharacterType has been found, it creates a new CharacterType of the prefered
     * classification, except if the type is "général" and there is no CharacterType with that name.
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
	if (type.equals(noTypeRequired) && (found == null || found.length == 0))
	    return null;

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