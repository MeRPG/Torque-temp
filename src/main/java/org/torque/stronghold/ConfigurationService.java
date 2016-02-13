package org.torque.stronghold;

/**
 * This class should be used to configure values used in the robot.
 * I expect to put values like ports, powers, limits, and thresholds in here.
 * @author Jaxon A Brown
 */
public class ConfigurationService {
    public static int FORWARD_FACING_CAMERA_ID;
    public static int REAR_FACING_CAMERA_ID;

    public static double JOYSTICK_DEADZONE = 0.1;

    public static int PORT_DRIVE_LEFT_FRONT = 0;
    public static int PORT_DRIVE_LEFT_REAR = 1;
    public static int PORT_DRIVE_RIGHT_FRONT = 2;
    public static int PORT_DRIVE_RIGHT_REAR = 3;
    public static int PORT_LAUNCH_COLLECTOR = 4;
    public static int PORT_LAUNCH_LOWER_LAUNCH = 5;
    public static int PORT_LAUNCH_UPPER_LAUNCH = 6;

    public static double LAUNCHER_LAUNCH_POWER = 1;
}
