import org.junit.Assert;
import ru.sbt.mipt.oop.SmartHome.Light;
import ru.sbt.mipt.oop.SmartHome.SmartHome;

public class LightChecker {

    public static void checkLight(SmartHome smartHome, String lightId, boolean isOnExpected) {
        smartHome.execute((object) -> {
            if (object instanceof Light) {
                Light light = (Light) object;
                if (light.getId().equals(lightId)) {
                    Assert.assertEquals(isOnExpected, light.isOn());
                }
            }
        });
    }
}
