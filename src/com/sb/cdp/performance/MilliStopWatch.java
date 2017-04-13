package com.sb.cdp.performance;

public class MilliStopWatch implements PerformanceObserver {

    private long time;
    
    @Override
    public void start() {
	time = System.currentTimeMillis();
    }

    @Override
    public long stop() {
	time = System.currentTimeMillis() - time;
	return time;
    }

    @Override
    public long getTime() {
	return time;
    }

    @Override
    public String getTimeScale() {
	return "ms";
    }

}
