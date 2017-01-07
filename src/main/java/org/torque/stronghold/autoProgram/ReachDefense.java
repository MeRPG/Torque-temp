package org.torque.stronghold.autoProgram;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.torque.lib.concurrent.Timer;
import org.torque.stronghold.Robot;

import java.util.concurrent.TimeUnit;

/**
 * Approaches a defense, but does not cross it
 */
public class ReachDefense implements AutoProgram {
    private final String TRAVEL_TIME_KEY;
    private final String TRAVEL_SPEED_KEY;

    public ReachDefense() {
        TRAVEL_TIME_KEY = formatDashboardKey("Travel Time (s)");
        TRAVEL_SPEED_KEY = formatDashboardKey("Travel Speed");
        SmartDashboard.putNumber(TRAVEL_TIME_KEY, 2.5);
        SmartDashboard.putNumber(TRAVEL_SPEED_KEY, -0.5);
    }

    @Override
    public String getName() {
        return "Reach Defense";
    }

    @Override
    public void run(Robot robot) {
        int time = (int) (SmartDashboard.getNumber(TRAVEL_TIME_KEY) * 1000);
        double speed = SmartDashboard.getNumber(TRAVEL_SPEED_KEY);

        robot.arm.setArmPower(-.4);

        robot.driveTrain.setLeft(speed);
        robot.driveTrain.setRight(speed);

        Timer.wait(time, TimeUnit.MILLISECONDS);


        robot.driveTrain.setLeft(-.5);
        robot.driveTrain.setRight(.5);
        Timer.waitMillis(1300);
        robot.driveTrain.setLeft(0);
        robot.driveTrain.setRight(0);

        Timer.waitMillis(500);

        robot.arm.setArmPower(-1);
        Timer.waitMillis(750);
        robot.arm.setArmPower(-.3);
        Timer.waitMillis(1000);
        robot.arm.setArmPower(0);
    }
}
