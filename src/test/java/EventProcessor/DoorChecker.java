import org.junit.Assert;
import ru.sbt.mipt.oop.SmartHome.Door;
import ru.sbt.mipt.oop.SmartHome.SmartHome;

public class DoorChecker {

    public static void checkDoor(SmartHome smartHome, String doorId, boolean isOpenExpected) {
        smartHome.execute((object) -> {
            if (object instanceof Door) {
                Door door = (Door) object;
                if (door.getId().equals(doorId)) {
                    Assert.assertEquals(isOpenExpected, door.isOpen());
                }
            }
        });
    }
}
