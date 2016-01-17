package org.torque.lib.driverstation.hardware.gamepad;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import edu.wpi.first.wpilibj.Joystick;
import org.apache.commons.math3.analysis.interpolation.HermiteInterpolator;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Jaxon A Brown
 */
public class GamepadAxis {
    private Joystick wpiJoystick;
    private int port;

    private Cache<Long, Double> recentPositions;

    public GamepadAxis(Joystick wpiJoystick, int port) {
        this.wpiJoystick = wpiJoystick;
        this.port = port;

        this.recentPositions = CacheBuilder.newBuilder().expireAfterWrite(500, TimeUnit.MILLISECONDS).build();
    }

    public double getRaw() {
        return wpiJoystick.getRawAxis(port);
    }

    public double getMagnitude() {
        return Math.abs(getRaw());
    }

    public synchronized void updateCalculus() {
        recentPositions.put(System.currentTimeMillis(), getRaw());
    }

    public synchronized double getVelocity() {
        HermiteInterpolator hermiteInterpolator = new HermiteInterpolator();
        for(Map.Entry<Long, Double> entry : recentPositions.asMap().entrySet()) {
            hermiteInterpolator.addSamplePoint(entry.getKey(), new double[]{entry.getValue()});
        }
        return hermiteInterpolator.getPolynomials()[0].derivative().value(System.currentTimeMillis());//TODO not sure if this works
    }
}
