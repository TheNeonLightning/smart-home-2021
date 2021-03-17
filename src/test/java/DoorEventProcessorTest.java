import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.sbt.mipt.oop.EventProcessor.DoorEventProcessor;
import ru.sbt.mipt.oop.EventProcessor.EventProcessor;
import ru.sbt.mipt.oop.HomeProvider.HomeProvider;
import ru.sbt.mipt.oop.HomeProvider.JsonHomeProvider;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.SmartHome.Door;
import ru.sbt.mipt.oop.SmartHome.SmartHome;
import ru.sbt.mipt.oop.SmartHomeUtility;


public class DoorEventProcessorTest {

    HomeProvider homeProvider;
    SmartHome smartHome;
    EventProcessor eventProcessor;

    public void setHome() {
        homeProvider = new JsonHomeProvider("smart-home-1.js");
        smartHome = homeProvider.provideHome();
        eventProcessor = new DoorEventProcessor(smartHome);
    }

    @Test
    public void processDoorEventTest() {
        setHome();

        setAllDoorsClosedAndCheck();
        setAllDoorsOpenAndCheck();
    }

    @Test
    public void processUnrelatedEventTest() {
        setHome();

        setAllDoorsClosedAndCheck();

        for (int id = 1; id < 9; ++id) {
            String lightId = Integer.valueOf(id).toString();

            SensorEvent event = new SensorEvent(SensorEventType.LIGHT_OFF, lightId);
            eventProcessor.processEvent(event);
            checkAllDoorsClosed();
        }

        for (int id = 1; id < 9; ++id) {
            String lightId = Integer.valueOf(id).toString();

            SensorEvent event = new SensorEvent(SensorEventType.LIGHT_ON, lightId);
            eventProcessor.processEvent(event);
            checkAllDoorsClosed();
        }
    }

    private void setAllDoorsOpenAndCheck() {
        for (int id = 1; id < 5; ++id) {
            String doorId = Integer.valueOf(id).toString();

            SensorEvent event = new SensorEvent(SensorEventType.DOOR_OPEN, doorId);
            eventProcessor.processEvent(event);
            Assert.assertTrue(SmartHomeUtility.findDoor(smartHome, doorId).isOpen());
        }
    }

    private void setAllDoorsClosedAndCheck() {
        for (int id = 1; id < 5; ++id) {
            String doorId = Integer.valueOf(id).toString();

            SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, doorId);
            eventProcessor.processEvent(event);
            Assert.assertFalse(SmartHomeUtility.findDoor(smartHome, doorId).isOpen());
        }
    }

    private void checkAllDoorsClosed() {
        for (int id = 1; id < 5; ++id) {
            String doorId = Integer.valueOf(id).toString();
            Assert.assertFalse(SmartHomeUtility.findDoor(smartHome, doorId).isOpen());
        }
    }
}
