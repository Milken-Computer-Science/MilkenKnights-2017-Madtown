package org.usfirst.frc.team1836.robot.auto.commands;

import org.usfirst.frc.team1836.robot.subsystems.Drive;
import org.usfirst.frc.team1836.robot.auto.routines.Path;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StraightPathFollower extends Command {

    private int cycle;
    public StraightPathFollower() {
      
    }

    // Called just before this Command runs the first time
    protected void initialize() {
      cycle = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
      Drive.getInstance().setDriveVelocity(Path.Points[cycle][1]);
      cycle++;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
