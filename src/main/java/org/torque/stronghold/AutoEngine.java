package org.torque.stronghold;

import com.google.common.collect.Maps;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.torque.lib.driverstation.hardware.gamepad.Gamepad;
import org.torque.stronghold.autoProgram.AutoProgram;

import java.util.Map;

/**
 * Created by Jaxon A Brown on 2/20/2016.
 */
public class AutoEngine {
    private Robot robot;
    private Map<Integer, AutoProgram> registeredPrograms;

    public AutoEngine(Robot robot) {
        this.robot = robot;
        this.registeredPrograms = Maps.newHashMap();

        SmartDashboard.putNumber("Auto Mode", 0);
    }

    public void registerAuto(AutoProgram autoProgram) {
        this.registeredPrograms.put(registeredPrograms.size(), autoProgram);
    }

    public String list() {
        String list = "";
        for(Map.Entry<Integer, AutoProgram> entry : registeredPrograms.entrySet()) {
            list += entry.getKey() + ": " + entry.getValue().getName() + "\n";
        }
        return list;
    }

    public void executeAuto() {
        registeredPrograms.get((int) SmartDashboard.getNumber("Auto Mode")).run(robot);
    }
}
