/*
 * FRC 4931 (http://www.evilletech.com)
 *
 * Open source software. Licensed under the FIRST BSD license file in the
 * root directory of this project's Git repository.
 */
package org.frc4931.robot.hardware;

import org.frc4931.robot.component.MotorWithAngle;

import edu.wpi.first.wpilibj.CANTalon;

/**
 * Talon speed controller with position sensor
 * @author Nathan Brown
 */
class HardwareTalonSRX implements MotorWithAngle{
    private final CANTalon motor;
    private double tolerance;
    
    HardwareTalonSRX(int port){
        this.motor = new CANTalon(port);
    }
    
    @Override
    public double getAngle(){
        return motor.getPosition();
    }

    @Override
    public void setAngle(double angle) {
        motor.setPosition(angle);
    }

    @Override
    public void setSpeed(double speed) {
        motor.set(speed);
    }

    @Override
    public double getSpeed() {
        return motor.get();
    }

    @Override
    public short getSpeedAsShort() {
        return (short)(motor.get()*1000);
    }

    @Override
    public void setTolerance(double tol){
        tolerance = tol;
    }
    
    @Override
    public double getTolerance(){
        return tolerance;
    }
}
