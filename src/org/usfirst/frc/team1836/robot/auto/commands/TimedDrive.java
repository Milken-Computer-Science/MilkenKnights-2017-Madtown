package org.usfirst.frc.team1836.robot.auto.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1836.robot.Constants;
import org.usfirst.frc.team1836.robot.subsystems.Drive;

public class TimedDrive extends Command {

    private final double time;
    private Timer timer;

    public TimedDrive(double time) {
        this.time = time;
    }

    @Override protected void initialize() {
        timer.start();
    }

    @Override protected void execute() {
        Drive.getInstance().setDrive(Constants.Auto.CENTER_AUTO_SPEED);
    }

    @Override protected boolean isFinished() {
        return timer.get() < time;

    }

    @Override protected void end() {

    }

    @Override protected void interrupted() {

    }
}
