package com.sb.cdp.ability;

import java.util.HashMap;
import java.util.Map;

public class Abilities {
    public static Map<String, Ability> registry = new HashMap<>();

    public static Ability get(String name) {
	return registry.get(name);
    }

    public static void register(Ability ability) {
	registry.put(ability.getName(), ability);
    }
}
