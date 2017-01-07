package org.torque.lib.hardware.moving.motor;

import org.torque.lib.concurrent.Timer;

import java.util.concurrent.TimeUnit;

/**
 * Created by Jaxon A Brown on 3/5/2016.
 */
public class MotorRevThread extends Thread {
    private MotorLink target;
    private double setPoint;

    public MotorRevThread(MotorLink target) {
        this.target = target;
        this.setPoint = target.getPower();
    }

    public void setPower(double power) {
        this.setPoint = power;
    }

    @Override
    public void run() {
        System.out.println("updating motor thread");
        double diff = setPoint - target.getPower();
        double inc = diff < 0 ? Math.max(diff, 0.05) : Math.min(0.05, diff);
        target.setPower(target.getPower() + inc);
        Timer.wait(12, TimeUnit.MILLISECONDS);
    }
}
