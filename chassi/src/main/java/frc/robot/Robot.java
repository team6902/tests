/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
  boolean controlToogle = false;
  

  private Spark motorsLeft = new Spark(0);
  private Spark motorsRight = new Spark(9);
  private DifferentialDrive drive = new DifferentialDrive(motorsLeft, motorsRight);

  public final Joystick stick1 = new Joystick(0);
  public final Joystick stick2 = new Joystick(0);

  @Override
  public void robotInit() {
    stick1.setYChannel(5);
    stick2.setXChannel(4);
    // drive.setMaxOutput(.4);

  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {    
    if (controlToogle) drive.tankDrive(-stick2.getY(), -stick1.getY(), true);
    else drive.arcadeDrive(-stick2.getY(), stick2.getX(), true);

    if (stick1.getRawButtonPressed(1)) {
      controlToogle = !controlToogle;
    }
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

  @Override
  public void robotPeriodic() {
  }

}
