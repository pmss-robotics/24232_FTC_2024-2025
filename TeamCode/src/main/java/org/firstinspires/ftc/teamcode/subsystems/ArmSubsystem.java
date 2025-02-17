package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ArmSubsystem extends SubsystemBase {
    // declare hardware here
    public DcMotorEx shoulderMotor1, shoulderMotor2, elbowMotor;
    private Telemetry telemetry;

    private static final double TICKS_PER_REV1 = 1680, TICKS_PER_REV2 = 3892;

    public PIDController shoulderPID = new PIDController(0.01, 0.0, 0.0);
    public PIDController elbowPID = new PIDController(0.01, 0.0, 0.0);
    double shoulderTolerance = 3;
    double elbowTolerance = 3;
    public static final double SHOULDER_MIN_MOTORPOS = 0, SHOULDER_MAX_MOTORPOS = 0;
    public static final double ELBOW_MIN_MOTORPOS = 0, ELBOW_MAX_MOTORPOS = 0;

    //Shoulder
    double pS_SpecimenOut = 1.0, pS_SpecimenIn =1.0, pS_SubmersibleIn = 1.0, pS_Bucket = 1.0;
    //Elbow
    double pE_pecimenOut = 1.0, pE_SpecimenIn =1.0, pE_SubmersibleIn = 1.0, pE_Bucket = 1.0;

    public ArmSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        shoulderMotor1 = hardwareMap.get(DcMotorEx.class, "joint1Motor1");
        shoulderMotor2 = hardwareMap.get(DcMotorEx.class, "joint1Motor2");
        elbowMotor = hardwareMap.get(DcMotorEx.class, "joint2Motor");

        this.telemetry = telemetry;

        shoulderMotor2.setDirection(DcMotorSimple.Direction.REVERSE);

        shoulderPID.setTolerance(shoulderTolerance);
        elbowPID.setTolerance(elbowTolerance);

        resetEncoders();
        setZeroPowerBehavior();
    }

    @Override
    public void periodic() {

    }

    public void shoulderHoldPosition() {
        setShoulderPower(shoulderToAngle(getShoulderAngle()));
    }
    public void elbowHoldPosition() {
        setElbowPower(elbowToAngle(getElbowAngle()));
    }
    public void holdPosition() {
        shoulderHoldPosition();
        elbowHoldPosition();
    }

    public void setShoulderPower(double power) {
        double shoulderPower = Math.max(-1.0, Math.min(1.0, power));
        shoulderMotor1.setPower(shoulderPower);
        shoulderMotor2.setPower(shoulderPower);
        /*double currentPosition = joint1Motor1.getCurrentPosition();

        if (currentPosition > JOINT1_MAX_MOTORPOS) {
            joint1Motor1.setPower(pivot1ToAngle(JOINT1_MAX_MOTORPOS));
            joint1Motor2.setPower(pivot1ToAngle(JOINT1_MAX_MOTORPOS));
        }
        else if (currentPosition < JOINT1_MIN_MOTORPOS) {
            joint1Motor1.setPower(pivot1ToAngle(JOINT1_MIN_MOTORPOS));
            joint1Motor2.setPower(pivot1ToAngle(JOINT1_MIN_MOTORPOS));
        }
        else{
            double joint1Power = Math.max(-1.0, Math.min(1.0, power));
            joint1Motor1.setPower(joint1Power);
            joint1Motor2.setPower(joint1Power);
        }*/
    }
    public void setElbowPower(double power) {
        double joint2Power = Math.max(-1.0, Math.min(1.0, power));
        elbowMotor.setPower(joint2Power);

        /*double currentPosition = joint2Motor.getCurrentPosition();

        if (currentPosition > JOINT2_MAX_MOTORPOS) {
            joint2Motor.setPower(pivot1ToAngle(JOINT2_MAX_MOTORPOS));
        }
        else if (currentPosition < JOINT2_MIN_MOTORPOS) {
            joint2Motor.setPower(pivot1ToAngle(JOINT2_MIN_MOTORPOS));
        }
        else{
            double joint2Power = Math.max(-1.0, Math.min(1.0, power));
            joint2Motor.setPower(joint2Power);
        }*/
    }

    public double getShoulderAngle() {
        return shoulderMotor1.getCurrentPosition() * (2*Math.PI/TICKS_PER_REV1);
    }

    public double getElbowAngle(){
        return elbowMotor.getCurrentPosition() * (2*Math.PI/TICKS_PER_REV2);
    }

    public double shoulderToAngle(double targetAngle) {
        double currentAngle = getElbowAngle();
        return shoulderPID.calculate(currentAngle, targetAngle);

    }
    public double elbowToAngle(double targetAngle){
        double currentAngle = getShoulderAngle();
        return elbowPID.calculate(currentAngle, targetAngle);
    }

    private void resetEncoders() {
        shoulderMotor1.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        shoulderMotor2.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        elbowMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        shoulderMotor1.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        shoulderMotor2.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        elbowMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
    }

    private void setZeroPowerBehavior() {
        shoulderMotor1.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        shoulderMotor2.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        elbowMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    }
}
