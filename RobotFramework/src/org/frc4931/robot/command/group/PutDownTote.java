/*
 * FRC 4931 (http://www.evilletech.com)
 *
 * Open source software. Licensed under the FIRST BSD license file in the
 * root directory of this project's Git repository.
 */
package org.frc4931.robot.command.group;

import org.frc4931.robot.command.CloseGuardrail;
import org.frc4931.robot.command.LowerStacker;
import org.frc4931.robot.command.OpenGuardrail;
import org.frc4931.robot.command.RaiseStacker;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 */
public class PutDownTote extends CommandGroup {
    public PutDownTote(){
        addSequential(new LowerStacker());
        addSequential(new OpenGuardrail());
        //TODO move back two feet
        addSequential(new CloseGuardrail());
        addSequential(new RaiseStacker());
        
    }

}
