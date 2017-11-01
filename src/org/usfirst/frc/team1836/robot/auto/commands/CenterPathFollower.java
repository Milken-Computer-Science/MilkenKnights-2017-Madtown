package org.usfirst.frc.team1836.robot.auto.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1836.robot.auto.paths.CenterAutoPath;
import org.usfirst.frc.team1836.robot.subsystems.Drive;

/**
 *
 */
public class CenterPathFollower extends Command {

    public CenterPathFollower() {

    }

    // Called just before this Command runs the first time
    @Override protected void initialize() {
        CenterAutoPath x = new CenterAutoPath();
        Drive.getInstance().setDriveTrajectory(x,
            x.getLeftWheelTrajectory().getSegment(x.getLeftWheelTrajectory().getNumSegments()).pos);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override protected void execute() {
        org.usfirst.frc.team1836.robot.subsystems.Drive.getInstance().setTrajectoryPoint();
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override protected boolean isFinished() {
        return org.usfirst.frc.team1836.robot.subsystems.Drive.getInstance()
            .getTrajectoryFinished();
    }

    // Called once after isFinished returns true
    @Override protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override protected void interrupted() {
    }
}
