//package com.example.meepmeeptesting;
//
//
//import com.acmerobotics.roadrunner.Pose2d;
//import com.acmerobotics.roadrunner.Vector2d;
//import com.noahbres.meepmeep.MeepMeep;
//import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
//import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
//
//public class MeepMeepTesting {
//    public static double specimenIntakeWaitTime = 1.0;
//    public static double specimenOuttakeWaitTime = 0.5;
//    public static double submersibleIntakeWaitTime = 2.0;
//
//    public static void main(String[] args) {
//        MeepMeep meepMeep = new MeepMeep(600);
//
//        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
//                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width+-
//
//                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
//                .build();
//
//        myBot.runAction(myBot.getDrive ().actionBuilder(new Pose2d(12, -63, Math.PI / 2))
//                .waitSeconds(0.5)
//
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
//
////                        .splineToConstantHeading(new Vector2d(8.24, -37.50), Math.toRadians(112.78))
////                        .setTangent(Math.toRadians(-30))
////                        .splineToConstantHeading(new Vector2d(35.54, -26.75), Math.toRadians(90.00))
////                        .splineToConstantHeading(new Vector2d(38.11, -13.37), Math.toRadians(90.00))
////                        .splineToConstantHeading(new Vector2d(48.73, -48.92), Math.toRadians(-75.37))
////                        .splineToConstantHeading(new Vector2d(45.44, -13.19), Math.toRadians(90.00))
////                        .splineToConstantHeading(new Vector2d(57.53, -48.37), Math.toRadians(-90.00))
////                        .splineToConstantHeading(new Vector2d(56.24, -13.56), Math.toRadians(86.94))
////                        .splineToConstantHeading(new Vector2d(65.22, -49.10), Math.toRadians(-100.00))
////                        .splineToSplineHeading(new Pose2d(40.00, -52.50, Math.toRadians(-90.00)), Math.toRadians(180.00))
////                        .splineToConstantHeading(new Vector2d(40.00, -61.00), Math.toRadians(-90.00))
////                        .setTangent(Math.toRadians(125))
////                        .splineToSplineHeading(new Pose2d(-2.56, -37.50, Math.toRadians(90.00)), Math.toRadians(136.65))
////                        .setTangent(Math.toRadians(-45))
////                        .splineToSplineHeading(new Pose2d(40.00, -52.50, Math.toRadians(-90.00)), Math.toRadians(-90.00))
////                        .splineToConstantHeading(new Vector2d(40.00, -61.00), Math.toRadians(-90.00))
////                        .setTangent(Math.toRadians(125))
////                        .splineToSplineHeading(new Pose2d(0.73, -37.50, Math.toRadians(90.00)), Math.toRadians(139.26))
////                        .setTangent(Math.toRadians(-45))
////                        .splineToSplineHeading(new Pose2d(40.00, -52.50, Math.toRadians(-90.00)), Math.toRadians(-90.00))
////                        .splineToConstantHeading(new Vector2d(40.00, -61.00), Math.toRadians(-90.00))
////                        .setTangent(Math.toRadians(125))
////                        .splineToSplineHeading(new Pose2d(3.48, -37.50, Math.toRadians(90.00)), Math.toRadians(90.00))
////                        .setTangent(Math.toRadians(-45))
////                        .splineToSplineHeading(new Pose2d(40.00, -52.50, Math.toRadians(-90.00)), Math.toRadians(-90.00))
////                        .splineToConstantHeading(new Vector2d(40.00, -61.00), Math.toRadians(-90.00))
////                        .setTangent(Math.toRadians(125))
////                        .splineToSplineHeading(new Pose2d(6.05, -37.50, Math.toRadians(90.00)), Math.toRadians(90.00))
//
////                        .splineToConstantHeading(new Vector2d(8.24, -40.67), Math.toRadians(112.78))
////                        .setTangent(Math.toRadians(-30))
////                        .splineToConstantHeading(new Vector2d(35.54, -26.75), Math.toRadians(90.00))
////                        .splineToConstantHeading(new Vector2d(38.11, -13.37), Math.toRadians(90.00))
////                        .splineToConstantHeading(new Vector2d(48.73, -48.92), Math.toRadians(-75.37))
////                        .splineToConstantHeading(new Vector2d(45.44, -13.19), Math.toRadians(90.00))
////                        .splineToConstantHeading(new Vector2d(57.53, -48.37), Math.toRadians(-90.00))
////                        .splineToConstantHeading(new Vector2d(56.24, -13.56), Math.toRadians(86.94))
////                        .splineToConstantHeading(new Vector2d(65.22, -49.10), Math.toRadians(-100.00))
////                        .splineToSplineHeading(new Pose2d(40.00, -52.50, Math.toRadians(-90.00)), Math.toRadians(180.00))
////                        .splineToConstantHeading(new Vector2d(40.00, -61.00), Math.toRadians(-90.00))
////                        .setTangent(Math.toRadians(125))
////                        .splineToSplineHeading(new Pose2d(-3.11, -37.56, Math.toRadians(90.00)), Math.toRadians(136.65))
////                        .setTangent(Math.toRadians(-45))
////                        .splineToSplineHeading(new Pose2d(40.00, -52.50, Math.toRadians(-90.00)), Math.toRadians(-90.00))
////                        .splineToConstantHeading(new Vector2d(40.00, -61.00), Math.toRadians(-90.00))
////                        .setTangent(Math.toRadians(125))
////                        .splineToSplineHeading(new Pose2d(0.18, -37.19, Math.toRadians(90.00)), Math.toRadians(139.26))
////                        .setTangent(Math.toRadians(-45))
////                        .splineToSplineHeading(new Pose2d(40.00, -52.50, Math.toRadians(-90.00)), Math.toRadians(-90.00))
////                        .splineToConstantHeading(new Vector2d(40.00, -61.00), Math.toRadians(-90.00))
////                        .setTangent(Math.toRadians(125))
////                        .splineToSplineHeading(new Pose2d(3.30, -37.19, Math.toRadians(90.00)), Math.toRadians(90.00))
////                        .setTangent(Math.toRadians(-45))
////                        .splineToSplineHeading(new Pose2d(40.00, -52.50, Math.toRadians(-90.00)), Math.toRadians(-90.00))
////                        .splineToConstantHeading(new Vector2d(40.00, -61.00), Math.toRadians(-90.00))
////                        .setTangent(Math.toRadians(125))
////                        .splineToSplineHeading(new Pose2d(6.05, -37.19, Math.toRadians(90.00)), Math.toRadians(90.00))
//
////                        .splineToConstantHeading(new Vector2d(8.24, -40.67), Math.toRadians(112.78))
////                        .setTangent(Math.toRadians(-30))
////                        .splineToConstantHeading(new Vector2d(35.54, -26.75), Math.toRadians(90.00))
////                        .splineToConstantHeading(new Vector2d(38.11, -13.37), Math.toRadians(90.00))
////                        .splineToConstantHeading(new Vector2d(48.73, -48.92), Math.toRadians(-75.37))
////                        .splineToConstantHeading(new Vector2d(45.44, -13.19), Math.toRadians(90.00))
////                        .splineToConstantHeading(new Vector2d(57.53, -48.37), Math.toRadians(-90.00))
////                        .splineToConstantHeading(new Vector2d(56.24, -13.56), Math.toRadians(86.94))
////                        .splineToConstantHeading(new Vector2d(65.22, -49.10), Math.toRadians(-100.00))
////                        .splineToSplineHeading(new Pose2d(40.00, -52.50, Math.toRadians(-90.00)), Math.toRadians(-90.00))
////                        .splineToConstantHeading(new Vector2d(40.00, -61.00), Math.toRadians(-90.00))
////                        .setTangent(Math.toRadians(125))
////                        .splineToSplineHeading(new Pose2d(-3.11, -37.56, Math.toRadians(90.00)), Math.toRadians(136.65))
////                        .setTangent(Math.toRadians(-45))
////                        .splineToSplineHeading(new Pose2d(40.00, -52.50, Math.toRadians(-90.00)), Math.toRadians(-90.00))
////                        .splineToConstantHeading(new Vector2d(40.00, -61.00), Math.toRadians(-90.00))
////                        .setTangent(Math.toRadians(125))
////                        .splineToSplineHeading(new Pose2d(0.18, -37.19, Math.toRadians(90.00)), Math.toRadians(139.26))
////                        .setTangent(Math.toRadians(-45))
////                        .splineToSplineHeading(new Pose2d(40.00, -52.50, Math.toRadians(-90.00)), Math.toRadians(-90.00))
////                        .splineToConstantHeading(new Vector2d(40.00, -61.00), Math.toRadians(-90.00))
////                        .setTangent(Math.toRadians(125))
////                        .splineToSplineHeading(new Pose2d(3.30, -37.19, Math.toRadians(90.00)), Math.toRadians(90.00))
////                        .setTangent(Math.toRadians(-45))
////                        .splineToSplineHeading(new Pose2d(40.00, -52.50, Math.toRadians(-90.00)), Math.toRadians(-90.00))
////                        .splineToConstantHeading(new Vector2d(40.00, -61.00), Math.toRadians(-90.00))
////                        .setTangent(Math.toRadians(125))
////                        .splineToSplineHeading(new Pose2d(6.05, -37.19, Math.toRadians(90.00)), Math.toRadians(90.00))
//
//                .build());
//
//
//
//
//
//
//        //.turn(Math.toRadians(90)) save_fl
//
//        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
//                .setDarkMode(true)
//                .setBackgroundAlpha(0.95f)
//                .addEntity(myBot)
//                .start();
//    }
//}