package org.torque.lib.driverstation.hardware.gamepad;

/**
 * Buttons on the Logitech Gamepad.
 * @author Jaxon A Brown
 */
public enum GamepadButton {
    A(0),
    B(0),
    X(0),
    Y(0),
    START(0),
    BACK(0),
    LEFT_JOYSTICK(0),
    RIGHT_JOYSTICK(0),
    LEFT_BUMPER(0),
    RIGHT_BUMPER(0);

    private int port;

    GamepadButton(int port) {
        this.port = port;
    }

    /**
     * Get the port that the button is registered to.
     * You can find this value in the Driver Station.
     * @return Raw port value
     */
    public int getPort() {
        return port;
    }
}
