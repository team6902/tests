/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;


public class TurnToAngle extends Command {

  public TurnToAngle(double degrees) {
    System.out.println("Turn to angle " + degrees);
    requires(Robot.m_drive);
    Robot.m_drive.getTurnController().setSetpoint(degrees);
    setTimeout(5.0);
  }

  @Override
  protected void initialize() {
    
  }

  @Override
  protected void execute() {
    double pidOutput
        = Robot.m_drive.getTurnController().calculate(Robot.m_drive.getHeading());
    Robot.m_drive.arcadeDrive(0, pidOutput);
  }

  @Override
  protected boolean isFinished() {
    return this.isTimedOut() || Robot.m_drive.getTurnController().atSetpoint();
  }

  @Override
  protected void end() {

  }

  @Override
  protected void interrupted() {
  }
}
