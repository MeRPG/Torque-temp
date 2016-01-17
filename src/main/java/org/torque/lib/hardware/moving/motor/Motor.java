package org.torque.lib.hardware.moving.motor;

/**
 * Represents a motor (controller).
 * @author Jaxon A Brown
 */
public abstract class Motor {
    private boolean dead;

    protected Motor() {
        this.dead = false;
    }

    /**
     * Set the power of the motor
     * @param power double power, [-1,1]
     */
    public abstract void setPower(double power);

    /**
     * Get the current power on the motor
     * @return double power, [-1,1]
     */
    public abstract double getPower();

    /**
     * Set the reverse state of this motor
     * @param reversed true indicates that motor input should be reversed.
     */
    public abstract void setReversed(boolean reversed);

    /**
     * Get the reverse state of this motor
     * @return true indicates that motor input should be reversed.
     */
    public abstract boolean getReversed();

    /**
     * Stop the motor completely.
     */
    public abstract void stop();

    /**
     * Sets the motor as dead
     * @param dead true will stop motor motions
     */
    public synchronized void setDead(boolean dead) {
        this.dead = dead;
        if(this.dead) {
            stop();
        }
    }

    /**
     * Checks weather or not the motor is dead
     * @return true will stop motor motions
     */
    public synchronized boolean isDead() {
        return this.dead;
    }
}
