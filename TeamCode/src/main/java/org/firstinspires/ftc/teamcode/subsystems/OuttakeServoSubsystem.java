package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.function.DoubleSupplier;

@Config
public class OuttakeServoSubsystem extends SubsystemBase {
    Telemetry telemetry;
    Servo servo;
    String outtakeServo;

    public OuttakeServoSubsystem(HardwareMap hardwareMap, Telemetry telemetry, String servoName) {
        // initialize hardware here alongside other parameters
        this.outtakeServo = servoName;
        this.servo = hardwareMap.get(Servo.class, outtakeServo);
        this.telemetry = telemetry;
    }
    @Override
    public void periodic() {
        if(!Double.isNaN(servo.getPosition())){
            telemetry.addData(outtakeServo+": outtakeServoData" , servo.getPosition());
        }
    }

    public void setPower(DoubleSupplier power) {
        servo.setPosition(power.getAsDouble());
    }

    public void setPower(double power) {
        servo.setPosition(power);
    }

}
