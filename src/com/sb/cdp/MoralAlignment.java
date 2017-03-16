package com.sb.cdp;

public enum MoralAlignment {
    GOOD, NEUTRAL, EVIL;

    @Override
    public String toString() {
	switch (this) {
	case GOOD:
	    return "Bon";
	case NEUTRAL:
	    return "Neutre";
	case EVIL:
	    return "Mauvais";
	}
	return this.name();
    }
}
