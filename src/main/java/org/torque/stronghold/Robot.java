package org.torque.stronghold;

import com.ni.vision.NIVision;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.torque.lib.concurrent.Scheduler;
import org.torque.lib.def.Driver;
import org.torque.lib.def.Hand;
import org.torque.lib.driverstation.hardware.gamepad.Gamepad;
import org.torque.lib.driverstation.hardware.gamepad.GamepadButton;
import org.torque.lib.driverstation.hardware.gamepad.RumbleType;
import org.torque.lib.driverstation.software.dashboard.Dashboard;
import org.torque.lib.robot.TorqueRobot;
import org.torque.stronghold.autoProgram.DriveForwardAuto;
import org.torque.stronghold.module.DriveTrain;
import org.torque.stronghold.module.Launcher;
import org.torque.stronghold.vision.ImageSendbackThread;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * Main robot class.
 * @author Jaxon A Brown
 */
public class Robot extends TorqueRobot {
    public Gamepad gamepad1;
    public Gamepad gamepad2;
    public DriveTrain driveTrain;
    public Launcher launcher;

    public ImageSendbackThread imageSendbackThread;
    public AutoEngine autoEngine;

    public Robot() {
        //Schedule the thread which will send images back to the driver station.
        this.imageSendbackThread = new ImageSendbackThread();
        Scheduler.scheduleRepeatingTask(imageSendbackThread, 1000, 50);

        this.autoEngine = new AutoEngine(this);
        this.autoEngine.registerAuto(new DriveForwardAuto());

        System.out.println(this.autoEngine.list());
    }

    @Override
    public void robotInit() {
        //Initialize the gamepads and subsystems
        this.gamepad1 = new Gamepad(Driver.ONE);
        this.gamepad2 = new Gamepad(Driver.TWO);

        this.driveTrain = new DriveTrain();
        this.launcher = new Launcher();
    }

    @Override
    public void autonomous() {
        autoEngine.executeAuto();
    }

    @Override
    public void teleopInit() {

    }

    @Override
    public void teleopPeriodic() {
        if(this.gamepad1.getButtonState(GamepadButton.RIGHT_BUMPER)) {//If the right bumper is pressed, activate forward camera
            //this.gamepad1.getWpiJoystick().setRumble(Joystick.RumbleType.kLeftRumble, 1f);//Test
            this.imageSendbackThread.setShooterCam(false);
        } else if(this.gamepad1.getButtonState(GamepadButton.LEFT_BUMPER)) {//If the left bumper is pressed, activate the reverse camera
            //this.gamepad1.getWpiJoystick().setRumble(Joystick.RumbleType.kRightRumble, 1f);//Test
            this.imageSendbackThread.setShooterCam(true);
        } else {//Test
            //this.gamepad1.getWpiJoystick().setRumble(Joystick.RumbleType.kLeftRumble, 0);
            //this.gamepad1.getWpiJoystick().setRumble(Joystick.RumbleType.kRightRumble, 0);
        }

        if(this.gamepad1.getButtonState(GamepadButton.START)) {//If the start button is pressed
            this.driveTrain.setReversed(false);//Set the drive train to forwards
        } else if(this.gamepad1.getButtonState(GamepadButton.BACK)) {//If the back button is pressed
            this.driveTrain.setReversed(true);//Set the drive train to reverse
        }

        //this.driveTrain.setLeft(gamepad1.getAxis(Hand.LEFT).getY());
        //this.driveTrain.setRight(gamepad1.getAxis(Hand.RIGHT).getY());
        //Drive
        this.driveTrain.forzaDrive(-gamepad1.getAxis(Hand.RIGHT).getX(), gamepad1.getAxis(Hand.LEFT).getY());

        //Set the white collecting wheel power. Use right - left triggers to use both inputs
        this.launcher.setCollectorSpeed(gamepad2.getTrigger(Hand.RIGHT) - gamepad2.getTrigger(Hand.LEFT));
        //If the user is holding down the B button, activate the launcher.
        this.launcher.setLaunching(gamepad2.getButtonState(GamepadButton.B));

        if(this.isShotInPlace()) {
            this.gamepad1.setRumble(RumbleType.LIGHT, 1);
        } else {
            this.gamepad1.setRumble(RumbleType.LIGHT, 0);
        }

        if(this.launcher.isAtFullSpeed()) {
            this.gamepad2.setRumble(RumbleType.LIGHT, 1);
        } else {
            this.gamepad2.setRumble(RumbleType.LIGHT, 0);
        }
    }

    private boolean isShotInPlace() {
        return new Date().getSeconds() < 5;//Test. Just to test the rumble
    }

//        $$$$          $$$            $       $$       $$
//      $$$$$$$$        $$$          $$$$$     $$       $$
//     $$$    $$$      $$ $$        $$$$$$$    $$       $$
//     $$      $$$     $$ $$       $$$ $ $$$   $$       $$
//    $$$       $$     $$ $$       $$  $  $$   $$       $$
//    $$              $$   $$      $$  $       $$       $$
//    $$              $$   $$      $$$ $       $$$$$$$$$$$
//    $$              $$   $$       $$$$$      $$$$$$$$$$$
//    $$             $$     $$        $$$$$    $$       $$
//    $$             $$$$$$$$$         $ $$$   $$       $$
//    $$$       $$  $$$$$$$$$$$        $  $$   $$       $$
//     $$      $$   $$       $$        $  $$   $$       $$
//     $$$    $$$   $$       $$    $$  $  $$   $$       $$
//      $$$$$$$$   $$         $$   $$$ $ $$$   $$       $$
//        $$$$     $$         $$    $$$$$$$    $$       $$
//                                     $

//         AAA       AA         AAAAAAAAAA  AA       AA  AA       AA      AAA
//         AAA       AA         AAAAAAAAAA  AAA      AA  AAA      AA      AAA
//        AA AA      AA         AA          AAAA     AA  AAAA     AA     AA AA
//        AA AA      AA         AA          AAAA     AA  AAAA     AA     AA AA
//        AA AA      AA         AA          AA AA    AA  AA AA    AA     AA AA
//       AA   AA     AA         AA          AA AA    AA  AA AA    AA    AA   AA
//       AA   AA     AA         AAAAAAAAA   AA  AA   AA  AA  AA   AA    AA   AA
//       AA   AA     AA         AAAAAAAAA   AA  AAA  AA  AA  AAA  AA    AA   AA
//      AA     AA    AA         AA          AA   AA  AA  AA   AA  AA   AA     AA
//      AAAAAAAAA    AA         AA          AA    AA AA  AA    AA AA   AAAAAAAAA
//     AAAAAAAAAAA   AA         AA          AA    AA AA  AA    AA AA  AAAAAAAAAAA
//     AA       AA   AA         AA          AA     AAAA  AA     AAAA  AA       AA
//     AA       AA   AA         AA          AA     AAAA  AA     AAAA  AA       AA
//    AA         AA  AAAAAAAA   AAAAAAAAAA  AA      AAA  AA      AAA AA         AA
//    AA         AA  AAAAAAAA   AAAAAAAAAA  AA       AA  AA       AA AA         AA


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
