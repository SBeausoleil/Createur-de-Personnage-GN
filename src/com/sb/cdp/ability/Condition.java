package com.sb.cdp.ability;

import com.sb.cdp.PlayerCharacter;

public interface Condition {
    public boolean accept(PlayerCharacter pc);
    public String describe();
}
