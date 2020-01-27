/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  private Compressor compressor;
  private Joystick m_stick;
  private DoubleSolenoid m_doubleSolenoid;
  
  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    compressor = new Compressor();
    m_stick = new Joystick(0);
    m_doubleSolenoid = new DoubleSolenoid(0,1);
    compressor.start();

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
    if (m_stick.getRawButton(1)) {
      m_doubleSolenoid.set(DoubleSolenoid.Value.kForward);
    } else if (m_stick.getRawButton(2)) {
      m_doubleSolenoid.set(DoubleSolenoid.Value.kReverse);
    } else {
      m_doubleSolenoid.set(DoubleSolenoid.Value.kOff);
    }

  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

}
