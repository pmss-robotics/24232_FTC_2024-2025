package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

// tuning guide: https://docs.wpilib.org/en/stable/docs/software/advanced-controls/introduction/introduction-to-feedforward.html#introduction-to-dc-motor-feedforward
@Config
public class FeedForwardArmSubsystem extends SubsystemBase {

    public DcMotorEx shoulder1, shoulder2, elbow;
    Telemetry telemetry;

    public static double shoulderTicksPerRev = 2786.2, elbowTicksPerRev = 3895.9;
    // to find kS, it is the amount of voltage before the arm starts moving.
    // I Have no freaking clue how to tune kCos and kV
    // ignore kA if the component has not much inertia
    //shoulder
    //At Different Positions
    public static double shoulderAngleOffset = 0.98097251045; //0.56377730486; //250 / shoulderTicksPerRev * (2 * Math.PI);
    public static double sP1 = 0.0347, sI1 = 0.000, sD1 = 0.000;
    public static double ks1_Cos = 0.043;

    public static double sP2 = 0.018, sI2 = 0.000, sD2 = 0.001;
    public static double ks2_Cos = 0.015;

    //elbow
    public static double elbowAngleOffset = 2.3561944902;
    public static double eP = 0.02, eI = 0.000, eD = 0.000;
    public static double ke_Cos = 0.01;

    //ArmFeedforward shoulderFeedforward = new ArmFeedforward(ks_S, ks_Cos, ks_V, ks_A);

    // in degrees
    // TODO: Telemetry get position then input values in the following
    public static double shoulderTarget, elbowTarget; //change to int after getting positions in degrees
    public double maxShoulderAngle = 200, maxElbowAngle = 300;

    //Shoulder position constants
    public static int ps_Home = 0;
    public static int ps_Bucket = 600, ps_SpecimenOut1 = 650, ps_SpecimenOut2 = 400;
    public static int ps_SpecimenIn1 = 300, ps_SpecimenIn2 = 500, ps_SubmersibleIn = 280, ps_SubmersibleIntake = 180;

    //Elbow position constants
    public static int pe_Home = 100;
    public static int pe_Bucket = 1200;
    public static int pe_SubmersibleIn1 = 1750, pe_SubmersibleIn2 = 1790, pe_SubmersibleOut = 1700, pe_TuckIn = 350, pe_Observation = 1400;

    // TODO: CHANGE
    public static double shoulderTolerance = 15;
    public static double elbowTolerance = 15;


    // TODO: Change how you would like it
    public static double manualShoulderPower = 1;

    public PIDController shoulderPidController, elbowPidController;
    private VoltageSensor voltageSensor;

    public FeedForwardArmSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        shoulder1 = hardwareMap.get(DcMotorEx.class, "shoulderMotor1");
        shoulder2 = hardwareMap.get(DcMotorEx.class, "shoulderMotor2");
        shoulder1.setDirection(DcMotorSimple.Direction.REVERSE);
        elbow = hardwareMap.get(DcMotorEx.class, "elbowMotor");

        resetEncoders();
        setZeroPowerBehavior();

        //shoulder
        shoulderPidController = new PIDController(sP1, sI1, sD1);
        shoulderPidController.setTolerance(shoulderTolerance);
        voltageSensor = hardwareMap.voltageSensor.iterator().next();
        shoulderTarget = 0;

        //elbow
        elbowPidController = new PIDController(eP, eI, eD);
        elbowPidController.setTolerance(elbowTolerance);
        elbowTarget = 0;
    }

    public void setShoulderSubmersible() {
        shoulderMoveTo(ps_SubmersibleIn);
    }

    @Override
    public void periodic() {
        telemetry.addData("Shoulder Target: ", shoulderTarget);
        telemetry.addData("Shoulder Position: ", shoulder1.getCurrentPosition());
        telemetry.addData("Elbow Target: ", elbowTarget);
        telemetry.addData("Elbow Position: ", elbow.getCurrentPosition());
    }

    public void holdPosition() {
        elbowHoldPosition();
        shoulderHoldPosition();
    }

    public void elbowMoveTo(double target){
        this.elbowTarget = target;
        elbowHoldPosition();
    }
    public void elbowSlowTo(double target){
        this.elbowTarget = target;
        double power = elbowCalculate();
        elbow.setPower(power);
    }

    public void elbowHoldPosition() {
        double power = elbowCalculate();
        elbow.setPower(power);
    }

    public void shoulderMoveTo(double target){
        this.shoulderTarget = target;
        shoulderHoldPosition();
    }


    public void shoulderHoldPosition() {
        double power;
        if(shoulder1.getCurrentPosition() <= 350 && elbow.getCurrentPosition() >= 1100) {
            shoulderPidController = new PIDController(sP1, sI1, sD1);
            power = calculate(sP1, sI1, sD1, ks1_Cos, shoulderAngleOffset);
        } else {
            shoulderPidController = new PIDController(sP2, sI2, sD2);
            power = calculate(sP2, sI2, sD2, ks2_Cos, shoulderAngleOffset);
        }
        shoulder1.setPower(power);
        shoulder2.setPower(power);
    }

    // MANUAL
    public void manual(boolean forward) { //manually moving the motor; need forward = true or false
        double power;
        if(forward) {
            power = manualShoulderPower;
        }else {
            power = manualShoulderPower * -1;
        }
        shoulder1.setPower(power);
        shoulder2.setPower(power);
    }

    private double calculate(double p, double i, double d, double cosine, double angleDifference) {
        shoulderPidController.setPID(p,i,d);
        shoulderPidController.setTolerance(shoulderTolerance);
        int current = shoulder1.getCurrentPosition();

        double power = shoulderPidController.calculate(current, shoulderTarget);
        double angle = (2 * Math.PI * current) / shoulderTicksPerRev;

        power += cosine * Math.cos(angle - angleDifference); //angleDifference radians
        power /= voltageSensor.getVoltage();

        if (power < 0) {
            power = Math.max(-0.6, power);
        } else if (power > 0){
            power = Math.min(power, 0.6);
        }

        telemetry.addData("Shoulder Power:", "%.6f", power);
        telemetry.addData("Shoulder Angle:", Math.toDegrees(angle));
        return power;
    }

    /*private double shoulderCalculate() {
        shoulderPidController.setPID(sP,sI,sD);
        shoulderPidController.setTolerance(shoulderTolerance);
        int current = shoulder1.getCurrentPosition();

        double power = shoulderPidController.calculate(current, shoulderTarget);
        double angle = (2 * Math.PI * current) / shoulderTicksPerRev;

        power += ks_Cos * Math.cos(angle);
        power /= voltageSensor.getVoltage();

        if (power < 0) {
            power = Math.max(-0.6, power);
        } else if (power > 0){
            power = Math.min(power, 0.6);
        }

        telemetry.addData("Shoulder Power:", "%.6f", power);
        return power;
    }*/

    private double elbowCalculate() {
        elbowPidController.setPID(eP,eI,eD);
        elbowPidController.setTolerance(elbowTolerance);
        int current = elbow.getCurrentPosition();

        double power = elbowPidController.calculate(current, elbowTarget);
        double angle = (2 * Math.PI * current) / elbowTicksPerRev;

        power += ke_Cos * Math.cos(angle);
        power /= voltageSensor.getVoltage();

        if (power < 0) {
            power = Math.max(-0.6, power);
        } else if (power > 0){
            power = Math.min(power, 0.6);
        }

        telemetry.addData("Elbow Power:", "%.6f", power);
        telemetry.addData("Elbow Angle:", Math.toDegrees(angle));
        return power;
    }

    private void resetEncoders() {
        shoulder1.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        shoulder2.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        elbow.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        shoulder1.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        shoulder2.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        elbow.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
    }

    private void setZeroPowerBehavior() {
        shoulder1.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        shoulder2.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        elbow.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    }
}