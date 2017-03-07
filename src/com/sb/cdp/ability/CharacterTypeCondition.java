package com.sb.cdp.ability;

import java.util.HashSet;
import java.util.Set;

import com.sb.cdp.CharacterType;
import com.sb.cdp.PlayerCharacter;

public class CharacterTypeCondition implements Condition {

    private Set<CharacterType> neededTypes;

    public CharacterTypeCondition() {
	neededTypes = new HashSet<>();
    }

    public CharacterTypeCondition(Set<CharacterType> neededTypes) {
	this.neededTypes = neededTypes;
    }

    /**
     * Checks if the PlayerCharacter has at least one of the neededTypes.
     */
    @Override
    public boolean accept(PlayerCharacter pc) {
	for (CharacterType pct : pc.getCharacterTypes())
	    if (neededTypes.contains(pct))
		return true;
	return false;
    }

    @Override
    public String describe() {
	boolean plural = neededTypes.size() > 1;
	StringBuilder sb = new StringBuilder(String.format("%1 classe%2/race%2 suivante%2 doit être possédée: ", plural ? "L'une des" : "La ", plural ? "S" : ""));
	int i = 0;
	for (CharacterType type : neededTypes) {
	    sb.append(type.getName());
	    if (i + 1 < neededTypes.size())
		sb.append(", ");
	}
	return sb.toString();
    }

    /**
     * Returns the neededTypes.
     * @return the neededTypes
     */
    public Set<CharacterType> getNeededTypes() {
        return neededTypes;
    }

    /**
     * Sets the value of neededTypes to that of the parameter.
     * @param neededTypes the neededTypes to set
     */
    public void setNeededTypes(Set<CharacterType> neededTypes) {
        this.neededTypes = neededTypes;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "CharacterTypeCondition [neededTypes=" + neededTypes + "]";
    }

}
