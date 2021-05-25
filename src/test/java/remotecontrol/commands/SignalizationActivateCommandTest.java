package remotecontrol.commands;

import org.junit.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.RemoteControl.Command;
import ru.sbt.mipt.oop.RemoteControl.Commands.SignalizationActivateCommand;
import ru.sbt.mipt.oop.Signalization.Signalization;

public class SignalizationActivateCommandTest {

    @Test
    public void executionTurnsOnAlarmTest() {
        int code = 1234;
        Signalization signalization = new Signalization(code);

        Command command = new SignalizationActivateCommand(signalization);
        command.execute();

        Assert.assertTrue(signalization.isActivated());
    }
}
