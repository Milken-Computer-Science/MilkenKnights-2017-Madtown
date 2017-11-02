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
    private CANTalon gearTalon;
    private MkCANTalon rollerTalon;
    private GearMechanismState gearMechState;


    public GearMech() {
        super("GEARMECH");
        gearTalon = new CANTalon(Constants.Hardware.GEAR_PICKUP_TALON_ID);
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
        gearMechState = GearMechanismState.STOW; 
        
        rollerTalon = new MkCANTalon(Constants.Hardware.ROLLER_TALON_ID, false);
        rollerTalon.reverseOutput(Constants.Hardware.ROLLER_TALON_REVERSE);
        rollerTalon.setPrint(false);
    }
    public static GearMech getInstance() {
      if (gearMech == null){
        gearMech = new GearMech();
      }
      return gearMech;
    }

    @Override public void updateTeleop() {
     if (Inputs.gearPickupButton.isHeld()) {
           //set(GearMechanismState.PICKUP);
       gearTalon.changeControlMode(TalonControlMode.MotionMagic);
       gearTalon.set(50);

       System.out.println(gearTalon.getClosedLoopError() + "Error");
       System.out.println(gearTalon.getOutputVoltage() / gearTalon.getBusVoltage() + " Voltage");
        } else if (Inputs.gearStowButton.isPressed()) {
           set(GearMechanismState.STOW);
        } else if (Inputs.gearPlaceButton.isPressed()) {
           set(GearMechanismState.PLACE);
        } 
     
     if(Inputs.rollerInButton.isHeld()){
       rollerTalon.set(1);
     }
     else if(Inputs.rollerOutButton.isHeld()) {
       rollerTalon.set(-1);
     }


    }

    @Override public void updateAuto() {

    }

    @Override public void initTeleop() {
      gearTalon.setPosition(0);
      gearTalon.changeControlMode(TalonControlMode.MotionMagic);
    }

    @Override public void initAuto() {
      gearTalon.setPosition(0);
       // gearTalon.changeControlMode(TalonControlMode.MotionMagic);
    }

    @Override public void sendToSmartDash() {
    SmartDashboard.putNumber("Arm Encoder Position", gearTalon.getPosition());
    }

     public void set(GearMechanismState state) {
        gearMechState = state;
        gearTalon.set(state.state);
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
