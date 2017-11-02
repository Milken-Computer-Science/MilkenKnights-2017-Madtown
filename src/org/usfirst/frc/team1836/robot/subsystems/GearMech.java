package org.usfirst.frc.team1836.robot.subsystems;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1836.robot.Constants;
import org.usfirst.frc.team1836.robot.Inputs;
import org.usfirst.frc.team1836.robot.util.MkCANTalon;
import org.usfirst.frc.team1836.robot.util.Subsystem;

public class GearMech extends Subsystem {
  private static GearMech gearMech;
  private MkCANTalon gearTalon;
  private MkCANTalon rollerTalon;
  private GearMechanismState gearMechState;


  public GearMech() {
    super("GEARMECH");
    gearTalon = new MkCANTalon(Constants.Hardware.GEAR_PICKUP_TALON_ID, true);
    gearTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
    gearTalon.reverseSensor(Constants.Hardware.GEAR_ARM_TALON_SENSOR_REVERSE);
    gearTalon.reverseOutput(Constants.Hardware.GEAR_ARM_TALON_REVERSE);
    gearTalon.configNominalOutputVoltage(+0.0f, 0.0f);
    gearTalon.configPeakOutputVoltage(12.0f, -12.0f);
    gearTalon.setProfile(0);
    gearTalon.setF(Constants.GearMech.GEAR_F);
    gearTalon.setP(Constants.GearMech.GEAR_P);
    gearTalon.setI(Constants.GearMech.GEAR_I);
    gearTalon.setD(Constants.GearMech.GEAR_D);
    gearTalon.setIZone(Constants.GearMech.GEAR_I_ZONE);
    gearTalon.setMotionMagicCruiseVelocity(Constants.GearMech.GEAR_V);
    gearTalon.setMotionMagicAcceleration(Constants.GearMech.GEAR_A);
    gearTalon.setPrint(true);
    gearMechState = GearMechanismState.STOW;

    rollerTalon = new MkCANTalon(Constants.Hardware.ROLLER_TALON_ID, false);
    rollerTalon.setPrint(false);
  }

  public static GearMech getInstance() {
    if (gearMech == null) {
      gearMech = new GearMech();
    }
    return gearMech;
  }

  @Override
  public void updateTeleop() {
    /*
     * if (Inputs.gearPickupButton.isPressed()) { set(GearMechanismState.PICKUP); } else if
     * (Inputs.gearStowButton.isPressed()) { set(GearMechanismState.STOW); } else if
     * (Inputs.gearPlaceButton.isPressed()) { set(GearMechanismState.PLACE); }
     */
    if (Inputs.gearPickupButton.isHeld()) {
      gearTalon.changeControlMode(TalonControlMode.MotionMagic);
      gearTalon.set(Inputs.operatorJoystick.getRawAxis(1) * 10);
      System.out.println("Voltage: " + gearTalon.getOutputVoltage() / gearTalon.getBusVoltage()
          + " Error: " + gearTalon.getClosedLoopError() + " Position: " + gearTalon.getPosition()
          + " Raw Position " + gearTalon.getEncPosition());
    } 
    else if (Inputs.gearPlaceButton.isHeld()) {
      gearTalon.changeControlMode(TalonControlMode.PercentVbus);
      gearTalon.set(Inputs.operatorJoystick.getRawAxis(1));
      System.out.println("Voltage: " + gearTalon.getOutputVoltage() / gearTalon.getBusVoltage()
          + " Error: " + gearTalon.getClosedLoopError() + " Position: " + gearTalon.getPosition()
          + " Raw Position " + gearTalon.getEncPosition());
    } 
    else{
      gearTalon.set(0);
    }

    if (Inputs.rollerInButton.isHeld()) {
      rollerTalon.set(Constants.GearMech.ROLLER_SPEED);
    } else if (Inputs.rollerOutButton.isHeld()) {
      rollerTalon.set(-Constants.GearMech.ROLLER_SPEED);
    } else {
      rollerTalon.set(0);
    }


  }

  @Override
  public void updateAuto() {

  }

  @Override
  public void initTeleop() {
    gearTalon.setEncPosition(0);
    gearTalon.changeControlMode(TalonControlMode.MotionMagic);
  }

  @Override
  public void initAuto() {
    gearTalon.setEncPosition(0);
    gearTalon.changeControlMode(TalonControlMode.MotionMagic);
  }

  @Override
  public void sendToSmartDash() {
    SmartDashboard.putNumber("Arm Encoder Position", gearTalon.getPosition());
  }

  public void set(GearMechanismState state) {
    gearMechState = state;
    gearTalon.set(state.state);
  }

  public GearMechanismState getState() {
    return gearMechState;
  }

  public enum GearMechanismState {
    PICKUP(Constants.GearMech.GEAR_PICKUP), STOW(Constants.GearMech.GEAR_STOW), PLACE(
        Constants.GearMech.GEAR_PLACE);
    public final double state;

    GearMechanismState(final double state) {
      this.state = state;
    }
  }

}
