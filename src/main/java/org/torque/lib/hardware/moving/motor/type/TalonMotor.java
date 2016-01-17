package org.torque.lib.hardware.moving.motor.type;

import edu.wpi.first.wpilibj.TalonSRX;
import org.torque.lib.hardware.moving.motor.Motor;

/**
 * Created by Jaxon A Brown on 1/16/2016.
 */
public class TalonMotor extends Motor {
    private TalonSRX wpiController;

    public TalonMotor(int channel) {
        this.wpiController = new TalonSRX(channel);
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
