import org.junit.Test;
import ru.sbt.mipt.oop.EventProcessor.EventProcessor;
import ru.sbt.mipt.oop.EventProcessor.LightEventProcessor;
import ru.sbt.mipt.oop.HomeProvider.HomeProvider;
import ru.sbt.mipt.oop.HomeProvider.JsonHomeProvider;
import ru.sbt.mipt.oop.SensorsInteraction.SensorEvent;
import ru.sbt.mipt.oop.SensorsInteraction.SensorEventType;
import ru.sbt.mipt.oop.SmartHome.SmartHome;

public class LightEventProcessorTest {

    HomeProvider homeProvider;
    SmartHome smartHome;
    EventProcessor eventProcessor;

    public void setHome() {
        homeProvider = new JsonHomeProvider("smart-home-1.js");
        smartHome = homeProvider.provideHome();
        eventProcessor = new LightEventProcessor(smartHome);
    }

    @Test
    public void processLightEventTest() {
        setHome();

        setAllLightsOffAndCheck();
        setAllLightsOnAndCheck();
    }

    @Test
    public void processUnrelatedEventTest() {
        setHome();

        setAllLightsOffAndCheck();

        for (int id = 1; id < 5; ++id) {
            String doorId = Integer.valueOf(id).toString();

            SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, doorId);
            eventProcessor.processEvent(event);
            checkAllLightsOff();
        }

        for (int id = 1; id < 5; ++id) {
            String doorId = Integer.valueOf(id).toString();

            SensorEvent event = new SensorEvent(SensorEventType.DOOR_OPEN, doorId);
            eventProcessor.processEvent(event);
            checkAllLightsOff();
        }
    }

    private void setAllLightsOffAndCheck() {
        for (int id = 1; id < 9; ++id) {
            String lightId = Integer.valueOf(id).toString();

            SensorEvent event = new SensorEvent(SensorEventType.LIGHT_OFF, lightId);
            eventProcessor.processEvent(event);
            LightChecker.checkLight(smartHome, lightId, false);
        }
    }

    private void setAllLightsOnAndCheck() {
        for (int id = 1; id < 9; ++id) {
            String lightId = Integer.valueOf(id).toString();

            SensorEvent event = new SensorEvent(SensorEventType.LIGHT_ON, lightId);
            eventProcessor.processEvent(event);
            LightChecker.checkLight(smartHome, lightId, true);
        }
    }

    private void checkAllLightsOff() {
        for (int id = 1; id < 9; ++id) {
            String lightId = Integer.valueOf(id).toString();
            LightChecker.checkLight(smartHome, lightId, false);
        }
    }
}
