package com.sb.idl.ability;

import com.sb.idl.PlayerCharacter;

public interface Condition {
    public boolean accept(PlayerCharacter pc);
    public String describe();
}
