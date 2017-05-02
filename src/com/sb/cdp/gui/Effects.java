package com.sb.cdp.gui;

import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

/**
 * A series of effects.
 * 
 * @author Samuel Beausoleil
 */
public final class Effects {
    
    public static double defaultGlowDepth = 20;

    public static DropShadow glow(Color color) {
	return glow(color, defaultGlowDepth);
    }
    
    public static DropShadow glow(Color color, double depth) {
	DropShadow glow = new DropShadow();
	glow.setColor(color);
	glow.setOffsetX(0);
	glow.setOffsetY(0);
	glow.setHeight(depth);
	glow.setWidth(depth);
	return glow;
    }
}
