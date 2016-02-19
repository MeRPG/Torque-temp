package org.torque.lib.driverstation.hardware.gamepad;

import edu.wpi.first.wpilibj.Joystick;
import org.torque.lib.def.Driver;
import org.torque.lib.def.Hand;
import org.torque.stronghold.ConfigurationService;

/**
 * Logitech Gamepad wrapper class for the joystick.
 * @author Jaxon A Brown
 */
public class Gamepad {
    private Driver driver;
    private Joystick wpiJoystick;

    private GamepadAxis leftStick;
    private GamepadAxis rightStick;

    /**
     * Define a new Logitech gamepad.
     * @param driver The driver who will man this gamepad
     */
    public Gamepad(Driver driver) {
        this.driver = driver;
        this.wpiJoystick = new Joystick(this.driver.getJoystickPort());

        this.leftStick = new GamepadAxis(wpiJoystick, Hand.LEFT);
        this.rightStick = new GamepadAxis(wpiJoystick, Hand.RIGHT);
    }

    /**
     * Gets the state of a button on the Gamepad
     * @param buttonPort port of the button on the gamepad to check the state of
     * @return true if the button is depressed, false if it is not
     */
    public boolean getButtonState(int buttonPort) {
        return wpiJoystick.getRawButton(buttonPort);
    }

    /**
     * Gets the state of a button on the Gamepad
     * @param button the button of the gamepad to check the state of
     * @return true if the button is depressed, false if it is not
     */
    public boolean getButtonState(GamepadButton button) {
        return getButtonState(button.getPort());
    }

    public double getTrigger(Hand hand) {
        switch(hand) {
            case LEFT:
                return deadZone(wpiJoystick.getRawAxis(2));
            case RIGHT:
                return deadZone(wpiJoystick.getRawAxis(3));
            default:
                return 0;
        }
    }

    /**
     * Gets the GamepadAxis of the hand identified
     * @param hand left or right?
     * @return GamepadAxis object for this Gamepad
     */
    public GamepadAxis getAxis(Hand hand) {
        switch(hand) {
            case LEFT:
                return leftStick;
            case RIGHT:
                return rightStick;
            default:
                return null;
        }
    }

    public Joystick getWpiJoystick() {
        return wpiJoystick;
    }

    private static double deadZone(double input) {
        return Math.abs(input) <= ConfigurationService.JOYSTICK_DEADZONE ? 0 : input;
    }
}
