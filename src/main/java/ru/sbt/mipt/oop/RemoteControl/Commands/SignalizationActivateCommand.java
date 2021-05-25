package ru.sbt.mipt.oop.RemoteControl.Commands;

import ru.sbt.mipt.oop.RemoteControl.Command;
import ru.sbt.mipt.oop.Signalization.Signalization;

public class SignalizationActivateCommand implements Command {

    private final Signalization signalization;
    private final int code = 1234;
        // TODO not sure what to do with the code for signalization

    public SignalizationActivateCommand(Signalization signalization) {
        this.signalization = signalization;
    }

    @Override
    public void execute() {
        signalization.activate(code);
    }
}
