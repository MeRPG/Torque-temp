package org.torque.lib.driverstation.hardware.gamepad;

/**
 * Buttons on the Logitech Gamepad.
 * @author Jaxon A Brown
 */
public enum GamepadButton {
    A(1),
    B(2),
    X(3),
    Y(4),
    START(8),
    BACK(7),
    LEFT_JOYSTICK(9),
    RIGHT_JOYSTICK(10),
    LEFT_BUMPER(5),
    RIGHT_BUMPER(6);

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
