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
    private MotorLink launcher;

    public Launcher() {
        this.collector = new VictorMotor(PORT_LAUNCH_COLLECTOR);
        this.collector.setReversed(true);

        Motor launchLower = new VictorMotor(PORT_LAUNCH_LOWER_LAUNCH);
        Motor launchUpper = new VictorMotor(PORT_LAUNCH_UPPER_LAUNCH);
        launchUpper.setReversed(true);
        this.launcher = new MotorLink(launchLower, launchUpper);
    }

    /**
     * Power on/off the launcher.
     * @param launching True to turn on the launcher.
     */
    public void setLaunching(boolean launching) {
        if(launching) {
            launcher.setPower(LAUNCHER_LAUNCH_POWER);
        } else {
            launcher.setPower(0);
        }
    }

    /**
     * Set the speed of the collector.
     * @param collectorSpeed Speed of the collector. [-1, 1]
     */
    public void setCollectorSpeed(double collectorSpeed) {
        this.collector.setPower(collectorSpeed);
    }
}
