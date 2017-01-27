package com.sb.cdp.ability;

import com.sb.cdp.PlayerCharacter;

public interface StatBonus {
    public void apply(PlayerCharacter personnage);
    public void remove(PlayerCharacter personnage);
}
