package org.torque.stronghold;

import com.ni.vision.NIVision;
import org.torque.lib.concurrent.Scheduler;
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
        ConfigurationService.FORWARD_FACING_CAMERA_ID =
                NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController);

        Scheduler.scheduleRepeatingTask(new ImageSendbackThread(), 1, 50);
    }

    @Override
    public void disabledInit() {

    }

    @Override
    public void teleopInit() {

    }

    @Override
    public void testInit() {

    }

    @Override
    public void disabledPeriodic() {

    }

    @Override
    public void autonomous() {

    }

    @Override
    public void teleopPeriodic() {

    }

    @Override
    public void testPeriodic() {

    }
}
