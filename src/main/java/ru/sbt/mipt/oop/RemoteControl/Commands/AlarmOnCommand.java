package ru.sbt.mipt.oop.RemoteControl.Commands;

import ru.sbt.mipt.oop.RemoteControl.Command;
import ru.sbt.mipt.oop.Signalization.Signalization;

public class AlarmOnCommand implements Command {

    private final Signalization signalization;

    public AlarmOnCommand(Signalization signalization) {
        this.signalization = signalization;
    }

    @Override
    public void execute() {
        signalization.alarm();
    }
}
