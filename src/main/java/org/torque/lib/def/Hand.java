package org.torque.lib.def;

import edu.wpi.first.wpilibj.GenericHID;

/**
 * Simple as that. HI hand.
 * @author Jaxon A Brown
 */
public enum Hand {
    LEFT,
    RIGHT;

    public int getRawAxisPort() {
        if(this == LEFT) {
            return 0;
        } else {
            return 1;
        }
    }
}
