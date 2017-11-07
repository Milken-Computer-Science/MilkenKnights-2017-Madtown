package org.usfirst.frc.team1836.robot.auto.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team1836.robot.Constants;

import org.usfirst.frc.team1836.robot.auto.commands.PlaceGearSequence;
import org.usfirst.frc.team1836.robot.auto.commands.TimedDrive;
import org.usfirst.frc.team1836.robot.subsystems.GearMech;


public class TimedCenterAuto extends CommandGroup {



    public TimedCenterAuto() {
        GearMech.getInstance().set(GearMech.GearMechanismState.PLACE);
        addSequential(new TimedDrive(Constants.Auto.CENTER_AUTO_TIME));
        addSequential(new PlaceGearSequence(5));
    }


}
