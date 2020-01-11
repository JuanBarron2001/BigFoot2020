package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Commands.AutoShoot;
import frc.robot.Subsystem.Box;
import frc.robot.Subsystem.DriveTrain;
import frc.robot.Subsystem.Foot;
import frc.robot.Subsystem.Pistons;
import frc.robot.Subsystem.Triangle;

import static edu.wpi.first.wpilibj.XboxController.Button;

public class RobotContainer
{
    private final DriveTrain robotDrive = new DriveTrain();
    private final Triangle triangle = new Triangle();
    private final Pistons pistons = new Pistons();
    private final Foot foot = new Foot();
    private final Box box = new Box();

    private final Command simpleAuto = new StartEndCommand(
        () -> robotDrive.driveVoltage(.1, .1),
        () -> robotDrive.driveVoltage(0, 0),
        robotDrive
        );

    
    SendableChooser<Command> m_chooser = new SendableChooser<>();

    Joystick gamepad1 = new Joystick(0);
    Joystick gamepad2 = new Joystick(1);

    public RobotContainer()
    {
        this.configureButtonBindings();

        robotDrive.setDefaultCommand(
            new RunCommand(() -> robotDrive
                .driveVoltage(gamepad1.getX(GenericHID.Hand.kLeft),
                            gamepad1.getY(GenericHID.Hand.kRight)),robotDrive));

        m_chooser.addOption("simple Auto", simpleAuto);


    }

    private void configureButtonBindings() 
    {
        new JoystickButton(gamepad2, 1)
        .whenPressed(new InstantCommand(triangle::downT, triangle));

        new JoystickButton(gamepad2, 2)
        .whenPressed(new InstantCommand(triangle::upT, triangle));

        new JoystickButton(gamepad2, 3)
        .whileHeld(new InstantCommand(pistons::push, pistons));
        //.whenReleased(new InstantCommand(pistons::off, pistons));

        new JoystickButton(gamepad2, 4)
        .whenPressed(new AutoShoot(pistons, triangle));

        new JoystickButton(gamepad2, 5)
        .whenPressed(new InstantCommand(box::suck, box));

        new JoystickButton(gamepad2, 6)
        .whenPressed(new InstantCommand(box::spit, box));

        new JoystickButton(gamepad1, 7)
        .whileHeld(new InstantCommand(foot::jump, foot));
    }
    
    public Command getAutonomousCommand() {
        return m_chooser.getSelected();
      }
}