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
    Servo intakeServoLeftServo;
    Servo intakeServoRightServo;
    String intakeServoLeft;
    String intakeServoRight;

    public IntakeServosSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        // initialize hardware here alongside other parameters
        this.intakeServoLeft = "intakeServoLeft";
        this.intakeServoRight = "intakeServoRight";
        this.intakeServoLeftServo = hardwareMap.get(Servo.class, intakeServoLeft);
        this.intakeServoRightServo = hardwareMap.get(Servo.class, intakeServoRight);
        this.intakeServoLeftServo.setDirection(Servo.Direction.REVERSE);
        this.telemetry = telemetry;
    }
    @Override
    public void periodic() {
        if(!Double.isNaN(intakeServoRightServo.getPosition())){
            telemetry.addData(intakeServoLeft+": intakeServoLeftData" , intakeServoLeftServo.getPosition());
            telemetry.addData(intakeServoRight+": intakeServoRightData" , intakeServoRightServo.getPosition());
        }
    }

    public void setPower(DoubleSupplier power) {
        intakeServoLeftServo.setPosition(power.getAsDouble());
        intakeServoRightServo.setPosition(power.getAsDouble());
    }


    public void setPower(double power) {
        intakeServoLeftServo.setPosition(power);
        intakeServoRightServo.setPosition(power);
    }

}
