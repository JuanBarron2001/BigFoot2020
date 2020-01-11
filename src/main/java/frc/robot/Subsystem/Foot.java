package frc.robot.Subsystem;



import edu.wpi.first.wpilibj2.command.SubsystemBase;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;


public class Foot extends SubsystemBase
{
    private TalonSRX foot1;
    private VictorSPX foot2;
    private boolean used;

    public Foot() 
    {
        used = false; //this flag is used to decide if the leg needs to be pulled up
        
        foot1 = new TalonSRX(7);  //master
        foot2 = new VictorSPX(2);  //slave

        foot1.configFactoryDefault();     //this might be the order
        foot2.configFactoryDefault();
      
       
        foot2.follow(foot1);  // setting the slave to its master

        
    }

    public void jump()
    {
        foot1.set(ControlMode.PercentOutput, -.6);          //this is the static number for climbing
        used = true; //we might be able to tie this to the reverse limit switch to work better even if jump is accidently pressed
        // havent tested this
    }

    public void stop()
    {
        foot1.set(ControlMode.PercentOutput, 0);            //this sets the foots power to 0
    }

    public void manualJump(double power)
    {
        foot1.set(ControlMode.PercentOutput, power);        //this sets the foot power by the number given
    }

    public boolean isUsed()
    {
        return used;                                        //it returns if the foot was used
    }
}