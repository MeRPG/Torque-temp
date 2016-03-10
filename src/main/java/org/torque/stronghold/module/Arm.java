package org.torque.stronghold.module;

import org.torque.lib.hardware.moving.motor.Motor;
import org.torque.lib.hardware.moving.motor.type.VictorMotor;
import static org.torque.stronghold.ConfigurationService.*;

/**
 * Module to control the spare arm
 */
public class Arm {
    private Motor armMotor;

    public Arm() {
        this.armMotor = new VictorMotor(PORT_ARM);
        this.armMotor.setReversed(true);
    }

    /**
     * Set the power of the arm. This value will be multiplied by the ARM_POWER configuration value
     * @param power power to set the arm to. Use normal [-1, 1] bounds.
     */
    public void setArmPower(double power) {
        armMotor.setPower(power * ARM_POWER);
    }

    /**
     * Set the power of the arm. Will NOT be changed.
     * @param power power, normal [-1, 1] bounds.
     */
    public void setArmRaw(double power) {
        armMotor.setPower(power);
    }
}
