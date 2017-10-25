package org.usfirst.frc.team1836.robot.auto.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1836.robot.subsystems.Drive;

/**
 *
 */
public class MagicMotionDrive extends Command {

		private double pos;

		public MagicMotionDrive(double pos) {
				this.pos = pos;
		}

		// Called just before this Command runs the first time
		@Override protected void initialize() {
				Drive.getInstance().setMotionMagicRelPosition(pos);
		}

		// Called repeatedly when this Command is scheduled to run
		@Override protected void execute() {

		}

		// Make this return true when this Command no longer needs to run execute()
		@Override protected boolean isFinished() {
				return Drive.getInstance().getMotionMagicOnTarget();
		}

		// Called once after isFinished returns true
		@Override protected void end() {

		}

		// Called when another command which requires one or more of the same
		// subsystems is scheduled to run
		@Override protected void interrupted() {

		}
}
