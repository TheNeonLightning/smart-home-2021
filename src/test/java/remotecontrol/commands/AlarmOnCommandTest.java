package remotecontrol.commands;

import org.junit.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.RemoteControl.Command;
import ru.sbt.mipt.oop.RemoteControl.Commands.AlarmOnCommand;
import ru.sbt.mipt.oop.Signalization.Signalization;
import ru.sbt.mipt.oop.Signalization.StateType;

public class AlarmOnCommandTest {

    @Test
    public void executionTurnsOnAlarmTest() {
        int code = 1234;
        Signalization signalization = new Signalization(code);

        Command command = new AlarmOnCommand(signalization);
        command.execute();

        Assert.assertEquals(StateType.ALARM, signalization.stateType());
    }
}
