package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

// https://docs.ftclib.org/ftclib/command-base/command-system/subsystems
@Config
public class IntakeSlides extends SubsystemBase {

    // declare hardware here
    Telemetry telemetry;
    MotorEx intake;


    public IntakeSlides(HardwareMap hardwareMap, Telemetry telemetry) {
        // initialize hardware here alongside other parameters
        this.telemetry = telemetry;
        intake = new MotorEx(hardwareMap, "intake");
    }

    @Override
    public void periodic() {
        telemetry.addData("GenericServoSubsystem", intake.get());
        telemetry.update();
    }

    public void setPower(double power) {
        intake.set(power);
    }


}
