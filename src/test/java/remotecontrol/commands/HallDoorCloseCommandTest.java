package remotecontrol.commands;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.SensorsInteraction.CommandType;
import ru.sbt.mipt.oop.HomeProvider.HomeProvider;
import ru.sbt.mipt.oop.HomeProvider.JsonHomeProvider;
import ru.sbt.mipt.oop.RemoteControl.Command;
import ru.sbt.mipt.oop.RemoteControl.Commands.HallDoorCloseCommand;
import ru.sbt.mipt.oop.SmartHome.SmartHome;


public class HallDoorCloseCommandTest {

    HomeProvider homeProvider;
    SmartHome smartHome;
    TestHomeControl homeControl;

    @Before
    public void setHome() {
        homeProvider = new JsonHomeProvider("smart-home-1.json");
        smartHome = homeProvider.provideHome();
        homeControl = new TestHomeControl();
    }

    @Test
    public void executionClosesHallDoorTest() {
        Command command = new HallDoorCloseCommand(smartHome, homeControl);
        command.execute();

        Assert.assertEquals(homeControl.getType(), CommandType.DOOR_CLOSE);
    }
}
