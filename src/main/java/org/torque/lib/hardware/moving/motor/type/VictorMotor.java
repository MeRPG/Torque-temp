package org.torque.lib.hardware.moving.motor.type;

import edu.wpi.first.wpilibj.Victor;
import org.torque.lib.hardware.moving.motor.Motor;

/**
 * Victor Motor.
 * @author Jaxon A Brown
 */
public class VictorMotor extends Motor {
    private Victor wpiController;

    /**
     * Victor motor controller.
     * @param channel motor controller's channel.
     */
    public VictorMotor(int channel) {
        this.wpiController = new Victor(channel);
    }

    @Override
    public synchronized void setPower(double power) {
        if(!isDead()) {
            this.wpiController.set(power);
        }
    }

    @Override
    public synchronized double getPower() {
        return this.wpiController.get();
    }

    @Override
    public synchronized void setReversed(boolean reversed) {
        this.wpiController.setInverted(reversed);
    }

    @Override
    public synchronized boolean getReversed() {
        return this.wpiController.getInverted();
    }

    @Override
    public synchronized void stop() {
        setPower(0D);
    }
}
