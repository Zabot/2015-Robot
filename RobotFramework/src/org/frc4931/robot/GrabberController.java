/*
 * FRC 4931 (http://www.evilletech.com)
 *
 * Open source software. Licensed under the FIRST BSD license file in the
 * root directory of this project's Git repository.
 */
package org.frc4931.robot;

import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;

/**
 * 
 */
public class GrabberController implements Controller {
    private final double p;
    private final double tolerance;
    
    private final double raiseSpeed;
    private final double lowerSpeed;
    
    private final double maxAngle;
    
    private DoubleSupplier input;
    private DoubleConsumer output;
    
    private double setPoint;
    private double lastPoint;
    
    private volatile boolean shouldStop;
    
    public GrabberController(double p, double tolerance, double raiseSpeed, double lowerSpeed, double maxAngle) {
        this.p = p;
        this.tolerance = tolerance;

        this.raiseSpeed = raiseSpeed;
        this.lowerSpeed = lowerSpeed;
        
        this.maxAngle = maxAngle;
    }
    
    @Override
    public boolean update(long time) {
        lastPoint = input.getAsDouble();
        double error = lastPoint - setPoint;
//        System.out.println("error" +error + "prep: "+ Math.min(raiseSpeed, Math.abs(error*p))+", "+Math.max(-lowerSpeed, Math.abs(error * p)));
        
        if(error < -tolerance)
            if(error < -tolerance * 2)
                output.accept(raiseSpeed);
            else
                output.accept(raiseSpeed/6);
        
        else if(error > tolerance)
            if(error > tolerance * 2)
                output.accept(-lowerSpeed);
            else
                output.accept(-lowerSpeed/2);
        else
            output.accept(0);
        
        return shouldStop;
    }

    @Override
    public boolean onTarget() {
        return Math.abs(lastPoint - setPoint) <= tolerance;
    }

    @Override
    public void enable(DoubleSupplier input, DoubleConsumer output) {
        this.input = input;
        this.output = output;
        shouldStop = false;
        UpdateableManager.getInstancce().register(this);
    }

    @Override
    public void stop() {
        shouldStop = true;
    }

    @Override
    public void setSetpoint(double setPoint) {
        this.setPoint = setPoint;
    }

    @Override
    public double getError() {
        return lastPoint;
    }

    @Override
    public boolean isAt(double angle) {
        return Math.abs(lastPoint - angle) <= tolerance;
    }

}
