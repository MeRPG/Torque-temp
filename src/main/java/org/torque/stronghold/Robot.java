package org.torque.stronghold;

import com.ni.vision.NIVision;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.vision.AxisCamera;
import edu.wpi.first.wpilibj.vision.USBCamera;
import javafx.scene.Camera;
import org.torque.lib.def.Driver;
import org.torque.lib.def.Hand;
import org.torque.lib.driverstation.hardware.gamepad.Gamepad;
import org.torque.lib.driverstation.hardware.gamepad.GamepadButton;
import org.torque.lib.robot.TorqueRobot;
import org.torque.stronghold.module.DriveTrain;
import org.torque.stronghold.module.Launcher;

import java.lang.reflect.Field;

/**
 * Main robot class.
 * @author Jaxon A Brown
 */
public class Robot extends TorqueRobot {
    private Gamepad gamepad1;
    private Gamepad gamepad2;
    private DriveTrain driveTrain;
    private Launcher launcher;

    private AxisCamera acam;

    private NIVision.Image frame;

    public Robot() {

    }

    @Override
    public void robotInit() {
        this.gamepad1 = new Gamepad(Driver.ONE);
        this.gamepad2 = new Gamepad(Driver.TWO);

        this.driveTrain = new DriveTrain();
        this.launcher = new Launcher();

        //try {
        //    Field field = ConfigurationService.class.getField("LAUNCHER_LAUNCH_POWER");
        //    DashboardEntry entry = new DashboardEntry("maxSpeed", field);
        //    Dashboard.getInstance().addDashboardEntry(entry);
        //} catch(Exception ex) {
        //    ex.printStackTrace();
        //}

        //ConfigurationService.FORWARD_FACING_CAMERA_ID =
        //        NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController);

        //Scheduler.scheduleRepeatingTask(new ImageSendbackThread(), 1, 50);
    }

    @Override
    public void autonomous() {

    }

    //private Joystick joystick;
    //private Joystick joystick2;
    //private MotorLink left;
    //private MotorLink right;
    //private Motor whiteSpinner;
    //private MotorLink greenLauncher;
    private int camses;

    @Override
    public void teleopInit() {
        //this.joystick = new Joystick(0);
        //this.joystick2 = new Joystick(1);
        ////this.gamepad2 = new Gamepad(Driver.TWO);
        //this.left = new MotorLink(new TalonMotor(0), new TalonMotor(1));
        //this.right = new MotorLink(new TalonMotor(2), new TalonMotor(3));
        //left.setReversed(true);
        //this.whiteSpinner = new VictorMotor(4);
        //Motor greenLauncherLower = new VictorMotor(5);
        //Motor greenLauncherUpper = new VictorMotor(6);
        //whiteSpinner.setReversed(true);
        //greenLauncherUpper.setReversed(true);
        //this.greenLauncher = new MotorLink(greenLauncherLower, greenLauncherUpper);


        //camses = NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        //NIVision.IMAQdxConfigureGrab(camses);

        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
//
        //camses = NIVision.IMAQdxOpenCamera("cam1", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        //NIVision.IMAQdxConfigureGrab(camses);
//
        //NIVision.IMAQdxStartAcquisition(camses);

        acam = new AxisCamera("10.52.83.11");
    }

    @Override
    public void teleopPeriodic() {
        this.driveTrain.setLeft(gamepad1.getAxis(Hand.LEFT).getY());
        this.driveTrain.setRight(gamepad1.getAxis(Hand.RIGHT).getY());

        this.launcher.setCollectorSpeed(gamepad2.getTrigger(Hand.RIGHT) - gamepad2.getTrigger(Hand.LEFT));
        this.launcher.setLaunching(gamepad2.getButtonState(GamepadButton.B));


        //NIVision.IMAQdxGrab(camses, frame, 1);
        //CameraServer.getInstance().setImage(frame);

        try {
            acam.getImage(frame);
            CameraServer.getInstance().setImage(frame);
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        //left.setPower(deadZoneValue(joystick.getRawAxis(1)));
        //right.setPower(deadZoneValue(joystick.getRawAxis(5)));
        //NIVision.Image image = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        //NIVision.IMAQdxGrab(camses, image, 1);
        //CameraServer.getInstance().setImage(image);
        //if(this.joystick2.getRawButton(1)) {
        //    greenLauncher.setPower(1);
        //} else {
        //    greenLauncher.setPower(0);
        //}
        //this.whiteSpinner.setPower(deadZoneValue(joystick2.getRawAxis(3) - joystick2.getRawAxis(2)));
    }

    //private double deadZoneValue(double joy) {
    //    return Math.abs(joy) <= 0.1 ? 0 : joy;
    //}

    @Override
    public void testInit() {

    }

    @Override
    public void testPeriodic() {

    }

    @Override
    public void disabledInit() {
        //NIVision.IMAQdxStopAcquisition(camses);
    }

    @Override
    public void disabledPeriodic() {

    }
}
