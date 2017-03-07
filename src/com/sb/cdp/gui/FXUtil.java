package com.sb.cdp.gui;

import java.io.IOException;

import com.sb.cdp.DesktopApplication;

import javafx.fxml.FXMLLoader;

public final class FXUtil {
    private FXUtil() {}

    public static Object load(String resourceURL) {
	FXMLLoader loader = new FXMLLoader();
	loader.setLocation(DesktopApplication.class.getResource(resourceURL));
	try {
	    return loader.load();
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
    }
}
