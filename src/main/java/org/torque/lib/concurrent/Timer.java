package org.torque.lib.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @author Jaxon A Brown
 */
public class Timer {
    public static void wait(long duration, TimeUnit unit) {
        waitMillis(unit.toMillis(duration));
    }

    public static void waitMillis(long millis) {
        try {
            Thread.sleep(millis);
        } catch(InterruptedException ex) {
            new RuntimeException("Timer exception swallowed", ex).printStackTrace();
        }
    }
}
