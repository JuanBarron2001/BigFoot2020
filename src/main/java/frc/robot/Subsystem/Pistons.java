package frc.robot.Subsystem;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.Solenoid;

public class Pistons extends SubsystemBase
{
    private Solenoid solenoid1;                         //it initializes solenoid1 of type Solenoid

    public Pistons()
    {
        solenoid1 = new Solenoid(0, 6);                 //it declares solenoid in pcm port 0 in port 6
        addChild("Solenoid 1",solenoid1);               //it makes a child
    }

    public void push()
    {
        solenoid1.set(true);                            //it sets the piston to true in order to push
    }

    public void off()
    {
        solenoid1.set(false);                           //it sets the piston to false to retract
    }
}
