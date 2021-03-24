import org.junit.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.EventProcessor.DoorEventProcessor;
import ru.sbt.mipt.oop.EventProcessor.HallDoorEventProcessor;
import ru.sbt.mipt.oop.EventProcessor.LightEventProcessor;
import ru.sbt.mipt.oop.EventResolver.EventResolver;
import ru.sbt.mipt.oop.EventResolver.SmartHomeEventResolver;
import ru.sbt.mipt.oop.HomeControl.HomeControl;
import ru.sbt.mipt.oop.HomeControl.HomeControlSimulator;
import ru.sbt.mipt.oop.HomeProvider.HomeProvider;
import ru.sbt.mipt.oop.HomeProvider.JsonHomeProvider;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.SmartHome.SmartHome;
import ru.sbt.mipt.oop.SmartHomeUtility;

import java.util.Arrays;


public class HallDoorEventProcessorTest {

    HomeProvider homeProvider;
    SmartHome smartHome;
    EventResolver eventResolver;
    HomeControl homeControl;

    public void setHome() {
        homeProvider = new JsonHomeProvider("smart-home-1.js");
        smartHome = homeProvider.provideHome();
        homeControl = new HomeControlSimulator();
        eventResolver = new SmartHomeEventResolver(
                Arrays.asList(
                        new LightEventProcessor(smartHome),
                        new DoorEventProcessor(smartHome),
                        new HallDoorEventProcessor(homeControl, smartHome))
        );
    }

    @Test
    public void processHallDoorClosedEventTest() {
        setHome();
        String hallDoorId = "4";

        setAllLightsOn();
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, hallDoorId);
        eventResolver.resolveEvent(event);
        checkAllLightsOff();
    }

    @Test
    public void processHallDoorOpenEventTest() {
        setHome();
        String hallDoorId = "4";

        setAllLightsOn();
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_OPEN, hallDoorId);
        eventResolver.resolveEvent(event);
        checkAllLightsOn();
    }

    private void setAllLightsOn() {
        for (int id = 1; id < 9; ++id) {
            String lightId = Integer.valueOf(id).toString();

            SensorEvent event = new SensorEvent(SensorEventType.LIGHT_ON, lightId);
            eventResolver.resolveEvent(event);
        }
    }

    private void checkAllLightsOn() {
        for (int id = 1; id < 9; ++id) {
            String lightId = Integer.valueOf(id).toString();
            Assert.assertTrue(SmartHomeUtility.findLight(smartHome, lightId).isOn());
        }
    }

    private void checkAllLightsOff() {
        for (int id = 1; id < 9; ++id) {
            String lightId = Integer.valueOf(id).toString();
            Assert.assertFalse(SmartHomeUtility.findLight(smartHome, lightId).isOn());
        }
    }
}
