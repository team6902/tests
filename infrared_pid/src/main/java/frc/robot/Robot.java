/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.MedianFilter;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  Joystick stick = new Joystick(0);

  // distance in inches the robot wants to stay from an object
  private static final double kHoldDistance = 1.0;

  // factor to convert sensor values to a distance in inches
  private static final double kValueToInches = 0.125;

  // proportional speed constant
  private static final double kP = 7.0;

  // integral speed constant
  private static final double kI = 0.018;

  // derivative speed constant
  private static final double kD = 1.5;

  private static final int kLeftMotorPort = 9;
  private static final int kRightMotorPort = 0;
  private static final int kUltrasonicPort = 0;

  // median filter to discard outliers; filters over 5 samples
  private final MedianFilter m_filter = new MedianFilter(15);

  private final AnalogInput m_ultrasonic = new AnalogInput(kUltrasonicPort);

  private final DifferentialDrive m_robotDrive = new DifferentialDrive(new Spark(kLeftMotorPort),
      new Spark(kRightMotorPort));
  private final PIDController m_pidController = new PIDController(kP, kI, kD);

  private double getDist() {
    return 10650.08*Math.pow(m_filter.calculate(m_ultrasonic.getVoltage()),-0.935) - 10;
  }

  @Override
  public void robotInit() {
    m_robotDrive.setMaxOutput(.4);
  }

  @Override
  public void teleopInit() {
    // Set setpoint of the pid controller
    m_pidController.setSetpoint(1.0);
  }

  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("dist", m_filter.calculate(m_ultrasonic.getVoltage()));
  }

  @Override
  public void teleopPeriodic() {
    // returned value is filtered with a rolling median filter, since ultrasonics
    // tend to be quite noisy and susceptible to sudden outliers
    
    if (stick.getRawButton(1)) {
      double pidOutput = m_pidController.calculate(m_filter.calculate(m_ultrasonic.getVoltage()));
  
      SmartDashboard.putNumber("pidOutput", pidOutput);
      m_robotDrive.arcadeDrive(pidOutput, 0);
    } else {
      m_robotDrive.stopMotor();
    }
  }
}