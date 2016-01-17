package org.torque.lib.driverstation.hardware.gamepad;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import edu.wpi.first.wpilibj.Joystick;
import org.apache.commons.math3.analysis.interpolation.HermiteInterpolator;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Joystick on a Logitech Gamepad.
 * @author Jaxon A Brown
 */
public class GamepadAxis {
    private Joystick wpiJoystick;

    private Cache<Long, Double> recentPositionsX;
    private Cache<Long, Double> recentPositionsY;

    GamepadAxis(Joystick wpiJoystick) {
        this.wpiJoystick = wpiJoystick;

        this.recentPositionsX = CacheBuilder.newBuilder().expireAfterWrite(500, TimeUnit.MILLISECONDS).build();
        this.recentPositionsY = CacheBuilder.newBuilder().expireAfterWrite(500, TimeUnit.MILLISECONDS).build();
    }

    /**
     * Get the x value of the axis
     * @return [-1,1] double on the x axis
     */
    public double getX() {
        return wpiJoystick.getX();
    }

    /**
     * Get the y value of the axis
     * @return [-1,1] double on the y axis
     */
    public double getY() {
        return wpiJoystick.getY();
    }

    /**
     * Get the magnitude of the x value of the axis
     * @return [0,1] double
     */
    public double getXMagnitude() {
        return Math.abs(getX());
    }

    /**
     * Get the magnitude of the y value of the axis
     * @return [0,1] double
     */
    public double getYMagnitude() {
        return Math.abs(getY());
    }

    /**
     * This method is not recommended for use yet.
     */
    public synchronized void updateCalculus() {
        recentPositionsX.put(System.currentTimeMillis(), getX());
        recentPositionsY.put(System.currentTimeMillis(), getY());
    }

    /**
     * This method is not recommended for use yet.
     */
    public synchronized double getXVelocity() {
        HermiteInterpolator hermiteInterpolator = new HermiteInterpolator();
        for(Map.Entry<Long, Double> entry : recentPositionsX.asMap().entrySet()) {
            hermiteInterpolator.addSamplePoint(entry.getKey(), new double[]{entry.getValue()});
        }
        return hermiteInterpolator.getPolynomials()[0].derivative().value(System.currentTimeMillis());//TODO not sure if this works
    }

    /**
     * This method is not recommended for use yet.
     */
    public synchronized double getYVelocity() {
        HermiteInterpolator hermiteInterpolator = new HermiteInterpolator();
        for(Map.Entry<Long, Double> entry : recentPositionsY.asMap().entrySet()) {
            hermiteInterpolator.addSamplePoint(entry.getKey(), new double[]{entry.getValue()});
        }
        return hermiteInterpolator.getPolynomials()[0].derivative().value(System.currentTimeMillis());//TODO not sure if this works
    }
}
