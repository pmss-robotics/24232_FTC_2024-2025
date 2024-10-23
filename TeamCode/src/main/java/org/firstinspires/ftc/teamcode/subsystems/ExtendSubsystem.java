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
    MotorEx intakeExtensionLeft;
    MotorEx intakeExtensionRight;



    public ExtendSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        // initialize hardware here alongside other parameters
        this.telemetry = telemetry;
        //Set Motors
        this.intakeExtensionLeft = new MotorEx(hardwareMap, "intakeExtensionLeft");
        this.intakeExtensionRight = new MotorEx(hardwareMap, "intakeExtensionRight");
        intakeExtensionLeft.setInverted(true);
        //When there is nothing for the motors to do
        intakeExtensionLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        intakeExtensionRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);


    }
    @Override
    public void periodic() {
        telemetry.addData("Intake Extension Position Thingie Left", intakeExtensionLeft.getCurrentPosition());
        telemetry.addData("Intake Extension Position Thingie Right", intakeExtensionRight.getCurrentPosition());

    }

    public void setPower(double power) {
        intakeExtensionLeft.set(power);
        intakeExtensionRight.set(power);
    }

}
