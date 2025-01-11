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
public class NewArmElbowSubsystem extends SubsystemBase {

    // declare hardware here
    Telemetry telemetry;
    MotorEx elbowMotor;
    String elbowName;


    public NewArmElbowSubsystem(HardwareMap hardwareMap, Telemetry telemetry, String elbowName) {
        // initialize hardware here alongside other parameters
        this.telemetry = telemetry;
        this.elbowName = elbowName;
        this.elbowMotor = new MotorEx(hardwareMap, elbowName);
        elbowMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void periodic() {
        telemetry.addData(elbowName+": ", elbowMotor.get());
    }

    public void setPower(DoubleSupplier power) {
        elbowMotor.set(power.getAsDouble());
    }
    public void setPower(double power) {
        elbowMotor.set(power);
    }
}
