/*
 * FRC 4931 (http://www.evilletech.com)
 *
 * Open source software. Licensed under the FIRST BSD license file in the
 * root directory of this project's Git repository.
 */
package org.frc4931.robot;

import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.PIDController;

/**
 * 
 */
public class PIDCont implements Controller{
    private DoubleSupplier input;
    private DoubleConsumer output;
    
    private PIDController control;
    
    public PIDCont() {
        
    }
    
    @Override
    public boolean update(long time) {
        return false;
    }

    @Override
    public boolean onTarget() {
        return control.onTarget();
    }

    @Override
    public void enable(DoubleSupplier input, DoubleConsumer output) {
        this.input = input;
        this.output = output;
        if(control==null) {
        control = new PIDController(0.1, 0, 0, input::getAsDouble, output::accept);
        control.setAbsoluteTolerance(5);
        control.setInputRange(0, 90);
        control.setOutputRange(-0.25, 0.75);
        control.enable();
        }
    }
    
    @Override
    public void stop() {
        control.disable();
    }

    @Override
    public void setSetpoint(double setPoint) {
        control.enable();
        control.setSetpoint(setPoint);
    }

    @Override
    public double getError() {
        return control.getError();
    }

    @Override
    public boolean isAt(double angle) {
        return Math.abs(input.getAsDouble() - angle)<10;
    }
}
