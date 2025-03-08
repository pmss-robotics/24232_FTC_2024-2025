package com.example.meepmeeptesting;


import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static double specimenIntakeWaitTime = 1.0;
    public static double specimenOuttakeWaitTime = 0.5;
    public static double submersibleIntakeWaitTime = 2.0;

    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width+-

                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive ().actionBuilder(new Pose2d(7.05, -36.00, Math.toRadians(90.00)))
                .turnTo(Math.toRadians(-90))
                .splineToConstantHeading(new Vector2d(40.00, -54.00), Math.toRadians(90.00))
                .splineToConstantHeading(new Vector2d(40.00, -58.00), Math.toRadians(90.00))


//                .splineToConstantHeading(new Vector2d(2.75, -32.06), Math.toRadians(112.70))
//                .splineToSplineHeading(new Pose2d(42.32, -55.33, Math.toRadians(-90.00)), Math.toRadians(-33.21))
//                .splineToConstantHeading(new Vector2d(42.14, -64.85), Math.toRadians(-88.26))
//                .splineToSplineHeading(new Pose2d(7.33, -32.24, Math.toRadians(90.00)), Math.toRadians(136.87))
//
//                .splineToConstantHeading(new Vector2d(8.43, -33.50), Math.toRadians(112.70))
//
//                .splineToConstantHeading(new Vector2d(36.27, -26.20), Math.toRadians(90.00))
//                .splineToConstantHeading(new Vector2d(38.84, -20.34), Math.toRadians(90.00))
//                .splineToConstantHeading(new Vector2d(52.40, -48.55), Math.toRadians(-75.37))
//                .splineToConstantHeading(new Vector2d(46.17, -20.52), Math.toRadians(90.00))
//                .splineToConstantHeading(new Vector2d(60.46, -48.18), Math.toRadians(270.00))
//                .splineToConstantHeading(new Vector2d(55.15, -20.34), Math.toRadians(90.00))
//                .splineToConstantHeading(new Vector2d(69.24, -48.50), Math.toRadians(270.00))
//                .splineToSplineHeading(new Pose2d(40.00, -54.50, Math.toRadians(-90.00)), Math.toRadians(-90.00))
//                .splineToSplineHeading(new Pose2d(40.00, -64.50, Math.toRadians(90.00)), Math.toRadians(-90.00))
//
//                .splineToSplineHeading(new Pose2d(4.58, -33.50, Math.toRadians(-90.00)), Math.toRadians(141.17))
//
//                .splineToSplineHeading(new Pose2d(40.00, -54.50, Math.toRadians(-90.00)), Math.toRadians(90.00))
//
//                .splineToConstantHeading(new Vector2d(8.43, -33.50), Math.toRadians(112.70))
//                .splineToConstantHeading(new Vector2d(36.27, -26.20), Math.toRadians(90.00))
//                .splineToConstantHeading(new Vector2d(38.84, -17.77), Math.toRadians(90.00))
//                .splineToConstantHeading(new Vector2d(52.40, -48.55), Math.toRadians(-75.37))
//                .splineToConstantHeading(new Vector2d(46.17, -18.14), Math.toRadians(90.00))
//                .splineToConstantHeading(new Vector2d(60.46, -48.18), Math.toRadians(270.00))
//                .splineToConstantHeading(new Vector2d(55.33, -17.22), Math.toRadians(90.00))
//                .splineToConstantHeading(new Vector2d(67.24, -48.50), Math.toRadians(270.00))
//                .splineToSplineHeading(new Pose2d(40.00, -54.50, Math.toRadians(-90.00)), Math.toRadians(-90.00))
//                .splineToSplineHeading(new Pose2d(40.00, -64.50, Math.toRadians(90.00)), Math.toRadians(-90.00))
//                .splineToSplineHeading(new Pose2d(4.58, -33.50, Math.toRadians(90.00)), Math.toRadians(141.17))
//
//                .splineToConstantHeading(new Vector2d(8.43, -35.00), Math.toRadians(112.70))
//
//                .splineToConstantHeading(new Vector2d(36.27, -26.20), Math.toRadians(90.00))
//                .splineToConstantHeading(new Vector2d(38.84, -17.77), Math.toRadians(90.00))
//                .splineToConstantHeading(new Vector2d(52.40, -48.55), Math.toRadians(-75.37))
//                .splineToConstantHeading(new Vector2d(46.17, -18.14), Math.toRadians(90.00))
//                .splineToConstantHeading(new Vector2d(60.46, -48.18), Math.toRadians(270.00))
//                .splineToConstantHeading(new Vector2d(55.33, -17.22), Math.toRadians(90.00))
//                .splineToConstantHeading(new Vector2d(67.24, -48.50), Math.toRadians(270.00))
//                .splineToSplineHeading(new Pose2d(40.00, -54.50, Math.toRadians(-90.00)), Math.toRadians(-90.00))
//                .splineToSplineHeading(new Pose2d(40.00, -64.50, Math.toRadians(90.00)), Math.toRadians(-90.00))
//                        .setTangent(Math.toRadians(125))
//                .splineToSplineHeading(new Pose2d(4.58, -35.00, Math.toRadians(90.00)), Math.toRadians(141.17))
//
////                .splineToConstantHeading(new Vector2d(8.43, -34.00), Math.toRadians(112.70))
////                        .setTangent(Math.toRadians(-30))
////                .splineToConstantHeading(new Vector2d(35.54, -26.75), Math.toRadians(90.00))
////                .splineToConstantHeading(new Vector2d(37.74, -14.66), Math.toRadians(90.00))
////                .splineToConstantHeading(new Vector2d(51.48, -48.92), Math.toRadians(-75.37))
////                .splineToConstantHeading(new Vector2d(46.35, -15.39), Math.toRadians(90.00))
////                .splineToConstantHeading(new Vector2d(58.44, -48.50), Math.toRadians(270.00))
////                .splineToConstantHeading(new Vector2d(55.69, -15.21), Math.toRadians(90.00))
////                .splineToConstantHeading(new Vector2d(67.24, -48.50), Math.toRadians(270.00))
////                .splineToSplineHeading(new Pose2d(40.00, -54.50, Math.toRadians(-90.00)), Math.toRadians(-90.00))
////                .splineToSplineHeading(new Pose2d(40.00, -64.50, Math.toRadians(90.00)), Math.toRadians(-90.00))
//                        .setTangent(Math.toRadians(125))
//                .splineToSplineHeading(new Pose2d(4.58, -34.00, Math.toRadians(90.00)), Math.toRadians(141.17))

//                .splineToConstantHeading(new Vector2d(8.43, -34.00), Math.toRadians(112.70))
//                .splineToConstantHeading(new Vector2d(35.54, -26.75), Math.toRadians(90.00))
//                .splineToConstantHeading(new Vector2d(37.74, -14.66), Math.toRadians(90.00))
//                .splineToConstantHeading(new Vector2d(51.48, -48.92), Math.toRadians(-75.37))
//                .splineToConstantHeading(new Vector2d(46.35, -15.39), Math.toRadians(90.00))
//                .splineToConstantHeading(new Vector2d(58.44, -48.50), Math.toRadians(270.00))
//                .splineToConstantHeading(new Vector2d(55.69, -15.21), Math.toRadians(90.00))
//                .splineToConstantHeading(new Vector2d(67.24, -48.50), Math.toRadians(270.00))
//                .splineToSplineHeading(new Pose2d(40.00, -54.50, Math.toRadians(-90.00)), Math.toRadians(90.00))
//                .splineToSplineHeading(new Pose2d(40.00, -64.50, Math.toRadians(90.00)), Math.toRadians(-90.00))
//                        //.turn(Math.toRadians(180))
//                        .setTangent(Math.toRadians(125))
//                .splineToSplineHeading(new Pose2d(4.58, -34.00, Math.toRadians(90)), Math.toRadians(141.17))

//                .splineToConstantHeading(new Vector2d(8.24, -32.00), Math.toRadians(112.70))
//
//                .splineToConstantHeading(new Vector2d(35.54, -26.75), Math.toRadians(90.00))
//                .splineToConstantHeading(new Vector2d(35.54, -26.75), Math.toRadians(90.00))
//                .splineToConstantHeading(new Vector2d(36.27, -16.67), Math.toRadians(90.00))
//                .splineToConstantHeading(new Vector2d(48.37, -47.45), Math.toRadians(-75.37))
//                .splineToConstantHeading(new Vector2d(45.98, -15.39), Math.toRadians(90.00))
//                .splineToConstantHeading(new Vector2d(56.06, -47.45), Math.toRadians(270.00))
//                .splineToConstantHeading(new Vector2d(54.41, -15.02), Math.toRadians(90.00))
//                .splineToConstantHeading(new Vector2d(63.57, -47.08), Math.toRadians(270.00))
//                .splineToSplineHeading(new Pose2d(40.00, -54.50, Math.toRadians(-90.00)), Math.toRadians(90.00))
//                .splineToConstantHeading(new Vector2d(40.00, -61.50), Math.toRadians(-90.00))
//                        .turn(Math.toRadians(180))
//                .splineToSplineHeading(new Pose2d(2.75, -31.51, Math.toRadians(90.00)), Math.toRadians(141.17))




//                .splineToConstantHeading(new Vector2d(8.24, -32.00), Math.toRadians(112.78))
//                .splineToConstantHeading(new Vector2d(35.54, -26.75), Math.toRadians(90.00))
//                .splineToConstantHeading(new Vector2d(36.27, -16.67), Math.toRadians(90.00))
//                .splineToConstantHeading(new Vector2d(48.37, -47.45), Math.toRadians(-75.37))
//                .splineToConstantHeading(new Vector2d(42.69, -17.22), Math.toRadians(90.00))
//                .splineToConstantHeading(new Vector2d(58.99, -47.45), Math.toRadians(-90.00))
//                .splineToConstantHeading(new Vector2d(55.51, -14.47), Math.toRadians(86.94))
//                .splineToConstantHeading(new Vector2d(62.84, -28.21), Math.toRadians(-68.40))
//                .splineToConstantHeading(new Vector2d(64.49, -47.82), Math.toRadians(-85.19))
//                .splineToLinearHeading(new Pose2d(38.00, -52.00, Math.toRadians(-90.00)), Math.toRadians(180.73))
//                .splineToConstantHeading(new Vector2d(38.00, -62.00), Math.toRadians(269.13))
//                .splineToLinearHeading(new Pose2d(5.50, -32.00, Math.toRadians(90.00)), Math.toRadians(137.35))
//                .splineTo(new Vector2d(38.00, -52.50), Math.toRadians(90.00))
//                .splineToConstantHeading(new Vector2d(38.00, -62.00), Math.toRadians(-45.00))
//                .splineToLinearHeading(new Pose2d(2.38, -32.00, Math.toRadians(90.00)), Math.toRadians(139.95))
//                .splineToLinearHeading(new Pose2d(38.00, -52.50, Math.toRadians(270.00)), Math.toRadians(90.00))
//                .splineToConstantHeading(new Vector2d(38.00, -62.00), Math.toRadians(257.86))
//                .splineToLinearHeading(new Pose2d(0.00, -32.00, Math.toRadians(90.00)), Math.toRadians(141.60))
//                .splineToLinearHeading(new Pose2d(38.00, -52.50, Math.toRadians(-90.00)), Math.toRadians(-30.81))
//                .splineToConstantHeading(new Vector2d(38.00, -62.00), Math.toRadians(252.26))
//
//
////                .splineToConstantHeading(new Vector2d(8.24, -35.00), Math.toRadians(112.78))
////                        .setTangent(Math.toRadians(-30))
////                .splineToConstantHeading(new Vector2d(35.54, -26.75), Math.toRadians(90.00))
////                .splineToConstantHeading(new Vector2d(38.11, -13.37), Math.toRadians(90.00))
////                .splineToConstantHeading(new Vector2d(48.73, -48.92), Math.toRadians(-75.37))
////                .splineToConstantHeading(new Vector2d(45.44, -13.19), Math.toRadians(90.00))
////                .splineToConstantHeading(new Vector2d(57.53, -48.37), Math.toRadians(-90.00))
////                .splineToConstantHeading(new Vector2d(56.24, -13.56), Math.toRadians(86.94))
////                .splineToConstantHeading(new Vector2d(65.22, -49.10), Math.toRadians(-100.00))
////                .splineToSplineHeading(new Pose2d(40.00, -52.00, Math.toRadians(-90.00)), Math.toRadians(181.28))
//                .splineToConstantHeading(new Vector2d(40.00, -61.50), Math.toRadians(90.00))
//                        .setReversed(true)
//                        .setTangent(Math.toRadians(125))
//                .splineToLinearHeading(new Pose2d(2.93, -34.81, Math.toRadians(90.00)), Math.toRadians(270.00))



//                .splineToConstantHeading(new Vector2d(8.24, -37.50), Math.toRadians(112.78))
//
//                .setTangent(Math.toRadians(-30))
//                .splineToConstantHeading(new Vector2d(35.54, -26.75), Math.toRadians(90.00))
//                .splineToConstantHeading(new Vector2d(38.11, -13.37), Math.toRadians(90.00))
//                .splineToConstantHeading(new Vector2d(48.73, -48.92), Math.toRadians(-75.37))
//                .splineToConstantHeading(new Vector2d(45.44, -13.19), Math.toRadians(90.00))
//                .splineToConstantHeading(new Vector2d(57.53, -48.37), Math.toRadians(-90.00))
//                .splineToConstantHeading(new Vector2d(56.24, -13.56), Math.toRadians(86.94))
//                .splineToConstantHeading(new Vector2d(65.22, -49.10), Math.toRadians(-100.00))
//                .splineToSplineHeading(new Pose2d(40.00, -52.50, Math.toRadians(-90.00)), Math.toRadians(90.00))
//                .splineToConstantHeading(new Vector2d(40.00, -61.01), Math.toRadians(-90.00))
//                //intake
//
//                .setTangent(Math.toRadians(125))
//                .splineToSplineHeading(new Pose2d(-2.56, -37.50, Math.toRadians(90.00)), Math.toRadians(136.65))
//                //out
//
//                .setTangent(Math.toRadians(-45))
//                .splineToSplineHeading(new Pose2d(40.00, -52.50, Math.toRadians(-90.00)), Math.toRadians(-45.00))
//                .splineToConstantHeading(new Vector2d(40.00, -61.00), Math.toRadians(-90.00))
//                //in
//
//                .setTangent(Math.toRadians(125))
//                .splineToSplineHeading(new Pose2d(0.73, -37.50, Math.toRadians(90.00)), Math.toRadians(139.26))
//
//                .setTangent(Math.toRadians(-45))
//                .splineToSplineHeading(new Pose2d(40.00, -52.50, Math.toRadians(-90.00)), Math.toRadians(-45.00))
//                .splineToConstantHeading(new Vector2d(40.00, -61.00), Math.toRadians(-90.00))
//
//                .setTangent(Math.toRadians(125))
//                .splineToSplineHeading(new Pose2d(3.48, -37.50, Math.toRadians(90.00)), Math.toRadians(90.00))
//
//                .setTangent(Math.toRadians(-45))
//                .splineToSplineHeading(new Pose2d(40.00, -52.50, Math.toRadians(-90.00)), Math.toRadians(-45.00))
//                .splineToConstantHeading(new Vector2d(40.00, -61.00), Math.toRadians(-90.00))
//
//                .setTangent(Math.toRadians(125))
//                .splineToSplineHeading(new Pose2d(25.28, -6.05, Math.toRadians(180.00)), Math.toRadians(180.00))

//                .splineToConstantHeading(new Vector2d(8.24, -37.50), Math.toRadians(112.78))
//                .setTangent(Math.toRadians(-30))
//                .splineToConstantHeading(new Vector2d(35.54, -26.75), Math.toRadians(90.00))
//                .splineToConstantHeading(new Vector2d(38.11, -13.37), Math.toRadians(90.00))
//                .splineToConstantHeading(new Vector2d(48.73, -48.92), Math.toRadians(-75.37))
//                .splineToConstantHeading(new Vector2d(45.44, -13.19), Math.toRadians(90.00))
//                .splineToConstantHeading(new Vector2d(57.53, -48.37), Math.toRadians(-90.00))
//                .splineToConstantHeading(new Vector2d(56.24, -13.56), Math.toRadians(86.94))
//                .splineToConstantHeading(new Vector2d(65.22, -49.10), Math.toRadians(-100.00))
//                .splineToSplineHeading(new Pose2d(40.00, -52.50, Math.toRadians(-90.00)), Math.toRadians(90.00))
//                .splineToConstantHeading(new Vector2d(40.00, -61.00), Math.toRadians(-90.00))
//                .setTangent(Math.toRadians(125))
//                .splineToSplineHeading(new Pose2d(-2.56, -37.50, Math.toRadians(90.00)), Math.toRadians(136.65))
//                .setTangent(Math.toRadians(-45))
//                .splineToSplineHeading(new Pose2d(40.00, -52.50, Math.toRadians(-90.00)), Math.toRadians(90.00))
//                .splineToConstantHeading(new Vector2d(40.00, -61.00), Math.toRadians(-90.00))
//                .setTangent(Math.toRadians(125))
//                .splineToSplineHeading(new Pose2d(0.73, -37.50, Math.toRadians(90.00)), Math.toRadians(139.26))
//                .setTangent(Math.toRadians(-45))
//                .splineToSplineHeading(new Pose2d(40.00, -52.50, Math.toRadians(-90.00)), Math.toRadians(90.00))
//                .splineToConstantHeading(new Vector2d(40.00, -61.00), Math.toRadians(-90.00))
//                .setTangent(Math.toRadians(125))
//                .splineToSplineHeading(new Pose2d(3.48, -37.50, Math.toRadians(90.00)), Math.toRadians(90.00))
//                .setTangent(Math.toRadians(-45))
//                .splineToSplineHeading(new Pose2d(40.00, -52.50, Math.toRadians(-90.00)), Math.toRadians(90.00))
//                .splineToConstantHeading(new Vector2d(40.00, -61.00), Math.toRadians(-90.00))
//                .setTangent(Math.toRadians(125))
//                .splineToSplineHeading(new Pose2d(6.05, -37.50, Math.toRadians(90.00)), Math.toRadians(90.00))

//                        .splineToConstantHeading(new Vector2d(8.24, -37.50), Math.toRadians(112.78))
//                        .setTangent(Math.toRadians(-30))
//                        .splineToConstantHeading(new Vector2d(35.54, -26.75), Math.toRadians(90.00))
//                        .splineToConstantHeading(new Vector2d(38.11, -13.37), Math.toRadians(90.00))
//                        .splineToConstantHeading(new Vector2d(48.73, -48.92), Math.toRadians(-75.37))
//                        .splineToConstantHeading(new Vector2d(45.44, -13.19), Math.toRadians(90.00))
//                        .splineToConstantHeading(new Vector2d(57.53, -48.37), Math.toRadians(-90.00))
//                        .splineToConstantHeading(new Vector2d(56.24, -13.56), Math.toRadians(86.94))
//                        .splineToConstantHeading(new Vector2d(65.22, -49.10), Math.toRadians(-100.00))
//                        .splineToSplineHeading(new Pose2d(40.00, -52.50, Math.toRadians(-90.00)), Math.toRadians(180.00))
//                        .splineToConstantHeading(new Vector2d(40.00, -61.00), Math.toRadians(-90.00))
//                        .setTangent(Math.toRadians(125))
//                        .splineToSplineHeading(new Pose2d(-2.56, -37.50, Math.toRadians(90.00)), Math.toRadians(136.65))
//                        .setTangent(Math.toRadians(-45))
//                        .splineToSplineHeading(new Pose2d(40.00, -52.50, Math.toRadians(-90.00)), Math.toRadians(-90.00))
//                        .splineToConstantHeading(new Vector2d(40.00, -61.00), Math.toRadians(-90.00))
//                        .setTangent(Math.toRadians(125))
//                        .splineToSplineHeading(new Pose2d(0.73, -37.50, Math.toRadians(90.00)), Math.toRadians(139.26))
//                        .setTangent(Math.toRadians(-45))
//                        .splineToSplineHeading(new Pose2d(40.00, -52.50, Math.toRadians(-90.00)), Math.toRadians(-90.00))
//                        .splineToConstantHeading(new Vector2d(40.00, -61.00), Math.toRadians(-90.00))
//                        .setTangent(Math.toRadians(125))
//                        .splineToSplineHeading(new Pose2d(3.48, -37.50, Math.toRadians(90.00)), Math.toRadians(90.00))
//                        .setTangent(Math.toRadians(-45))
//                        .splineToSplineHeading(new Pose2d(40.00, -52.50, Math.toRadians(-90.00)), Math.toRadians(-90.00))
//                        .splineToConstantHeading(new Vector2d(40.00, -61.00), Math.toRadians(-90.00))
//                        .setTangent(Math.toRadians(125))
//                        .splineToSplineHeading(new Pose2d(6.05, -37.50, Math.toRadians(90.00)), Math.toRadians(90.00))

//                        .splineToConstantHeading(new Vector2d(8.24, -40.67), Math.toRadians(112.78))
//                        .setTangent(Math.toRadians(-30))
//                        .splineToConstantHeading(new Vector2d(35.54, -26.75), Math.toRadians(90.00))
//                        .splineToConstantHeading(new Vector2d(38.11, -13.37), Math.toRadians(90.00))
//                        .splineToConstantHeading(new Vector2d(48.73, -48.92), Math.toRadians(-75.37))
//                        .splineToConstantHeading(new Vector2d(45.44, -13.19), Math.toRadians(90.00))
//                        .splineToConstantHeading(new Vector2d(57.53, -48.37), Math.toRadians(-90.00))
//                        .splineToConstantHeading(new Vector2d(56.24, -13.56), Math.toRadians(86.94))
//                        .splineToConstantHeading(new Vector2d(65.22, -49.10), Math.toRadians(-100.00))
//                        .splineToSplineHeading(new Pose2d(40.00, -52.50, Math.toRadians(-90.00)), Math.toRadians(180.00))
//                        .splineToConstantHeading(new Vector2d(40.00, -61.00), Math.toRadians(-90.00))
//                        .setTangent(Math.toRadians(125))
//                        .splineToSplineHeading(new Pose2d(-3.11, -37.56, Math.toRadians(90.00)), Math.toRadians(136.65))
//                        .setTangent(Math.toRadians(-45))
//                        .splineToSplineHeading(new Pose2d(40.00, -52.50, Math.toRadians(-90.00)), Math.toRadians(-90.00))
//                        .splineToConstantHeading(new Vector2d(40.00, -61.00), Math.toRadians(-90.00))
//                        .setTangent(Math.toRadians(125))
//                        .splineToSplineHeading(new Pose2d(0.18, -37.19, Math.toRadians(90.00)), Math.toRadians(139.26))
//                        .setTangent(Math.toRadians(-45))
//                        .splineToSplineHeading(new Pose2d(40.00, -52.50, Math.toRadians(-90.00)), Math.toRadians(-90.00))
//                        .splineToConstantHeading(new Vector2d(40.00, -61.00), Math.toRadians(-90.00))
//                        .setTangent(Math.toRadians(125))
//                        .splineToSplineHeading(new Pose2d(3.30, -37.19, Math.toRadians(90.00)), Math.toRadians(90.00))
//                        .setTangent(Math.toRadians(-45))
//                        .splineToSplineHeading(new Pose2d(40.00, -52.50, Math.toRadians(-90.00)), Math.toRadians(-90.00))
//                        .splineToConstantHeading(new Vector2d(40.00, -61.00), Math.toRadians(-90.00))
//                        .setTangent(Math.toRadians(125))
//                        .splineToSplineHeading(new Pose2d(6.05, -37.19, Math.toRadians(90.00)), Math.toRadians(90.00))

//                        .splineToConstantHeading(new Vector2d(8.24, -40.67), Math.toRadians(112.78))
//                        .setTangent(Math.toRadians(-30))
//                        .splineToConstantHeading(new Vector2d(35.54, -26.75), Math.toRadians(90.00))
//                        .splineToConstantHeading(new Vector2d(38.11, -13.37), Math.toRadians(90.00))
//                        .splineToConstantHeading(new Vector2d(48.73, -48.92), Math.toRadians(-75.37))
//                        .splineToConstantHeading(new Vector2d(45.44, -13.19), Math.toRadians(90.00))
//                        .splineToConstantHeading(new Vector2d(57.53, -48.37), Math.toRadians(-90.00))
//                        .splineToConstantHeading(new Vector2d(56.24, -13.56), Math.toRadians(86.94))
//                        .splineToConstantHeading(new Vector2d(65.22, -49.10), Math.toRadians(-100.00))
//                        .splineToSplineHeading(new Pose2d(40.00, -52.50, Math.toRadians(-90.00)), Math.toRadians(-90.00))
//                        .splineToConstantHeading(new Vector2d(40.00, -61.00), Math.toRadians(-90.00))
//                        .setTangent(Math.toRadians(125))
//                        .splineToSplineHeading(new Pose2d(-3.11, -37.56, Math.toRadians(90.00)), Math.toRadians(136.65))
//                        .setTangent(Math.toRadians(-45))
//                        .splineToSplineHeading(new Pose2d(40.00, -52.50, Math.toRadians(-90.00)), Math.toRadians(-90.00))
//                        .splineToConstantHeading(new Vector2d(40.00, -61.00), Math.toRadians(-90.00))
//                        .setTangent(Math.toRadians(125))
//                        .splineToSplineHeading(new Pose2d(0.18, -37.19, Math.toRadians(90.00)), Math.toRadians(139.26))
//                        .setTangent(Math.toRadians(-45))
//                        .splineToSplineHeading(new Pose2d(40.00, -52.50, Math.toRadians(-90.00)), Math.toRadians(-90.00))
//                        .splineToConstantHeading(new Vector2d(40.00, -61.00), Math.toRadians(-90.00))
//                        .setTangent(Math.toRadians(125))
//                        .splineToSplineHeading(new Pose2d(3.30, -37.19, Math.toRadians(90.00)), Math.toRadians(90.00))
//                        .setTangent(Math.toRadians(-45))
//                        .splineToSplineHeading(new Pose2d(40.00, -52.50, Math.toRadians(-90.00)), Math.toRadians(-90.00))
//                        .splineToConstantHeading(new Vector2d(40.00, -61.00), Math.toRadians(-90.00))
//                        .setTangent(Math.toRadians(125))
//                        .splineToSplineHeading(new Pose2d(6.05, -37.19, Math.toRadians(90.00)), Math.toRadians(90.00))

                .build());






        //.turn(Math.toRadians(90)) save_fl

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}