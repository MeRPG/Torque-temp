package org.torque.stronghold.autoProgram;

import org.torque.stronghold.Robot;

/**
 * Autonomous program for the AutoEngine.
 */
public interface AutoProgram {
    /**
     * Formats a dashboard key
     * @param input input key name
     * @return formatted key name with program name for separation
     */
    default String formatDashboardKey(String input) {
        return "<" + getName() + "> " + input;
    }

    /**
     * Configure the name of this program
     * @return the name of the program
     */
    String getName();

    /**
     * Execute the program
     * @param robot Robot on which to execute the autonomous
     */
    void run(Robot robot);
}
