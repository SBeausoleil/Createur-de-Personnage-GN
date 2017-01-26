package com.sb.idl.ability;

import com.sb.idl.PlayerCharacter;

public interface StatBonus {
    public void apply(PlayerCharacter personnage);
    public void remove(PlayerCharacter personnage);
}
