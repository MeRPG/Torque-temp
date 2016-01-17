package org.torque.lib.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * Basic util class to swallow a few exceptions, making it easier to sleep.
 * @author Jaxon A Brown
 */
public class Timer {
    /**
     * Pauses (and yields) the thread
     * @param duration amount of unit to pause for
     * @param unit unit to apply duration to
     */
    public static void wait(long duration, TimeUnit unit) {
        waitMillis(unit.toMillis(duration));
    }

    /**
     * Pauses (and yields) the thread
     * @param millis amount of millis to pause for
     */
    public static void waitMillis(long millis) {
        try {
            Thread.sleep(millis);
        } catch(InterruptedException ex) {
            new RuntimeException("Timer exception swallowed", ex).printStackTrace();
        }
    }
}
