package com.sb.idl.spell;

public class Spell<SpellType extends Spell> {
    private String name;
    private long duration;
    private long castingTime;
    private String description;
    private float range;
    private int cost;
    private Domain<SpellType> domain;
}
