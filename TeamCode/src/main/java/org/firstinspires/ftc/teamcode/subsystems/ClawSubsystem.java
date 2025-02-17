package org.firstinspires.ftc.teamcode.subsystems;

import android.bluetooth.le.ScanSettings;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.util.InterpLUT;
import com.arcrobotics.ftclib.util.MathUtils;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PwmControl;
import com.qualcomm.robotcore.hardware.ServoImpl;
import com.qualcomm.robotcore.hardware.ServoImplEx;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.States;

import java.lang.annotation.Target;
import java.util.Objects;

// https://docs.ftclib.org/ftclib/command-base/command-system/subsystems
@Config
public class ClawSubsystem extends SubsystemBase {

    Telemetry telemetry;
    public ServoImplEx claw, wrist; // wrist rotates claw
    public double position;
    double startPos = 0;

    // write in degrees
    //public static double W_target = 0, C_target = 0; // in degrees
    //public static double pWStart = 1, pWBucket = 1, pWIntake = 1;
    //public static double pCClosed = 1, pCOpen = 1;

    public ClawSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        claw = hardwareMap.get(ServoImplEx.class, "claw");
        wrist = hardwareMap.get(ServoImplEx.class, "wrist");
        this.position = startPos;

        wrist.setPwmRange(new PwmControl.PwmRange(500, 2500));
        claw.setPwmRange(new PwmControl.PwmRange(500, 2500));

        //wrist.setPosition(pWStart);
        //claw.setPosition(pCStart);
    }


    @Override
    public void periodic() {
        telemetry.addData("Wrist Position", wrist.getPosition());
        telemetry.addData("Claw Position", claw.getPosition());
    }

    private double range(double increment){
        position = MathUtils.clamp(position + increment, 0, 1);
        return position;
    }

    public void setWristPosition(double target) {
        wrist.setPosition(range(target));
    }
    public void setClawPosition(double target) {
        claw.setPosition(range(target));
    }

}