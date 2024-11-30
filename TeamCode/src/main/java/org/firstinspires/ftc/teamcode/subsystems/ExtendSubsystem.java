package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

// https://docs.ftclib.org/ftclib/command-base/command-system/subsystems
@Config
public class ExtendSubsystem extends SubsystemBase {

    // declare hardware here
    Telemetry telemetry;
    MotorEx intakeExtensionUp;
    MotorEx intakeExtensionFront;



    public ExtendSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        //initialize hardware here alongside other parameters
        this.telemetry = telemetry;
        //Set Motors
        this.intakeExtensionUp = new MotorEx(hardwareMap, "intakeExtensionUp");
        this.intakeExtensionFront = new MotorEx(hardwareMap, "intakeExtensionFront");
        //When there is nothing for the motors to do
        intakeExtensionUp.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        intakeExtensionFront.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        //aaaa


    }
    @Override
    public void periodic() {
        //telemetry.addData("Intake Extension Position Thingie Left", intakeExtensionLeft.getCurrentPosition());
        telemetry.addData("Intake Extension Position Thingie up", intakeExtensionUp.getCurrentPosition());
        telemetry.addData("Intake Extension Position Thingie front", intakeExtensionFront.getCurrentPosition());


    }

    public void setPower(double power) {
        intakeExtensionUp.set(power);
        intakeExtensionFront.set(power);
    }

}
