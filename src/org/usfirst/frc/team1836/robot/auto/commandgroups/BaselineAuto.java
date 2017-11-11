package org.usfirst.frc.team1836.robot.auto.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team1836.robot.Constants;
import org.usfirst.frc.team1836.robot.auto.commands.TimedDrive;
import org.usfirst.frc.team1836.robot.subsystems.GearMech;

/**
 *
 */
public class BaselineAuto extends CommandGroup {

  public BaselineAuto() {
    addSequential(new TimedDrive(Constants.Auto.BASELINE_TIME));
  }
}
