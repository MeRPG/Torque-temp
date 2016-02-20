package org.torque.stronghold.autoProgram;

import org.torque.stronghold.Robot;

/**
 * Created by Jaxon A Brown on 2/20/2016.
 */
public abstract class AutoProgram {
    protected final String formatDashboardKey(String input) {
        return "<" + getName() + "> " + input;
    }
    public abstract String getName();
    public abstract void run(Robot robot);
}
