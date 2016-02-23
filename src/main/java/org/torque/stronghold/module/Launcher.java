package org.torque.stronghold.module;

import org.torque.lib.hardware.moving.motor.Motor;
import org.torque.lib.hardware.moving.motor.MotorLink;
import org.torque.lib.hardware.moving.motor.type.VictorMotor;
import static org.torque.stronghold.ConfigurationService.*;

/**
 * @author Jaxon A Brown
 */
public class Launcher {
    private Motor collector;
    private Motor launchLower;
    private Motor launchUpper;

    private boolean wasLaunching = false;
    private long startedLaunching;

    public Launcher() {
        this.collector = new VictorMotor(PORT_LAUNCH_COLLECTOR);
        this.collector.setReversed(true);

        launchLower = new VictorMotor(PORT_LAUNCH_LOWER_LAUNCH);
        launchUpper = new VictorMotor(PORT_LAUNCH_UPPER_LAUNCH);
        launchUpper.setReversed(true);
    }

    /**
     * Power on/off the launcher.
     * @param launching True to turn on the launcher.
     */
    public void setLaunching(boolean launching) {
        if(launching) {
            launchLower.setPower(LAUNCHER_LAUNCH_POWER);
            launchUpper.setPower(LAUNCHER_LAUNCH_POWER * LAUNCHER_LAUNCH_UPPER_FACTOR);
            if(!wasLaunching) {
                wasLaunching = true;
                startedLaunching = System.currentTimeMillis();
            }
        } else {
            launchLower.setPower(0);
            launchUpper.setPower(0);
            wasLaunching = false;
        }
    }

    public boolean isAtFullSpeed() {
        return wasLaunching && startedLaunching < System.currentTimeMillis() - 1250;
    }

    /**
     * Set the speed of the collector.
     * @param collectorSpeed Speed of the collector. [-1, 1]
     */
    public void setCollectorSpeed(double collectorSpeed) {
        this.collector.setPower(collectorSpeed);
    }
}
