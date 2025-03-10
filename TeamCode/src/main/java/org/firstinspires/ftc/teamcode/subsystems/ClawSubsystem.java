package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.util.MathUtils;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PwmControl;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;  

// https://docs.ftclib.org/ftclib/command-base/command-system/subsystems
@Config
public class ClawSubsystem extends SubsystemBase {

    Telemetry telemetry;
    public ServoImplEx claw, wrist; // wrist rotates claw
    public double position;
    double startPos = 0;
    boolean wristDirectionForward = true;

    public static double W_target = 0, C_target = 0; // in degrees
    public static double pWStart = 0.17, pW90 = 0.19, pW180 = 0.21;
    public static double pWPos = pWStart;
    public static double pCClosed = 0.25, pCOpen = 0.55;
    public static double pCPos = pCClosed;

    public ClawSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        claw = hardwareMap.get(ServoImplEx.class, "claw");
        wrist = hardwareMap.get(ServoImplEx.class, "wrist");
        this.position = startPos;

        wrist.setPwmRange(new PwmControl.PwmRange(500, 2500));
        claw.setPwmRange(new PwmControl.PwmRange(500, 2500));

        wrist.setPosition(pWStart);
        claw.setPosition(pCClosed);
    }

    public void changeWristPosition() {
        if (pWPos == pWStart) {
            setWristPosition(pW90);
            pWPos = pW90;
        } else if (pWPos == pW90) {
            setWristPosition(pW180);
            pWPos = pW180;
        } else {
            setWristPosition(pWStart);
            pWPos = pWStart;
        }
    }

    public void changeClawState() {
        if (pCPos == pCClosed){
            claw.setPosition(pCOpen);
            pCPos = pCOpen;
        } else {
            claw.setPosition(pCClosed);
            pCPos = pCClosed;
        }
    }

    @Override
    public void periodic() {
        telemetry.addData("Wrist Position", wrist.getPosition());
        telemetry.addData("Claw Position", claw.getPosition());
    }

    public void clawOpen() {
        claw.setPosition(pCOpen);
    }

    public void clawClosed() {
        claw.setPosition(pCClosed);
    }

    /*private double range(double increment){
        position = MathUtils.clamp(position + increment, 0, 1);
        return position;
    }*/

    public void setWristPosition(double target) {
        wrist.setPosition(target);
    }
    public void setClawPosition(double target) {
        claw.setPosition(target);
    }

}