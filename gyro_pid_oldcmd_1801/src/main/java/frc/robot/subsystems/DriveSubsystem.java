/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.TankDriveWithJoystick;

public class DriveSubsystem extends Subsystem {

  private static final double kTurnP = 0;
  private static final double kTurnI = 0;
  private static final double kTurnD = 0;

  private final SpeedController m_leftMotors = new Spark(RobotMap.kLeftMotor);
  private final SpeedController m_rightMotors = new Spark(RobotMap.kRightMotor);
  private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);
  private final AHRS m_gyro = new AHRS(RobotMap.kNavXPort);
  private final PIDController m_pidTurnController = new PIDController(kTurnP, kTurnI, kTurnD, Robot.kPeriod);

  // Just for logging;
  private double xSpeed, zRotation = 0;

  public DriveSubsystem() {
    
  }

  public double getHeading() {
    return this.m_gyro.pidGet();
  }

  public void tankDrive(double left, double right) {
    m_drive.tankDrive(left, right, true);
  }

  public void arcadeDrive(double xSpeed, double zRotation) {
    this.xSpeed = xSpeed;
    this.zRotation = zRotation;
    m_drive.arcadeDrive(xSpeed, zRotation);
  }

  public PIDController getTurnController() {
    return this.m_pidTurnController;
  }

  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new TankDriveWithJoystick());
  }

  private void log() {
    System.out.printf("%f,%f,%f,%f,%f,%f,%f,%f,%f\n", 
                      Robot.m_timer.get(),
                      this.m_pidTurnController.getP(),
                      this.m_pidTurnController.getI(),
                      this.m_pidTurnController.getD(),
                      this.getHeading(),
                      this.m_gyro.getAngle(),
                      this.xSpeed,
                      this.zRotation,
                      this.m_pidTurnController.atSetpoint());
  }

  @Override
  public void periodic() {
    log();
  }
  
}
