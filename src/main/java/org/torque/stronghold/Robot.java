package org.torque.stronghold;

import com.ni.vision.NIVision;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import org.torque.lib.concurrent.Scheduler;
import org.torque.lib.def.Driver;
import org.torque.lib.def.Hand;
import org.torque.lib.driverstation.hardware.gamepad.Gamepad;
import org.torque.lib.hardware.moving.motor.Motor;
import org.torque.lib.hardware.moving.motor.MotorLink;
import org.torque.lib.hardware.moving.motor.type.TalonMotor;
import org.torque.lib.hardware.moving.motor.type.VictorMotor;
import org.torque.lib.robot.TorqueRobot;
import org.torque.stronghold.vision.ImageSendbackThread;

/**
 * Main robot class.
 * @author Jaxon A Brown
 */
public class Robot extends TorqueRobot {
    public Robot() {

    }

    @Override
    public void robotInit() {
        //ConfigurationService.FORWARD_FACING_CAMERA_ID =
        //        NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController);

        //Scheduler.scheduleRepeatingTask(new ImageSendbackThread(), 1, 50);
    }

    @Override
    public void autonomous() {

    }

    private Joystick joystick;
    private Joystick joystick2;
    private MotorLink left;
    private MotorLink right;
    private Motor whiteSpinner;
    private MotorLink greenLauncher;
    private int camses;

    @Override
    public void teleopInit() {
        this.joystick = new Joystick(0);
        this.joystick2 = new Joystick(1);
        //this.gamepad2 = new Gamepad(Driver.TWO);
        this.left = new MotorLink(new TalonMotor(0), new TalonMotor(1));
        this.right = new MotorLink(new TalonMotor(2), new TalonMotor(3));
        left.setReversed(true);
        this.whiteSpinner = new VictorMotor(4);
        Motor greenLauncherLower = new VictorMotor(5);
        Motor greenLauncherUpper = new VictorMotor(6);
        whiteSpinner.setReversed(true);
        greenLauncherUpper.setReversed(true);
        this.greenLauncher = new MotorLink(greenLauncherLower, greenLauncherUpper);


        //camses = NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        //NIVision.IMAQdxConfigureGrab(camses);
    }

    @Override
    public void teleopPeriodic() {
        left.setPower(deadZoneValue(joystick.getRawAxis(1)));
        right.setPower(deadZoneValue(joystick.getRawAxis(5)));
        //NIVision.Image image = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        //NIVision.IMAQdxGrab(camses, image, 1);
        //CameraServer.getInstance().setImage(image);
        if(this.joystick2.getRawButton(1)) {
            greenLauncher.setPower(1);
        } else {
            greenLauncher.setPower(0);
        }
        this.whiteSpinner.setPower(deadZoneValue(joystick2.getRawAxis(3) - joystick2.getRawAxis(2)));
    }

    private double deadZoneValue(double joy) {
        return Math.abs(joy) <= 0.1 ? 0 : joy;
    }

    @Override
    public void testInit() {

    }

    @Override
    public void testPeriodic() {

    }

    @Override
    public void disabledInit() {

    }

    @Override
    public void disabledPeriodic() {

    }
}
