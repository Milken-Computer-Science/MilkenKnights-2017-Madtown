package org.usfirst.frc.team1836.robot.auto.commands;

import com.team254.lib.trajectory.Main;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1836.robot.Constants;

/**
 *
 */
public class TurnInPlace extends Command {

    private final Main m;
    private final double deg;

    public TurnInPlace(double deg) {
        m = new Main();
        this.deg = deg;
    }

    @Override protected void initialize() {
        org.usfirst.frc.team1836.robot.subsystems.Drive.getInstance().setDriveTrajectory(
            m.genTraj(deg, Constants.DRIVE.DELTA_T, Constants.DRIVE.M_ACCEL, Constants.DRIVE.M_VEL,
                Constants.DRIVE.M_JERK), deg);
    }

    @Override protected void execute() {
        org.usfirst.frc.team1836.robot.subsystems.Drive.getInstance().setTurnTrajectoryPoint();
    }

    @Override protected boolean isFinished() {
        return org.usfirst.frc.team1836.robot.subsystems.Drive.getInstance()
            .getTrajectoryFinished();
    }

    @Override protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override protected void interrupted() {
    }
}
