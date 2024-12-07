package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.function.DoubleSupplier;

@Config
public class IntakeServosSubsystem extends SubsystemBase {
    Telemetry telemetry;
    Servo servo;
    String intakeServoLeft;
    String intakeServoRight;

    public IntakeServosSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        // initialize hardware here alongside other parameters
        this.intakeServoLeft = "intakeServoLeft";
        this.intakeServoRight = "intakeServoRight";
        this.servo = hardwareMap.get(Servo.class, intakeServoLeft);
        this.servo = hardwareMap.get(Servo.class, intakeServoRight);
        this.telemetry = telemetry;
    }
    @Override
    public void periodic() {
        if(!Double.isNaN(servo.getPosition())){
            telemetry.addData(intakeServoLeft+": " , servo.getPosition());
            telemetry.addData(intakeServoRight+": " , servo.getPosition());
        }
    }

    public void setPower(DoubleSupplier power) {
        servo.setPosition(power.getAsDouble());
    }

    public void setPower(double power) {
        servo.setPosition(power);
    }

}
