package com.sb.idl.spell;

import java.util.Set;

public class Domain<SpellType extends Spell> {
    private String name;
    private Set<SpellType> spells;
}
