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

    // write in degrees
    public static double W_target = 0, C_target = 0; // in degrees
    public static double pWStart = 0.158, pW90 = 0.180, pW180 = 0.207;
    public static double pCClosed = 0.42, pCOpen = 0.69;

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
        /* Rotating Back and Forth
        if (wrist.getPosition() == pWStart){

        } else if (wrist.getPosition() == pW90) {
            if (wristDirectionForward){
                setWristPosition(pW180);
            } else {
                setWristPosition(pWStart);
            }
        } else if (wrist.getPosition() == pW180){
            if (wristDirectionForward){
                setWristDirection();
                setWristPosition(pW90);
            }
        }*/

        if (wrist.getPosition() == pWStart) {
            setWristPosition(pW90);
        } else if (wrist.getPosition() == pW90) {
            setWristPosition(pW180);
        } else if (wrist.getPosition() == pW180) {
            setWristPosition(pWStart);
        } else {
            setWristPosition(pWStart);
        }
    }
    public void changeClawState() {
        if (claw.getPosition() == pCClosed){
            claw.setPosition(pCOpen);
        } else {
            claw.setPosition(pCClosed);
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