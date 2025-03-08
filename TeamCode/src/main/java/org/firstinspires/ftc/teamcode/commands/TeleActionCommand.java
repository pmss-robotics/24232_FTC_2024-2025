/*package org.firstinspires.ftc.teamcode.commands;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;

import java.util.Set;

public class TeleActionCommand extends CommandBase {
    private final Action action;
    private final DriveSubsystem drive;
    private final Set<Subsystem> requirements;
    private boolean finished = false;

    public TeleActionCommand(Action action, DriveSubsystem drive, Set<Subsystem> requirements) {
        this.action = action;
        this.drive = drive;
        this.requirements = requirements;
        addRequirements(drive);
    }


    @Override
    public Set<Subsystem> getRequirements() {
        return requirements;
    }

    @Override
    public void execute() {
        TelemetryPacket packet = new TelemetryPacket();
        action.preview(packet.fieldOverlay());
        finished = !action.run(packet);
        FtcDashboard.getInstance().sendTelemetryPacket(packet);
    }

    @Override
    public boolean isFinished() {
        return finished;
    }
}*/