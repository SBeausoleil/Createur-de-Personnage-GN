package com.sb.cdp.idl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sb.cdp.magic.Domain;
import com.sb.cdp.magic.Spell;

public class SpellParser {

    public static final String SEPARATOR = ":!:";
    public static final Pattern DOMAIN_PATTERN = Pattern.compile("^\\[(.+)\\]$");
    public static final Pattern LEVEL_PATTERN = Pattern.compile("^\\{(.+)\\}$");
    public static final Pattern SPELL_PATTERN = Pattern.compile("^(.+)" /* Name */ + SEPARATOR
	    + "(.+)" /* Description */ + SEPARATOR + "(.+)" /* Range */ + SEPARATOR + "(.+)" /* Duration */ + "$");
    public static final int NAME = 1;
    public static final int DESCRIPTION = 2;
    public static final int RANGE = 3;
    public static final int DURATION = 4;

    public static int castingTimeLevelMultiplier = 10;
    //public static int
    // TESTME
    public static Map<String, Domain<Spell>> parseSpells(File spellsFile,
	    Map<String, Domain<Spell>> domains) throws FileNotFoundException, IOException {
	if (domains == null)
	    domains = new TreeMap<>();

	try (BufferedReader in = new BufferedReader(new FileReader(spellsFile))) {
	    String line;
	    Domain currentDomain = null;
	    Matcher matcher = null;
	    int currentLevel = 0;
	    while ((line = in.readLine()) != null) {
		if (line.isEmpty())
		    continue;
		// Check if new domain declaration
		matcher = DOMAIN_PATTERN.matcher(line);
		if (matcher.find()) {
		    currentDomain = domains.get(matcher.group(1).trim());
		    if (currentDomain == null) {
			currentDomain = new Domain(matcher.group(1).trim());
			domains.put(currentDomain.getName(), currentDomain);
		    }
		} else {
		    // Else check if new level declaration
		    matcher = LEVEL_PATTERN.matcher(line);
		    if (matcher.find())
			currentLevel = Integer.parseInt(matcher.group(1).trim());
		    // Else parse spell
		    else {
			matcher = SPELL_PATTERN.matcher(line);
			if (matcher.find()) {
			    currentDomain.getSpells().add(parseSpell(matcher, currentDomain, currentLevel));
			}
			else
			    throw new RuntimeException("Line \"" +line + "\" does not match with any available matchers.");
		    }
		}

	    }
	}

	return domains;
    }

    private static Spell parseSpell(Matcher matcher, Domain currentDomain, int currentLevel) {
	Spell spell = new Spell(matcher.group(NAME).trim()); // Sets the name
	spell.setDescription(matcher.group(DESCRIPTION).trim());
	spell.setRange(matcher.group(RANGE).trim());
	spell.setDuration(matcher.group(DURATION).trim());
	spell.setCastingTime(currentLevel * castingTimeLevelMultiplier);
	spell.setLevel(currentLevel);
	spell.setDomain(currentDomain);
	spell.setCost(cost(currentLevel));
	return spell;
    }

    private static int cost(int currentLevel) {
	switch(currentLevel) {
	case 1:
	case 2:
	    return 1;
	case 3:
	case 4:
	    return 2;
	case 5:
	    return 3;
	}
	return 0;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
	Map<String, Domain<Spell>> domains = new TreeMap<>();
	parseSpells(new File("IDL_Spells.txt"), domains);

	for (Domain<Spell> domain : domains.values())
	    for (Spell spell : domain.getSpells())
		System.out.println(spell);
    }
}
