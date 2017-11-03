package org.usfirst.frc.team1836.robot.auto.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team1836.robot.auto.commands.CenterPathFollower;

public class TrajectoryCenterAuto extends CommandGroup {

    public TrajectoryCenterAuto() {
        addSequential(new CenterPathFollower());
    }
}


