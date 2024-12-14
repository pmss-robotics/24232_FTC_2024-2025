package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.function.DoubleSupplier;

// https://docs.ftclib.org/ftclib/command-base/command-system/subsystems
@Config
public class ExtendVerticalSubsystem extends SubsystemBase {

    // declare hardware here
    Telemetry telemetry;
    public DcMotorEx motor;
    String extendUp;


    public ExtendVerticalSubsystem(HardwareMap hardwareMap, Telemetry telemetry, String motorName) {
        // initialize hardware here alongside other parameters
        this.telemetry = telemetry;
        this.extendUp = motorName;
        this.motor = hardwareMap.get(DcMotorEx.class,  motorName);
        motor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public void periodic() {
        telemetry.addData(extendUp+": extendUpData", motor.getCurrentPosition());
    }

    public void setPower(double power) {
        motor.setPower(power);
    }
}
