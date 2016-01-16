package org.torque.lib.hardware.moving.motor;

/**
 * Created by Jaxon A Brown on 1/16/2016.
 */
public abstract class Motor {
    private boolean dead;

    public Motor() {
        this.dead = false;
    }

    public abstract void setPower(double power);
    public abstract double getPower();
    public abstract void setReversed(boolean reversed);
    public abstract boolean getReversed();
    public abstract void stop();

    public void setDead(boolean dead) {
        this.dead = dead;
        if(this.dead) {
            stop();
        }
    }
    public boolean isDead() {
        return this.dead;
    }
}
