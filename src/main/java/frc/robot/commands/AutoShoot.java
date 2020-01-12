package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Subsystem.Pistons;
import frc.robot.Subsystem.Triangle;

public class AutoShoot extends SequentialCommandGroup
{
    public AutoShoot( Pistons pistons,Triangle triangle)
    { 
        /*addCommands(
        new StartEndCommand(() -> new InstantCommand(triangle::downT, triangle),() -> time.delay(.75), triangle),
        new StartEndCommand(() -> new InstantCommand(pistons::push, pistons),() -> time.delay(.5), pistons),
        new InstantCommand(pistons::off, pistons)
        );*/
        addCommands(
        new InstantCommand(triangle::downT, triangle),
        new InstantCommand(pistons::push, pistons),
        new InstantCommand(pistons::off, pistons)
        );
        
    }

}