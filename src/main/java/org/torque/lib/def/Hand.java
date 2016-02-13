package org.torque.lib.def;

/**
 * Simple as that. HI hand.
 * @author Jaxon A Brown
 */
public enum Hand {
    LEFT(0, 1),
    RIGHT(2, 3);

    private int xPort;
    private int yPort;
    Hand(int xPort, int yPort) {
        this.xPort = xPort;
        this.yPort = yPort;
    }

    public int getRawXAxisPort() {
        return xPort;
    }

    public int getRawYAxisPort() {
        return yPort;
    }
}
