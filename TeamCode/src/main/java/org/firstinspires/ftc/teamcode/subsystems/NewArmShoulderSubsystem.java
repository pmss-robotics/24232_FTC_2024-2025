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
public class NewArmShoulderSubsystem extends SubsystemBase {

    // declare hardware here
    Telemetry telemetry;
    MotorEx shoulderMotorLeft;
    MotorEx shoulderMotorRight;
    String leftName;
    String rightName;


    public NewArmShoulderSubsystem(HardwareMap hardwareMap, Telemetry telemetry, String leftName, String rightName) {
        // initialize hardware here alongside other parameters
        this.telemetry = telemetry;
        this.leftName = leftName;
        this.rightName = rightName;
        this.shoulderMotorLeft = new MotorEx(hardwareMap, leftName);
        shoulderMotorLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        this.shoulderMotorRight = new MotorEx(hardwareMap, rightName);
        shoulderMotorRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        shoulderMotorRight.setInverted(true);
    }

    @Override
    public void periodic() {
        telemetry.addData(leftName+": ", shoulderMotorLeft.get());
        telemetry.addData(rightName+": ", shoulderMotorRight.get());
    }

    public void setPower(DoubleSupplier power) {
        shoulderMotorLeft.set(power.getAsDouble());
        shoulderMotorRight.set((power.getAsDouble()));
    }
    public void setPower(double power) {
        shoulderMotorLeft.set(power);
        shoulderMotorRight.set(power);
    }
}
