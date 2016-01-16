package org.torque.stronghold.test;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotBase;
import org.torque.lib.def.Driver;
import org.torque.lib.driverstation.hardware.gamepad.Gamepad;

/**
 * Created by Jaxon A. Brown on 1/9/2016.
 */
public class TestRobot extends RobotBase {
    @Override
    public void startCompetition() {
        Gamepad gamepad = new Gamepad(Driver.ONE);

    }
}
