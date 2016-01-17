package org.torque.lib.hardware.sensor;

import edu.wpi.first.wpilibj.DigitalInput;
import org.torque.lib.concurrent.Timer;

/**
 * Anti-Limit switch. Can be read, and also starts a thread to listen to changes.
 * @author Jaxon A Brown
 */
public class AntiLimitSwitch extends Callable {
    private DigitalInput wpiDigitalInput;
    private long refreshTime = 50;
    private AntiLimitSwitchCallThread thread;

    /**
     * Construct a new AntiLimitSwitch with refresh time as a default 50ms.
     * @param port port of the limit switch (DigitalInput)
     */
    public AntiLimitSwitch(int port) {
        this.wpiDigitalInput = new DigitalInput(port);
        (this.thread = new AntiLimitSwitchCallThread()).start();
    }

    /**
     * Construct a new AntiLimitSwitch.
     * @param port port of the limit switch (DigitalInput)
     * @param refreshTime refresh time (ms) for the listener
     */
    public AntiLimitSwitch(int port, long refreshTime) {
        this(port);
        this.refreshTime = refreshTime;
    }

    /**
     * Check if the LimitSwitch is released
     * @return true if the switch is released
     */
    public boolean isReleased() {
        return !wpiDigitalInput.get();
    }

    private class AntiLimitSwitchCallThread extends Thread {
        private boolean isCancelled = false;
        private boolean wasPressed = true;

        @Override
        public void run() {
            wasPressed = !isReleased();
            while(!isCancelled) {
                if(wasPressed && isReleased()) {
                    callTasks();
                    wasPressed = false;
                }

                if(!wasPressed && !isReleased()) {
                    wasPressed = true;
                }

                Timer.waitMillis(refreshTime);
            }
        }

        public void cancel() {
            this.isCancelled = true;
        }
    }

    /**
     * Don't call this. This method is to stop the thread when the object gets eaten by the GC.
     * @throws Throwable
     */
    @Override
    public void finalize() throws Throwable {
        super.finalize();
        thread.cancel();
    }
}