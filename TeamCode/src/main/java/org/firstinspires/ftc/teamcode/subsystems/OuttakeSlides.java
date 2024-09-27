package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

// https://docs.ftclib.org/ftclib/command-base/command-system/subsystems
@Config
public class OuttakeSlides extends SubsystemBase {

    // declare hardware here
    Telemetry telemetry;
    MotorEx outtake;


    public OuttakeSlides(HardwareMap hardwareMap, Telemetry telemetry) {
        // initialize hardware here alongside other parameters
        this.telemetry = telemetry;
        outtake = new MotorEx(hardwareMap, "outtake");
    }

    @Override
    public void periodic() {
        telemetry.addData("Outtake", outtake.get());
        telemetry.update();
    }

    public void setPower(double power) {
        outtake.set(power);
    }


}
