package org.torque.lib.hardware.moving.motor;

/**
 * Created by Jaxon A Brown on 2/6/2016.
 */
public class MotorLink {
    private Motor[] motors;

    public MotorLink(Motor... motors) {
        if(motors.length == 0) {
            throw new IllegalArgumentException("Cannot have no motors in a link");
        }

        this.motors = motors;
    }

    /**
     * Set the power of the motors
     * @param power double power, [-1,1]
     */
    public void setPower(double power) {
        for(Motor motor : motors) {
            motor.setPower(power);
        }
    }

    /**
     * Get the current power on the motor
     * @return double power, [-1,1]
     */
    public double getPower() {
        return motors[0].getPower();
    }

    /**
     * Set the reverse state of this motor
     * @param reversed true indicates that motor input should be reversed.
     */
    public void setReversed(boolean reversed) {
        for(Motor motor : motors) {
            motor.setReversed(reversed);
        }
    }

    /**
     * Get the reverse state of this motor
     * @return true indicates that motor input should be reversed.
     */
    public boolean getReversed() {
        return motors[0].getReversed();
    }

    /**
     * Stop the motor completely.
     */
    public void stop() {
        for(Motor motor : motors) {
            motor.stop();
        }
    }

    public Motor[] getMotors() {
        return motors;
    }
}
