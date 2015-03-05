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
public interface Controller extends Updateable{
    
    public boolean onTarget();

    public void enable(DoubleSupplier intput, DoubleConsumer output);
    
    public default void start(){};
    
    public void stop();
    
    public void setSetpoint(double setPoint);
    
    public double getError();
    
    public boolean isAt(double angle);
}
