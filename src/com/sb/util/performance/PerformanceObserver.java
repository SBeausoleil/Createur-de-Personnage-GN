package com.sb.util.performance;

public interface PerformanceObserver {
    public void start();
    public long stop();
    public long getTime();
    /**
     * Returns the time scale of the measured time.
     * Exemple: ns (nannosecond), ms (millisecond)
     * @return
     */
    public String getTimeScale();
}
