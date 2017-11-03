package org.usfirst.frc.team1836.robot.auto.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team1836.robot.Constants;
import org.usfirst.frc.team1836.robot.auto.commands.MagicMotionDrive;

/**
 *
 */
public class MagicCenterAuto extends CommandGroup {

    MagicMotionDrive magicMotionDrive;

    public MagicCenterAuto() {
        addSequential(new MagicMotionDrive(Constants.Auto.CENTER_AUTO_DISTANCE));
    }


}
