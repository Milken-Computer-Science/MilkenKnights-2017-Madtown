package com.ctre.phoenix.Motion;

import com.ctre.phoenix.Drive.*;
import com.ctre.phoenix.Util;

public class ServoGoStraight implements com.ctre.phoenix.ILoopable
{
	ISmartDrivetrain _driveTrain;
	Styles.Smart _selectedStyle;
	com.ctre.phoenix.Time.StopWatch _myStopWatch = new com.ctre.phoenix.Time.StopWatch();
	
	private ServoParameters servoParams = new com.ctre.phoenix.Motion.ServoParameters(); 
	
	public ServoParameters GetServoParams() { return servoParams; }
	public void SetServoParams(ServoParameters params)
	{
		servoParams = params;
	}
	
	float _y;
	float _heading;
	float _previousHeading;
	float _timeElapsed;
	
	boolean _isRunning = false;
	boolean _isDone = false;
	
	public ServoGoStraight(ISmartDrivetrain driveTrain, Styles.Smart style, ServoParameters params, float y, float heading)
	{
		_driveTrain = driveTrain;
		_selectedStyle = style;
		_y = y;
		_heading = heading;

		SetServoParams(params);
	}
	
    public ServoGoStraight(ISmartDrivetrain driveTrain, Styles.Smart style)
    {
        _driveTrain = driveTrain;
        _selectedStyle = style;
    }
    
    public void Set(float Y , float heading)
    {
        _y = Y;
		_heading = heading;
    }
    
    public float GetEncoderHeading()
    {
        return _driveTrain.GetEncoderHeading();
    }
	
	public void resetIAccum() { servoParams.resetIAccum(); }

    private void GoStraight(float Y, float heading)
    {
    	if(servoParams.P == 0 && servoParams.I == 0 && servoParams.D == 0)
    		com.ctre.phoenix.CTRLogger.Log(-503, "Servo Go Straight");
    	
        /* Grab encoder heading */
        float currentHeading = GetEncoderHeading();

        /* Find angular rate from the encoders */
        _timeElapsed = _myStopWatch.getDuration();
        float correctionRate = ((currentHeading - _previousHeading) / _timeElapsed);
        _myStopWatch.start();

        /* Heading PID */
        float headingError = heading - currentHeading;
        float X = servoParams.PID(headingError, correctionRate);
        X = -X;

        /* Select control style based on selected style */
        switch (_selectedStyle)
        {
            case PercentOutput:
                _driveTrain.set(com.ctre.phoenix.Drive.Styles.Smart.PercentOutput, Y, X);
                break;
            case Voltage:
                _driveTrain.ConfigNominalPercentOutputVoltage(+0.0f, -0.0f);
                _driveTrain.ConfigPeakPercentOutputVoltage(+servoParams.maxOut, -servoParams.maxOut);
                _driveTrain.set(com.ctre.phoenix.Drive.Styles.Smart.Voltage, Y, X);
                break;
            case VelocityClosedLoop:
                _driveTrain.set(com.ctre.phoenix.Drive.Styles.Smart.VelocityClosedLoop, Y, X);
                break;
        }
        _previousHeading = currentHeading;

    }

    /** ILoopable */
    public void OnStart()
    {
        _isDone = false;
		servoParams.OnStart();
    }

    public void OnStop()
    {
        _driveTrain.set(Styles.Smart.PercentOutput, 0, 0);
        _isRunning = false;
        _isDone = true;
    }

    public boolean IsDone()
    {
        return _isDone;
    }

    public void OnLoop()
    {
        GoStraight(_y, _heading);
    }
}