package frc.robot;

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
        robotDrive);

    SendableChooser<Command> m_chooser = new SendableChooser<>();

    Joystick driver = new Joystick(Constants.driverPad);
    Joystick controller = new Joystick(Constants.controllerPad);

    public RobotContainer()
    {
        this.configureButtonBindings();

        robotDrive.setDefaultCommand(
            new RunCommand(() -> robotDrive
                .driveVoltage(-driver.getRawAxis(1),
                            driver.getRawAxis(4)),robotDrive));

        m_chooser.addOption("simple Auto", simpleAuto);
    }

    private void configureButtonBindings() 
    {
        new JoystickButton(controller, Constants.A)
        .whenPressed(new InstantCommand(triangle::downT, triangle));

        new JoystickButton(controller, Constants.B)
        .whenPressed(new InstantCommand(triangle::upT, triangle));

        new JoystickButton(controller, Constants.X)
        .whileHeld(new InstantCommand(pistons::push, pistons))
        .whenReleased(new InstantCommand(pistons::off, pistons));

        new JoystickButton(controller, Constants.Y)
        .whenPressed(new AutoShoot(pistons, triangle));

        new JoystickButton(controller, Constants.LB)
        .whenPressed(new InstantCommand(box::suck, box))
        .whenReleased(new InstantCommand(box::stop, box));

        new JoystickButton(controller, Constants.RB)
        .whenPressed(new InstantCommand(box::spit, box))
        .whenReleased(new InstantCommand(box::stop, box));;

        new JoystickButton(driver, 7)
        .whileHeld(new InstantCommand(foot::jump, foot));
    }
    
    public Command getAutonomousCommand() 
    {
        return m_chooser.getSelected();
    }
}