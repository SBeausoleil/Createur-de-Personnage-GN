package com.sb.cdp.magic;

public class Spell extends Magic<Spell> {

    public Spell(String name, String duration, long castingTime, String description, String range, int cost,
	    Domain<Spell> domain) {
	super(name, 0, duration, castingTime, description, range, cost, domain);
    }

    public Spell(String name) {
	super(name);
    }

}
