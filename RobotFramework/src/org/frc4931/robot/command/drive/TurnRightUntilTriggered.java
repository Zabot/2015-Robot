/*
 * FRC 4931 (http://www.evilletech.com)
 *
 * Open source software. Licensed under the FIRST BSD license file in the
 * root directory of this project's Git repository.
 */
package org.frc4931.robot.command.drive;

import org.frc4931.robot.command.CommandWithSwitch;
import org.frc4931.robot.component.Switch;
import org.frc4931.robot.subsystem.DriveSystem;

public class TurnRightUntilTriggered extends CommandWithSwitch {
    private final DriveSystem driveSystem;
    private final double speed;

    public TurnRightUntilTriggered(DriveSystem driveSystem, double speed, Switch swtch) {
        super(swtch);
        this.driveSystem = driveSystem;
        this.speed = speed;
        requires(driveSystem);
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        driveSystem.arcade(0.0, Math.abs(speed));
    }

    @Override
    protected void end() {
        driveSystem.stop();
    }
}
