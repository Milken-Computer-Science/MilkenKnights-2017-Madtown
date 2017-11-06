package org.usfirst.frc.team1836.robot.subsystems;

import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team1836.robot.Constants;
import org.usfirst.frc.team1836.robot.Inputs;
import org.usfirst.frc.team1836.robot.util.MkCANTalon;
import org.usfirst.frc.team1836.robot.util.Subsystem;

public class Climber extends Subsystem {
  private static Climber climber;
  private MkCANTalon climberTalon;
  private int reverse = 1;


  public Climber() {
    super("CLIMBER");
    climberTalon = new MkCANTalon(Constants.Hardware.CLIMBER_TALON_ID, true);
    climberTalon.setPrint(false);
  }

  public static Climber getInstance() {
    if (climber == null) {
      climber = new Climber();
    }
    return climber;
  }

  @Override
  public void updateTeleop() {
    if(Inputs.climberReverseButton.isPressed()){
      reverse = -reverse;
      System.out.println("Reverse");
    }
    if (Inputs.climberButton.isHeld()) {
      climberTalon.set(0.75 * reverse);
    }
    else{
      climberTalon.set(0);
    }
  }

  @Override
  public void updateAuto() {

  }

  @Override
  public void initTeleop() {

  }

  @Override
  public void initAuto() {

  }

  @Override
  public void sendToSmartDash() {}

}
