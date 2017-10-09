package org.usfirst.frc.team1836.robot.auto.commands;

import org.usfirst.frc.team1836.robot.subsystems.Drive;
import org.usfirst.frc.team1836.robot.util.TrajectoryPoint;
import org.usfirst.frc.team1836.robot.auto.routines.MotionProfile;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StraightPathFollower extends Command {

    private int cycle;
    public StraightPathFollower() {
      
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
      cycle = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	if(cycle < MotionProfile.kNumPoints) {
      Drive.getInstance().setDriveTrajectory(new TrajectoryPoint(MotionProfile.Points[cycle][0], MotionProfile.Points[cycle][0], cycle+1, MotionProfile.Points[cycle][2]));
      cycle++;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
     	return cycle >= MotionProfile.kNumPoints;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
