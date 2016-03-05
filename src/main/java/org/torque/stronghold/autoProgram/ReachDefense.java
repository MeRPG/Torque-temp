package org.torque.stronghold.autoProgram;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.torque.lib.concurrent.Timer;
import org.torque.stronghold.Robot;

import java.util.concurrent.TimeUnit;

/**
 * Created by Jaxon A Brown on 2/20/2016.
 */
public class ReachDefense extends AutoProgram {
    private final String TRAVEL_TIME_KEY;
    private final String TRAVEL_SPEED_KEY;

    public ReachDefense() {
        TRAVEL_TIME_KEY = formatDashboardKey("Travel Time (s)");
        TRAVEL_SPEED_KEY = formatDashboardKey("Travel Speed");
        SmartDashboard.putNumber(TRAVEL_TIME_KEY, 1);
        SmartDashboard.putNumber(TRAVEL_SPEED_KEY, 0.5);
    }

    @Override
    public String getName() {
        return "Reach Defense";
    }

    @Override
    public void run(Robot robot) {
        int time = (int) SmartDashboard.getNumber(TRAVEL_TIME_KEY);
        double speed = SmartDashboard.getNumber(TRAVEL_SPEED_KEY);

        robot.driveTrain.setLeft(speed);
        robot.driveTrain.setRight(speed);

        Timer.wait(time, TimeUnit.SECONDS);

        robot.driveTrain.setLeft(0);
        robot.driveTrain.setRight(0);
    }
}