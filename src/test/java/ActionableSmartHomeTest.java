import org.junit.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.HomeProvider.HomeProvider;
import ru.sbt.mipt.oop.HomeProvider.JsonHomeProvider;
import ru.sbt.mipt.oop.SmartHome.Door;
import ru.sbt.mipt.oop.SmartHome.Light;
import ru.sbt.mipt.oop.SmartHome.Room;
import ru.sbt.mipt.oop.SmartHome.SmartHome;
import ru.sbt.mipt.oop.SmartHomeUtility;

import java.util.concurrent.atomic.AtomicInteger;


public class ActionableSmartHomeTest {

    HomeProvider homeProvider;
    SmartHome smartHome;

    public void setHome() {
        homeProvider = new JsonHomeProvider("smart-home-1.js");
        smartHome = homeProvider.provideHome();
    }

    @Test
    public void ActionableLightsTest() {
        setHome();

        smartHome.execute((object) -> {
            if (object instanceof Light) {
                Light light = (Light) object;
                light.setOn(true);
            }
        });
        checkAllLightsOn();

        String lightId = "4";
        smartHome.execute((object) -> {
            if (object instanceof Light) {
                Light light = (Light) object;
                if (light.getId().equals(lightId)) {
                    light.setOn(false);
                }
            }
        });
        Assert.assertFalse(SmartHomeUtility.findLight(smartHome, lightId).isOn());
    }

    @Test
    public void ActionableDoorsTest() {
        setHome();

        smartHome.execute((object) -> {
            if (object instanceof Door) {
                Door door = (Door) object;
                door.setOpen(true);
            }
        });
        checkAllDoorsOpen();

        String hallDoorId = "4";
        smartHome.execute((object) -> {
            if (object instanceof Door) {
                Door door = (Door) object;
                if (door.getId().equals(hallDoorId)) {
                    door.setOpen(false);
                }
            }
        });
        Assert.assertFalse(SmartHomeUtility.findDoor(smartHome, hallDoorId).isOpen());
    }

    @Test
    public void ActionableRoomsTest() {
        setHome();

        smartHome.execute(object -> {
            if (object instanceof Room) {
                Room room = (Room) object;
                if (room.getName().equals("hall")) {
                    room.execute(localObject -> {
                        if (localObject instanceof Door) {
                            Door door = (Door) localObject;
                            door.setOpen(false);
                        }
                    });
                }
            }
        });

        String hallDoorId = "4";
        Assert.assertFalse(SmartHomeUtility.findDoor(smartHome, hallDoorId).isOpen());
    }

    @Test
    public void ActionableSmartHomeTest() {
        setHome();
        int homeObjectsNumber = 18;

        class LocalAction implements Action {
            public int counter = 0;

            @Override
            public void applyAction(Object object) {
                ++counter;
            }
        }

        LocalAction action = new LocalAction();
        smartHome.execute(action);
        Assert.assertEquals(homeObjectsNumber, action.counter);
    }

    private void checkAllDoorsOpen() {
        for (int id = 1; id < 5; ++id) {
            String doorId = Integer.valueOf(id).toString();
            Assert.assertTrue(SmartHomeUtility.findDoor(smartHome, doorId).isOpen());
        }
    }

    private void checkAllLightsOn() {
        for (int id = 1; id < 9; ++id) {
            String lightId = Integer.valueOf(id).toString();
            Assert.assertTrue(SmartHomeUtility.findLight(smartHome, lightId).isOn());
        }
    }
}
