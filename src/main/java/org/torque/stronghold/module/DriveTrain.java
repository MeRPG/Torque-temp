package org.torque.stronghold.module;

import org.torque.lib.hardware.moving.motor.MotorLink;
import org.torque.lib.hardware.moving.motor.type.TalonMotor;
import static org.torque.stronghold.ConfigurationService.*;

/**
 * @author Jaxon A Brown
 */
public class DriveTrain {
    private MotorLink left;
    private MotorLink right;

    private boolean reversed;

    public DriveTrain() {
        this.left = new MotorLink(new TalonMotor(PORT_DRIVE_LEFT_FRONT), new TalonMotor(PORT_DRIVE_LEFT_REAR));
        this.right = new MotorLink(new TalonMotor(PORT_DRIVE_RIGHT_FRONT), new TalonMotor(PORT_DRIVE_RIGHT_REAR));
        left.setReversed(true);

        this.reversed = false;
    }

    public void setLeft(double power) {
        power *= DRIVE_POWER;
        if(reversed) {
            right.setPower(-power);
        } else {
            left.setPower(power);
        }
    }

    public void setRight(double power) {
        power *= DRIVE_POWER;
        if(reversed) {
            left.setPower(-power);
        } else {
            right.setPower(power);
        }
    }
}
