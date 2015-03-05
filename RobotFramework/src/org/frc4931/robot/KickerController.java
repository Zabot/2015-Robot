/*
 * FRC 4931 (http://www.evilletech.com)
 *
 * Open source software. Licensed under the FIRST BSD license file in the
 * root directory of this project's Git repository.
 */
package org.frc4931.robot;

import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;

/**
 * 
 */
public class KickerController implements Controller{
    private final double   tolerance; // = 5;
    private final double[] holdSpeeds;//
    private final double[] moveSpeeds;//
    
    private final IntSupplier toteCount;
    
    private DoubleSupplier input;
    private DoubleConsumer output;
    
    private double setPoint;
    private double error;
    
    private volatile boolean shouldStop;
    
    public KickerController(double tolerance, double[] moveSpeeds, double[] holdSpeeds, IntSupplier toteCount) {//DoubleSupplier input, DoubleConsumer output, IntSupplier toteCount) {
        this.tolerance = tolerance;
        this.moveSpeeds = moveSpeeds;
        this.holdSpeeds = holdSpeeds;
        this.toteCount = toteCount;
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
    public boolean update(long time) {
        if(setPoint != 0.0) {
        error = input.getAsDouble() - setPoint;
        if(error < 0)
            output.accept(moveSpeeds[toteCount.getAsInt()]);
        else if(error > tolerance)
            output.accept(-moveSpeeds[0]);
        else
            output.accept(holdSpeeds[toteCount.getAsInt()]);
        return shouldStop;
        }else {
            output.accept(-moveSpeeds[0]);
            return shouldStop;
        }
    }

    @Override
    public void setSetpoint(double setPoint) {
        this.setPoint = setPoint;
    }

    @Override
    public boolean onTarget() {
        if(error < 0)
            return false;
        else if(error > tolerance)
            return false;
        else
            return true;
    }

    @Override
    public double getError() {
        return error;
    }

    @Override
    public boolean isAt(double angle) {
        double e = input.getAsDouble() - angle;
        if(e < 0)
            return false;
        else if(e > tolerance)
            return false;
        else
            return true;
    }
}
