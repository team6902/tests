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
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

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
  private static final int kLeftMotorPort = 0;
  private static final int kRightMotorPort = 8;
  private static final int kJoystickPort = 0;

  private final DifferentialDrive m_myRobot
      = new DifferentialDrive(new Spark(kLeftMotorPort),
      new Spark(kRightMotorPort));
      
  private final Joystick m_joystick = new Joystick(kJoystickPort);

  @Override
  public void robotInit() {
    try {
      ahrs = new AHRS(SerialPort.Port.kUSB1);
      // ahrs = new AHRS(SerialPort.Port.kMXP, SerialDataType.kProcessedData,
      // (byte)50);
      ahrs.enableLogging(true);
    } catch (RuntimeException ex) {
      DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
    }
  }

  @Override
  public void teleopPeriodic() {
    if (m_joystick.getRawButton(0)) {
      this.angleSetpoint = 0;
    }
    if (m_joystick.getRawButton(1)) {
      this.angleSetpoint = 90;
    }
    if (m_joystick.getRawButton(2)) {
      this.angleSetpoint = 180;
    }
    if (m_joystick.getRawButton(3)) {
      this.angleSetpoint = 270;
    }
    double turningValue = (this.angleSetpoint - ahrs.getAngle()) * kP;
    // Invert the direction of the turn if we are going backwards
    turningValue = Math.copySign(turningValue, m_joystick.getY());
    m_myRobot.arcadeDrive(m_joystick.getY(), turningValue);
  }
}
