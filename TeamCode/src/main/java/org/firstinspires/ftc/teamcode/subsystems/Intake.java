package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

// https://docs.ftclib.org/ftclib/command-base/command-system/subsystems
@Config
public class Intake extends SubsystemBase {

    // declare hardware here
    Telemetry telemetry;
    Servo wrist, spinner;
    public double wristAngle = 0.5;
    public double increment = 0.001;

    public Intake(HardwareMap hardwareMap, Telemetry telemetry) {
        // initialize hardware here alongside other parameters
        this.telemetry = telemetry;
        wrist = hardwareMap.get(Servo.class, "wrist");
        spinner = hardwareMap.get(Servo.class, "spinner");
    }
    @Override
    public void periodic() {
        telemetry.addData("spinner", spinner.getPosition());
        telemetry.addData("wrist", wrist.getPosition());

    }

    public void spin(double power) {
        spinner.setPosition(power);
    }

    public void tilt(boolean upOrDown) {
        if(upOrDown) {
            wristAngle += increment;
        }else {
            wristAngle -= increment;
        }
        wrist.setPosition(wristAngle);
    }
}
