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
  private double angleSetpoint = 0.0;
  private static final double kP = 0.005; // propotional turning constant

  // gyro calibration constant, may need to be adjusted;
  // gyro value of 360 is set to correspond to one full revolution
  // private static final double kVoltsPerDegreePerSecond = 0.0128;

  // ToDo: verificar portas
  private static final int kLeftMotorPort = 9;
  private static final int kRightMotorPort = 0;
  private static final int kJoystickPort = 0;
  double turningValue = 0;

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
      this.angleSetpoint = 0;
    }
    if (m_joystick.getRawButton(2)) {
      this.angleSetpoint = 90;
    }
    if (m_joystick.getRawButton(3)) {
      this.angleSetpoint = 180;
    }
    if (m_joystick.getRawButton(4)) {
      this.angleSetpoint = 270;
    }
    turningValue = (this.angleSetpoint - ahrs.getYaw()) * kP;
    // Invert the direction of the turn if we are going backwards
    // turningValue = Math.copySign(turningValue, -m_joystick.getY());
    
    double vel = -m_joystick.getY();
    if (vel > .2) vel = .2;
    else if (vel < -.2) vel = -.2;
    m_myRobot.arcadeDrive(vel, turningValue);
  }

  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Angle", ahrs.getYaw());
    SmartDashboard.putNumber("Turning Value", turningValue);
    SmartDashboard.putNumber("SetPoint", angleSetpoint);
    System.out.printf("Angle=%2.2f Turning Value=%2.2f Setpoint=%2.2f\n",
                      ahrs.getYaw(),
                      turningValue,
                      angleSetpoint);
  }
}
