package org.firstinspires.ftc.teamcode.subsystems;
import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.controller.wpilibcontroller.ArmFeedforward;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.Telemetry;

// tuning guide: https://docs.wpilib.org/en/stable/docs/software/advanced-controls/introduction/introduction-to-feedforward.html#introduction-to-dc-motor-feedforward
@Config
public class DoubleJointArm {

    private DcMotorEx joint1Motor1, joint1Motor2, joint2Motor;

    // Final Variables
    private static final int ticksPerRev = 1680; // Motor type: NeveRest Classic 60 Gearmotor JST-VH-2 Connector
    private static final double ENCODER_TICKS_TO_RADIANS = 2 * Math.PI / ticksPerRev; // Each tick to radian

    // CHANGE!!!
    private static final double L1 = 10.0; // Length of first arm - closest to base
    private static final double L2 = 10.0; // Length of second arm - intake part
    private static final double Kp = 0.01, Ki = 0.0, Kd = 0.0; // PID coefficients: p is proportional grain, i is integral, d is derivative

    // Calculation Variables
    private double sumOfErrors1 = 0, sumOfErrors2 = 0, lastError1 = 0, lastError2 = 0;
    private ElapsedTime timer = new ElapsedTime();


    public DoubleJointArm(DcMotorEx joint1Motor1, DcMotorEx joint1Motor2, DcMotorEx joint2Motor) {
        this.joint1Motor1 = joint1Motor1;
        this.joint1Motor2 = joint1Motor2;
        this.joint2Motor = joint2Motor;

        joint1Motor2.setDirection(DcMotorSimple.Direction.REVERSE);
        setZeroPowerBehavior();
    }

    private void setZeroPowerBehavior() {
        joint1Motor1.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        joint1Motor2.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        joint2Motor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
    }

    public void controlArm(double targetX, double targetY) {
        double[] targetAngles = inverseKinematics(targetX, targetY); // Calculate target joint angles

        double joint1Power = pidControl(joint1Motor1, targetAngles[0], 1);
        double joint2Power = pidControl(joint2Motor, targetAngles[1], 2);

        joint1Motor1.setPower(joint1Power);
        joint1Motor2.setPower(joint1Power);
        joint2Motor.setPower(joint2Power);
    }

    private double pidControl(DcMotorEx motor, double targetAngle, int jointNum) {
        double currentAngle = getJointAngle(motor);
        double error = targetAngle - currentAngle;

        double proportional = Kp * error;

        double integral = 0;
        if (jointNum == 1) {
            sumOfErrors1 += error * timer.seconds();
            integral = Ki * sumOfErrors1;
        } else if (jointNum == 2) {
            sumOfErrors2 += error * timer.seconds();
            integral = Ki * sumOfErrors2;
        }

        double derivative = 0;
        if (jointNum == 1) {
            derivative = Kd * ((error - lastError1) / timer.seconds());
            lastError1 = error;
        } else if (jointNum == 2) {
            derivative = Kd * ((error - lastError2) / timer.seconds());
            lastError2 = error;
        }

        timer.reset(); // Reset timer for next calculation

        return proportional + integral + derivative; // Out Power = combined PID
    }

    private double[] inverseKinematics(double x, double y) { // angles from target pos
        double r = Math.sqrt(x * x + y * y);
        double alpha = Math.acos((L1 * L1 + r * r - L2 * L2) / (2 * L1 * r));
        double beta = Math.atan2(y, x);
        double theta1 = beta - alpha;

        double gamma = Math.acos((L1 * L1 + L2 * L2 - r * r) / (2 * L1 * L2));
        double theta2 = Math.PI - gamma;

        return new double[]{theta1, theta2};
    }

    public double getJointAngle(DcMotorEx motor) { // current angle
        return motor.getCurrentPosition() * ENCODER_TICKS_TO_RADIANS;
    }
    public double getJoint1Angle() {
        return getJointAngle(joint1Motor1);
    }
    public double getJoint2Angle() {
        return getJointAngle(joint2Motor);
    }
}
