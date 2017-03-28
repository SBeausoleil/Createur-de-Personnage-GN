package com.sb.cdp.gui.view;

/**
 * Objects implementing this interface can be updated.
 * 
 * @author Samuel Beausoleil
 */
@FunctionalInterface
public interface Controller {
    /**
     * Update the display's state to match the controller's data.
     */
    public void update();
}
