package com.example.meepmeeptesting;


import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
        public static double specimenIntakeWaitTime = 1.0;
        public static double specimenOuttakeWaitTime = 1.5;
        public static double submersibleIntakeWaitTime = 2.0;

        public static void main(String[] args) {
                MeepMeep meepMeep = new MeepMeep(600);

                RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                        // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width+-

                        .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                        .build();

                myBot.runAction(myBot.getDrive ().actionBuilder(new Pose2d(-10, 60, -Math.PI / 2))
                        .waitSeconds(1)
                        .strafeTo(new Vector2d(0, 32))
                        .waitSeconds(specimenOuttakeWaitTime)
                        //.setTangent(0)
                        .splineToSplineHeading(new Pose2d(-1.28, 37.76, Math.toRadians(-90)), Math.toRadians(90))
//                        .setTangent(90)
//                        .splineToSplineHeading(new Pose2d(-32, 32.80, Math.toRadians(90)), Math.toRadians(90))
//                        .setTangent(90)
//                        .splineToConstantHeading(new Vector2d(-40.96, 14), Math.toRadians(90))
                                //(new Pose2d(-40.96, 14, Math.toRadians(124.56)), Math.toRadians(124.56))
                        .splineTo(new Vector2d(-43.04, 52.80), Math.toRadians(90))
//                        .setTangent(90)
//                        .splineToLinearHeading(new Pose2d(-32.16, 34.56, Math.toRadians(90.00)), Math.toRadians(177.33))
//                        .strafeTo(new Vector2d(-43, 12))
//                        //.splineToLinearHeading(new Pose2d(-42.72, 12.32, Math.toRadians(90.00)), Math.toRadians(239.72))
//                        .strafeTo(new Vector2d(-43, 52))
//                        .setTangent(135)
//                        .splineTo(new Vector2d(-42.88, 51.52), Math.toRadians(90))


//                        .splineTo(new Vector2d(-33.28, 36.00), Math.toRadians(184.99))
//                        .splineToSplineHeading(new Pose2d(-42.24, 7.52, Math.toRadians(90.00)), Math.toRadians(180.00))
//                        .splineTo(new Vector2d(-29.76, 37.12), Math.toRadians(184.99))
//                        .splineToSplineHeading(new Pose2d(-40.64, 13.92, Math.toRadians(86.27)), Math.toRadians(86.27))
//                        .splineTo(new Vector2d(-43.20, 51.20), Math.toRadians(93.59))
//                        .splineToSplineHeading(new Pose2d(-33.12, 37.60, Math.toRadians(180.00)), Math.toRadians(230.00))
//                        .splineToSplineHeading(new Pose2d(-41.60, 11.20, Math.toRadians(86.27)), Math.toRadians(86.27))
//                        .splineTo(new Vector2d(-43.20, 51.20), Math.toRadians(93.59))

//                        .splineTo(new Vector2d(-14.56, 41.12), Math.toRadians(198.94))
//                        .splineTo(new Vector2d(-37.76, 26.88), Math.toRadians(236.57))
//                        .splineToSplineHeading(new Pose2d(-37.44, 10.24, Math.toRadians(180.00)), Math.toRadians(180.00))
//                        .splineToLinearHeading(new Pose2d(-43.68, 50.56, Math.toRadians(180.00)), Math.toRadians(93.59))

//                        .strafeTo(new Vector2d(-30, 39)) //tuck in arm while doing this
//                        .splineTo(new Vector2d(-32.96, 32.80), Math.toRadians(237.77))
//                        .splineToSplineHeading(new Pose2d(-42.56, 10.72, Math.toRadians(90.00)), Math.toRadians(90.00))
//                        .splineTo(new Vector2d(-45.92, 51.20), Math.toRadians(90.00))

                        .waitSeconds(specimenIntakeWaitTime)
//                        .splineToLinearHeading(new Pose2d(-53, -53, Math.toRadians(45)), Math.PI)
//                        .waitSeconds(specimenOuttakeWaitTime)
//                        .splineToLinearHeading(new Pose2d(-53, -51, Math.toRadians(120)), Math.PI)
//                        .waitSeconds(specimenOuttakeWaitTime)
//                        .splineToLinearHeading(new Pose2d(-53, -53, Math.toRadians(45)), Math.PI)
//                        .waitSeconds(specimenOuttakeWaitTime)
//                        .splineToLinearHeading(new Pose2d(-24, -10, Math.PI), Math.toRadians(0))
//                        .waitSeconds(submersibleIntakeWaitTime)
                        .build());






                        //.turn(Math.toRadians(90)) save_fl

                meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                        .setDarkMode(true)
                        .setBackgroundAlpha(0.95f)
                        .addEntity(myBot)
                        .start();
        }
}