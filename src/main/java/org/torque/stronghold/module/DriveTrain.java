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
        //Create motor links
        this.left = new MotorLink(new TalonMotor(PORT_DRIVE_LEFT_FRONT), new TalonMotor(PORT_DRIVE_LEFT_REAR));
        this.right = new MotorLink(new TalonMotor(PORT_DRIVE_RIGHT_FRONT), new TalonMotor(PORT_DRIVE_RIGHT_REAR));
        //The left half shall be reversed
        left.setReversed(true);

        //This reverse is different - it inverts the controls as well.
        this.reversed = false;
    }

    /**
     * Drive train made to mimic video games.
     * Intended to use the triggers as the Y axis
     * @param axisX used to turn [-1,1]
     * @param axisY used to go forward [-1,1]
     * @author William Yount (FRC 3164)
     */
    public void forzaDrive(double axisX, double axisY) {
        axisX = -100 * axisX;
        axisY = 100 * axisY;

        double v = (100 - Math.abs(axisX)) * (axisY/100) + axisY;
        double w = (100 - Math.abs(axisY)) * (axisX/100) + axisX;

        double r = (v+w)/200;
        double l = (v-w)/200;

        setLeft(l);
        setRight(r);
    }

    /**
     * Set weather or not the drive train is inverted
     * @param reversed weather or not the drive train is inverted
     */
    public void setReversed(boolean reversed) {
        this.reversed = reversed;
    }

    /**
     * Set the power of the left half of the robot.
     * @param power power to use. [-1, 1]
     */
    public void setLeft(double power) {
        power *= DRIVE_POWER;
        if(reversed) {
            right.setPower(-power);
        } else {
            left.setPower(power);
        }
    }

    /**
     * Set the power of the right half of the robot.
     * @param power power to use. [-1, 1]
     */
    public void setRight(double power) {
        power *= DRIVE_POWER;
        if(reversed) {
            left.setPower(-power);
        } else {
            right.setPower(power);
        }
    }
}
