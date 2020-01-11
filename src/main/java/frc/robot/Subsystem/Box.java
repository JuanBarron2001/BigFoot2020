package frc.robot.Subsystem;


import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.DigitalInput;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;


public class Box extends SubsystemBase
{
    private TalonSRX roller;                //initialize a TalonSRX
    private DigitalInput laser;             //initialize a DigitalInput(on and off)

    

    public Box()
    {
        roller = new TalonSRX(5);           //roller is declared as TalonSRX ID 5
        
        roller.configFactoryDefault();      //factory default idk why but every code i see needs it

        laser = new DigitalInput(0);        //laser is declared as DigitalInput port 0
    }

    public void suck()
    {
        roller.set(ControlMode.PercentOutput, .5 );       //sets roller to intake at .6 volts in the negative direction
    }

    //spits the ball
    public void spit()
    {
        roller.set(ControlMode.PercentOutput, -.75);         //sets roller to outtake at .75 volts in the possitive direction
    }

    public void stop()
    {
        roller.set(ControlMode.PercentOutput, 0);           //sets the roller to stop at 0 volts
    }

    public boolean IsBallThere()
    {
        boolean a = !laser.get();                           //makes a boolean to get laser status
        return a;                                           //returns the state of the laser
    }
}