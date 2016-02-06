package org.torque.lib.driverstation.hardware.gamepad;

/**
 * Buttons on the Logitech Gamepad.
 * @author Jaxon A Brown
 */
public enum GamepadButton {
    A(0),
    B(1),
    X(2),
    Y(3),
    START(7),
    BACK(6),
    LEFT_JOYSTICK(8),
    RIGHT_JOYSTICK(9),
    LEFT_BUMPER(4),
    RIGHT_BUMPER(5);

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
