package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import frc.robot.Subsystem.Pistons;
import frc.robot.Subsystem.Triangle;
import edu.wpi.first.wpilibj.Timer;

public class AutoShoot extends SequentialCommandGroup
{
    public AutoShoot( Pistons pistons,Triangle triangle)
    { 
        final Timer time = new Timer();
        addCommands(
        new StartEndCommand(() -> new InstantCommand(triangle::downT, triangle),() -> time.delay(.75), triangle),
        new StartEndCommand(() -> new InstantCommand(pistons::push, pistons),() -> time.delay(.5), pistons),
        new InstantCommand(pistons::off, pistons)
        );

        
    }


}