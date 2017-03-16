package com.sb.cdp;

public enum LawAlignment {
    LAWFUL, NEUTRAL, CHAOTIC;

    @Override
    public String toString() {
	switch (this) {
	case LAWFUL:
	    return "Loyal";
	case NEUTRAL:
	    return "Neutre";
	case CHAOTIC:
	    return "Chaotic";
	}
	return name();
    }
}
