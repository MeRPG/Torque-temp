package org.torque.lib.driverstation.hardware.gamepad;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Created by Jaxon A Brown on 2/20/2016.
 */
public enum RumbleType {
    LIGHT(Joystick.RumbleType.kRightRumble),
    DEEP(Joystick.RumbleType.kLeftRumble);

    private Joystick.RumbleType type;
    RumbleType(Joystick.RumbleType type) {
        this.type = type;
    }

    public Joystick.RumbleType getType() {
        return type;
    }
}
