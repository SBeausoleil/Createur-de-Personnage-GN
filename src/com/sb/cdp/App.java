package com.sb.cdp;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.sb.cdp.idl.Initialize;

public class App {
    public static void main(String[] args) {
	RPG idl = null;
	try {
	    idl = Initialize.initialize();
	} catch (FileNotFoundException e) {
	    System.err.println("One of the files needed for the initialization of the RPG has not been found.");
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	if (idl == null)
	    System.exit(1);
	
	// Pass the RPG to the UI
    }
}