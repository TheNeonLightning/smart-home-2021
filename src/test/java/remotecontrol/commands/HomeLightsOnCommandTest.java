package remotecontrol.commands;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.SensorsInteraction.CommandType;
import ru.sbt.mipt.oop.HomeProvider.HomeProvider;
import ru.sbt.mipt.oop.HomeProvider.JsonHomeProvider;
import ru.sbt.mipt.oop.RemoteControl.Command;
import ru.sbt.mipt.oop.RemoteControl.Commands.HomeLightsOnCommand;
import ru.sbt.mipt.oop.SmartHome.SmartHome;

public class HomeLightsOnCommandTest {

    HomeProvider homeProvider;
    SmartHome smartHome;
    TestHomeControl homeControl;

    @Before
    public void setHome() {
        homeProvider = new JsonHomeProvider("smart-home-1.js");
        smartHome = homeProvider.provideHome();
        homeControl = new TestHomeControl();
    }

    @Test
    public void executionTurnsOnHomeLightsTest() {
        Command command = new HomeLightsOnCommand(smartHome, homeControl);
        command.execute();

        Assert.assertEquals(homeControl.getType(), CommandType.LIGHT_ON);
    }
}
