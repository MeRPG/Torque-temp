package org.torque.stronghold.module;

import org.torque.lib.hardware.moving.motor.Motor;
import org.torque.lib.hardware.moving.motor.type.VictorMotor;
import static org.torque.stronghold.ConfigurationService.*;

/**
 * Created by Jaxon A Brown on 2/22/2016.
 */
public class Arm {
    private Motor armMotor;

    public Arm() {
        this.armMotor = new VictorMotor(PORT_ARM);
        this.armMotor.setReversed(true);
    }

    public void setArmPower(double power) {
        armMotor.setPower(power * ARM_POWER);
    }

    public void setArmRaw(double power) {
        armMotor.setPower(power);
    }
}
