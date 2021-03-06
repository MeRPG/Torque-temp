package org.torque.stronghold.autoProgram;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.torque.lib.concurrent.Timer;
import org.torque.stronghold.Robot;

import java.util.concurrent.TimeUnit;

/**
 * Crosses the cheval de frise
 */
public class ChevalDeFrise implements AutoProgram {
    private final String TRAVEL_TIME_REACH_KEY;
    private final String TRAVEL_TIME_CROSS_KEY;
    private final String TRAVEL_SPEED_KEY;

    public ChevalDeFrise() {
        TRAVEL_TIME_REACH_KEY = formatDashboardKey("Travel Time Reach (s)");
        TRAVEL_TIME_CROSS_KEY = formatDashboardKey("Travel Time Cross (s)");
        TRAVEL_SPEED_KEY = formatDashboardKey("Travel Speed");
        SmartDashboard.putNumber(TRAVEL_TIME_REACH_KEY, 1);
        SmartDashboard.putNumber(TRAVEL_TIME_CROSS_KEY, 3);
        SmartDashboard.putNumber(TRAVEL_SPEED_KEY, 0.5);
    }

    @Override
    public String getName() {
        return "Cheval De Frise";
    }

    @Override
    public void run(Robot robot) {
        int time_reach = (int) SmartDashboard.getNumber(TRAVEL_TIME_REACH_KEY);
        int time_cross = (int) SmartDashboard.getNumber(TRAVEL_TIME_CROSS_KEY);
        double speed = SmartDashboard.getNumber(TRAVEL_SPEED_KEY);

        robot.driveTrain.setLeft(speed);
        robot.driveTrain.setRight(speed);

        Timer.wait(time_reach, TimeUnit.SECONDS);

        robot.driveTrain.setLeft(0);
        robot.driveTrain.setRight(0);

        robot.arm.setArmPower(-0.4);
        Timer.wait(1, TimeUnit.SECONDS);
        robot.arm.setArmPower(0);

        robot.driveTrain.setLeft(speed);
        robot.driveTrain.setRight(speed);

        Timer.wait(time_cross, TimeUnit.SECONDS);

        robot.driveTrain.setLeft(0);
        robot.driveTrain.setRight(0);
    }
}
