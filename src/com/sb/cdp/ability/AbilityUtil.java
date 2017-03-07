package com.sb.cdp.ability;

import java.util.ArrayList;

import com.sb.cdp.CharacterType;
import com.sb.util.ArrayUtil;

/**
 * A series of utility methods for {@link Ability} objects.
 * 
 * @see Ability
 * @author Samuel Beausoleil
 */
public final class AbilityUtil {

    private AbilityUtil() {} // Do not instantiate

    public static String characterTypeConditionsToString(Condition[] conditions) {
	return characterTypeConditionsToString(selectCharacterTypeConditions(conditions));
    }

    private static CharacterTypeCondition[] selectCharacterTypeConditions(Condition[] conditions) {
	ArrayList<CharacterTypeCondition> list = new ArrayList<>();
	for (Condition condition : conditions)
	    if (condition instanceof CharacterTypeCondition)
		list.add((CharacterTypeCondition) condition);
	return list.toArray(new CharacterTypeCondition[list.size()]);
    }
    
    public static String characterTypeConditionsToString(CharacterTypeCondition[] conditions) {
	String message;
	if (conditions.length == 0)
	    message = "Accessible à tous";
	else if (conditions.length == 1)
	    message = conditions[0].describe();
	else
	    message = "(" + ArrayUtil.join(conditions, AbilityUtil::formatNeededTypes, ") et (") + ")";
	return message;
    }

    private static String formatNeededTypes(CharacterTypeCondition condition) {
	return ArrayUtil.join(condition.getNeededTypes().toArray(new CharacterType[condition.getNeededTypes().size()]), CharacterType::getName, " ou ");
    }
}
