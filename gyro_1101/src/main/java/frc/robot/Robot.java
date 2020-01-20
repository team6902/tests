/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

// import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.kauailabs.navx.frc.AHRS;
import com.kauailabs.navx.frc.AHRS.SerialDataType;

/**
 * This is a sample program to demonstrate how to use a gyro sensor to make a
 * robot drive straight. This program uses a joystick to drive forwards and
 * backwards while the gyro is used for direction keeping.
 */
public class Robot extends TimedRobot {

  AHRS ahrs;
  double P = .005;
  double D = .0003;
  double I = .005;
  double integral, previous_error, error, setpoint, derivative, rcw = 0;  

  // gyro calibration constant, may need to be adjusted;
  // gyro value of 360 is set to correspond to one full revolution
  // private static final double kVoltsPerDegreePerSecond = 0.0128;

  // ToDo: verificar portas
  private static final int kLeftMotorPort = 9;
  private static final int kRightMotorPort = 0;
  private static final int kJoystickPort = 0;
  double turningValue = 0;

  public void PID(){
    error = setpoint - ahrs.pidGet(); // Error = Target - Actual
    this.integral += (error*.02);
    derivative = (error - this.previous_error) / .02;
    this.rcw = P*error + I*this.integral + D*derivative;
    this.previous_error = error;
  }

  private final DifferentialDrive m_myRobot
      = new DifferentialDrive(new Spark(kLeftMotorPort),
      new Spark(kRightMotorPort));
      
  private final Joystick m_joystick = new Joystick(kJoystickPort);

  @Override
  public void robotInit() {
    // ahrs = new AHRS(I2C.Port.kOnboard);
    try {
      // ahrs = new AHRS(SerialPort.Port.kUSB1);
      ahrs = new AHRS(I2C.Port.kOnboard);
      // ahrs = new AHRS(SerialPort.Port.kMXP, SerialDataType.kProcessedData,
      // (byte)50);
      ahrs.enableLogging(true);
    } catch (RuntimeException ex) {
      DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
    }
  }

  @Override
  public void teleopPeriodic() {
    if (m_joystick.getRawButton(1)) {
      this.setpoint = 0;
    }
    if (m_joystick.getRawButton(2)) {
      this.setpoint = 90;
    }
    if (m_joystick.getRawButton(3)) {
      this.setpoint = 170;
    }
    if (m_joystick.getRawButton(5)) {
      this.setpoint = -90;
    }
    double vel = -m_joystick.getY();
    
    // if (vel > .2) vel = .2;
    // else if (vel < -.2) vel = -.2;
    PID();
    if (rcw > .5) rcw = .5;
    else if (rcw < .5) rcw = -.5;
    m_myRobot.arcadeDrive(vel, rcw);
  }

  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("dTurning Value", rcw);
    SmartDashboard.putString("Angle", ahrs.pidGet() + "");
    
    SmartDashboard.putNumber("pid", ahrs.pidGet());
    SmartDashboard.putString("setpoint", setpoint + "");
    // SmartDashboard.putString("Turning Value", turningValue + "");
    // SmartDashboard.putNumber("dTurning Value", turningValue);
    // SmartDashboard.putString("SetPoint", angleSetpoint + "");
    // System.out.printf("Angle=%2.2f Turning Value=%2.2f Setpoint=%2.2f\n",
    //                   ahrs.getAngle(),
    //                   turningValue,
    //                   angleSetpoint);
  }
}
