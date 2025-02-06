package com.example.meepmeeptesting;


import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
        public static void main(String[] args) {
                MeepMeep meepMeep = new MeepMeep(600);

                RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                        // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width+-

                        .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                        .build();

                myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(0, 56, Math.toRadians(270)))
                        .lineToY(32) //put on chamber
                        .lineToY(44) //back away
                        .turn(Math.toRadians(-90)) //turn to robot right
                        .lineToX(-34) //go away from submersible
                        .turn(Math.toRadians(90)) // go past the first blue brick
                        .lineToY(10) //go far
                        .turn(Math.toRadians(-90)) //turn to robot right
                        .lineToX(-48) //line up with 3brick1
                        .turn(Math.toRadians((-90))) //turn towards 3brick1
                        .lineToY(60) //push 3brick2 into o-zone
                        .lineToY(10) //back up
                        .turn(Math.toRadians(90)) //turn to go X
                        .lineToX(-55) //line up with 3brick2
                        .turn(Math.toRadians((-90))) //turn towards 3brick2
                        .lineToY(60) //push 3brick2 into o-zone
                        .lineToY(10) //back up
                        .turn(Math.toRadians(90)) //turn to go X
                        .lineToX(-60) //line up with 3brick3
                        .turn(Math.toRadians((-90))) //turn towards 3brick3
                        .lineToY(60) //push 3brick3 into o-zone






                        //.turn(Math.toRadians(90)) save_fl

                        .build());

                meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                        .setDarkMode(true)
                        .setBackgroundAlpha(0.95f)
                        .addEntity(myBot)
                        .start();
        }
}