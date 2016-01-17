package org.torque.lib.hardware.sensor;

import edu.wpi.first.wpilibj.DigitalInput;
import org.torque.lib.concurrent.Timer;

/**
 * Limit switch. Can be read, and also starts a thread to listen to changes.
 * @author Jaxon A Brown
 */
public class LimitSwitch extends Callable {
    private DigitalInput wpiDigitalInput;
    private long refreshTime = 50;
    private LimitSwitchCallThread thread;

    /**
     * Construct a new LimitSwitch with refresh time as a default 50ms.
     * @param port port of the limit switch (DigitalInput)
     */
    public LimitSwitch(int port) {
        this.wpiDigitalInput = new DigitalInput(port);
        (this.thread = new LimitSwitchCallThread()).start();
    }

    /**
     * Construct a new LimitSwitch.
     * @param port port of the limit switch (DigitalInput)
     * @param refreshTime refresh time (ms) for the listener
     */
    public LimitSwitch(int port, long refreshTime) {
        this(port);
        this.refreshTime = refreshTime;
    }

    /**
     * Check if the LimitSwitch is depressed
     * @return true if the switch is pressed
     */
    public boolean isPressed() {
        return wpiDigitalInput.get();
    }

    private class LimitSwitchCallThread extends Thread {
        private boolean isCancelled = false;
        private boolean wasPressed = false;

        @Override
        public void run() {
            wasPressed = isPressed();
            while(!isCancelled) {
                if(!wasPressed && isPressed()) {
                    callTasks();
                    wasPressed = true;
                }

                if(wasPressed && !isPressed()) {
                    wasPressed = false;
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
