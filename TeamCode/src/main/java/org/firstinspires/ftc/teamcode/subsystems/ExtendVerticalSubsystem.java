package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.function.DoubleSupplier;

// https://docs.ftclib.org/ftclib/command-base/command-system/subsystems
@Config
public class ExtendVerticalSubsystem extends SubsystemBase {

    // declare hardware here
    Telemetry telemetry;
    static MotorEx motor;
    String extendUp;


    public ExtendVerticalSubsystem(HardwareMap hardwareMap, Telemetry telemetry, String motorName) {
        // initialize hardware here alongside other parameters
        this.telemetry = telemetry;
        this.extendUp = motorName;
        this.motor = new MotorEx(hardwareMap, extendUp);
        motor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void periodic() {
        telemetry.addData(extendUp+": extendnUpData", motor.get());
    }

    public void setPower(DoubleSupplier power) {
        motor.set(power.getAsDouble());
    }
    public static void setPower(double power) {
        motor.set(power);
    }
}
