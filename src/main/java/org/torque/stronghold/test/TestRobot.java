package org.torque.stronghold.test;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotBase;

/**
 * Created by Jaxon A. Brown on 1/9/2016.
 */
public class TestRobot extends RobotBase {
    @Override
    public void startCompetition() {
        Joystick joystick = new Joystick(1);
    }
}
