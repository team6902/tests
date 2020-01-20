/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.TestPIDs;
import frc.robot.commands.TurnToAngle;

public class OI {

  public Joystick stick1 = new Joystick(0);
  public Joystick stick2 = new Joystick(0);

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  public OI() {
    Button button1 = new JoystickButton(stick2, 1);
    Button button2 = new JoystickButton(stick2, 2);
    button1.whenPressed(new TurnToAngle(90));
    button2.whenPressed(new TestPIDs());
    stick1.setYChannel(5);
  }

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
}
