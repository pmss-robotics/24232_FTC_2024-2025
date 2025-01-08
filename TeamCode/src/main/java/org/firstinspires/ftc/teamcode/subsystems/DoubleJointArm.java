package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.controller.wpilibcontroller.ArmFeedforward;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;


// tuning guide: https://docs.wpilib.org/en/stable/docs/software/advanced-controls/introduction/introduction-to-feedforward.html#introduction-to-dc-motor-feedforward
@Config
public class DoubleJointArm {

    private DcMotorEx joint1Motor1, joint1Motor2, joint2Motor;

    // Telemetry
    private Telemetry telemetry;

    // Constants
    private static final int ticksPerRev = 1680;
    private static final double ENCODER_TICKS_TO_RADIANS = 2 * Math.PI / ticksPerRev;

    // Arm dimensions
    private static final double L1 = 33.5; // Length of first arm
    private static final double L2 = 45.0; // Length of second arm

    // PID Coefficients
    private static final double Kp = 0.04, Ki = 0.0, Kd = 0.0;

    // Calculation Variables
    private double sumOfErrors1 = 0, sumOfErrors2 = 0, lastError1 = 0, lastError2 = 0;
    private ElapsedTime timer1 = new ElapsedTime();
    private ElapsedTime timer2 = new ElapsedTime();

    public DoubleJointArm(DcMotorEx joint1Motor1, DcMotorEx joint1Motor2, DcMotorEx joint2Motor, Telemetry telemetry) {
        this.joint1Motor1 = joint1Motor1;
        this.joint1Motor2 = joint1Motor2;
        this.joint2Motor = joint2Motor;
        this.telemetry = telemetry;

        joint1Motor2.setDirection(DcMotorSimple.Direction.REVERSE);
        resetEncoders();
        setZeroPowerBehavior();
    }

    private void resetEncoders() {
        joint1Motor1.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        joint1Motor2.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        joint2Motor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        joint1Motor1.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        joint1Motor2.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        joint2Motor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
    }

    private void setZeroPowerBehavior() {
        joint1Motor1.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        joint1Motor2.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        joint2Motor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    }

    public void controlArm(double targetX, double targetY) {
        double[] targetAngles = inverseKinematics(targetX, targetY);

        double joint1Power = pidControl(joint1Motor1, targetAngles[0], 1);
        double joint2Power = pidControl(joint2Motor, targetAngles[1], 2);

        joint1Motor1.setPower(applyPowerThreshold(joint1Power));
        joint1Motor2.setPower(applyPowerThreshold(joint1Power));
        joint2Motor.setPower(applyPowerThreshold(joint2Power));
    }

    private double applyPowerThreshold(double power) {
        return Math.abs(power) < 0.1 ? 0.0 : power;
    }

    private double pidControl(DcMotorEx motor, double targetAngle, int jointNum) {
        double currentAngle = getJointAngle(motor);
        double error = targetAngle - currentAngle;

        // PID components
        double proportional = Kp * error;
        double deltaTime = jointNum == 1 ? Math.max(timer1.seconds(), 0.001) : Math.max(timer2.seconds(), 0.001);

        double integral = 0;
        if (jointNum == 1) {
            sumOfErrors1 += error * deltaTime;
            sumOfErrors1 = Math.max(-100, Math.min(100, sumOfErrors1)); // Prevent windup
            integral = Ki * sumOfErrors1;
        } else if (jointNum == 2) {
            sumOfErrors2 += error * deltaTime;
            sumOfErrors2 = Math.max(-100, Math.min(100, sumOfErrors2)); // Prevent windup
            integral = Ki * sumOfErrors2;
        }

        double derivative = 0;
        if (jointNum == 1) {
            derivative = Kd * ((error - lastError1) / deltaTime);
            lastError1 = error;
            timer1.reset();
        } else if (jointNum == 2) {
            derivative = Kd * ((error - lastError2) / deltaTime);
            lastError2 = error;
            timer2.reset();
        }

        // Debugging telemetry
        telemetry.addData("Joint " + jointNum + " Error", error);
        telemetry.addData("Joint " + jointNum + " Proportional", proportional);
        telemetry.addData("Joint " + jointNum + " Integral", integral);
        telemetry.addData("Joint " + jointNum + " Derivative", derivative);

        double power = proportional + integral + derivative;
        return Math.max(-1.0, Math.min(1.0, power));
    }

    private double[] inverseKinematics(double x, double y) {
        double r = Math.sqrt(x * x + y * y);
        r = Math.min(r, L1 + L2);
        r = Math.max(r, Math.abs(L1 - L2));

        double alpha = Math.acos((L1 * L1 + r * r - L2 * L2) / (2 * L1 * r));
        double beta = Math.atan2(y, x);
        double theta1 = beta - alpha;

        double gamma = Math.acos((L1 * L1 + L2 * L2 - r * r) / (2 * L1 * L2));
        double theta2 = Math.PI - gamma;

        return new double[]{Math.max(-Math.PI, Math.min(Math.PI, theta1)), Math.max(-Math.PI, Math.min(Math.PI, theta2))};
    }

    public double getJointAngle(DcMotorEx motor) {
        return motor.getCurrentPosition() * ENCODER_TICKS_TO_RADIANS;
    }

    public double getJoint1Angle() {
        return getJointAngle(joint1Motor1);
    }

    public double getJoint2Angle() {
        return getJointAngle(joint2Motor);
    }
}
