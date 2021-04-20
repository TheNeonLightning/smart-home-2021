package remotecontrol;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.RemoteControl.Command;
import ru.sbt.mipt.oop.RemoteControl.RemoteControlImpl;

class TestCommand implements Command {

    private boolean isExecuted = false;

    @Override
    public void execute() {
        isExecuted = true;
    }

    public boolean getIsExecuted() {
        return isExecuted;
    }
}

public class RemoteControlImplTest {

    private RemoteControlImpl remoteControl;

    @Before
    public void setUpRemoteControlImpl() {
        remoteControl = new RemoteControlImpl();
    }

    @Test
    public void commandExecutedTest() {

        TestCommand testCommand = new TestCommand();

        String buttonCode = "A";
        String rcId = "1";

        remoteControl.setCommand(buttonCode, testCommand);

        remoteControl.onButtonPressed(buttonCode, rcId);

        Assert.assertTrue(testCommand.getIsExecuted());
    }

    @Test
    public void nonExistingButtonCommandNotRegisteredTest() {
        TestCommand testCommand = new TestCommand();

        String buttonCode = "E";
        String rcId = "1";

        remoteControl.setCommand(buttonCode, testCommand);

        remoteControl.onButtonPressed(buttonCode, rcId);

        Assert.assertFalse(testCommand.getIsExecuted());
    }

    @Test
    public void oneCommandPerButtonTest() {
        TestCommand previousCommand = new TestCommand();
        TestCommand actualCommand = new TestCommand();

        String buttonCode = "A";
        String rcId = "1";

        remoteControl.setCommand(buttonCode, previousCommand);
        remoteControl.setCommand(buttonCode, actualCommand);

        remoteControl.onButtonPressed(buttonCode, rcId);

        Assert.assertFalse(previousCommand.getIsExecuted());
        Assert.assertTrue(actualCommand.getIsExecuted());
    }
}
