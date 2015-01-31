/*
 * FRC 4931 (http://www.evilletech.com)
 *
 * Open source software. Licensed under the FIRST BSD license file in the
 * root directory of this project's Git repository.
 */
package org.frc4931.robot.driver;

import org.frc4931.robot.component.Switch;

/**
 * Holds the state of the operator interface.
 * 
 * @author Zach Anderson
 */
public class OperatorInterface {
    /* Cheesy */
    public final AnalogAxis wheel;
    public final AnalogAxis throttle;

    /* Arcade */
    public final AnalogAxis driveSpeed;
    public final AnalogAxis turnSpeed;

    public final Switch toggleClaw;
    public final Switch toggleLift;
    public final Switch toggleRamp;
    public final Switch toggleRails;

    public final Switch writeData;
    public final Switch quickTurn;
    
    // Modified to fit driver preference
    public OperatorInterface(Joystick joy){
        wheel = joy.getYaw();
        throttle = joy.getPitch();

        driveSpeed = joy.getPitch();
        turnSpeed = joy.getRoll();
        
        quickTurn = joy.getButton(5);
        
        writeData = joy.getButton(6);
        
        toggleClaw = joy.getButton(0);
        toggleLift = joy.getButton(1);
        toggleRamp = joy.getButton(2);
        toggleRails = joy.getButton(3);
    }
}
