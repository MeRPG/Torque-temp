package org.torque.lib.def;

/**
 * Human drivers. Driver one and two represent the two controlling team members.
 * @author Jaxon A Brown
 */
public enum Driver {
    ONE(0),
    TWO(1);

    private int joystickPort;

    Driver(int joystickPort) {
        this.joystickPort = joystickPort;
    }

    /**
     * Used to get the joystick for this driver.
     * @return raw port value of joystick.
     */
    public int getJoystickPort() {
        return this.joystickPort;
    }
}
