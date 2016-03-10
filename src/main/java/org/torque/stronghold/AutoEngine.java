package org.torque.stronghold;

import com.google.common.collect.Maps;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.torque.lib.driverstation.hardware.gamepad.Gamepad;
import org.torque.stronghold.autoProgram.AutoProgram;

import java.util.Map;

/**
 * Auto Engine will keep a list of auto programs and execute the one defined in the dashboard.
 */
public class AutoEngine {
    private Robot robot;
    private Map<Integer, AutoProgram> registeredPrograms;

    /**
     * Create the AutoEngine and prepare it
     * @param robot The Robot which to run.
     */
    public AutoEngine(Robot robot) {
        this.robot = robot;
        this.registeredPrograms = Maps.newHashMap();

        SmartDashboard.putNumber("Auto Mode", 0);
    }

    /**
     * Register an autonomous program with the engine
     * @param autoProgram program to register
     */
    public void registerAuto(AutoProgram autoProgram) {
        this.registeredPrograms.put(registeredPrograms.size(), autoProgram);
    }

    /**
     * A string with a list of strings
     * @return a multiline list of autnomous programs
     */
    public String list() {
        String list = "";
        for(Map.Entry<Integer, AutoProgram> entry : registeredPrograms.entrySet()) {
            list += entry.getKey() + ": " + entry.getValue().getName() + "\n";
        }
        return list;
    }

    /**
     * Run the autonomous program whose index is defined in the engine
     * @throws NullPointerException when the index isn't defined
     */
    public void executeAuto() {
        registeredPrograms.get((int) SmartDashboard.getNumber("Auto Mode")).run(robot);
    }
}
