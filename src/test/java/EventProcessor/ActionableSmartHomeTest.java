import org.junit.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.SmartHome.Action;
import ru.sbt.mipt.oop.HomeProvider.HomeProvider;
import ru.sbt.mipt.oop.HomeProvider.JsonHomeProvider;
import ru.sbt.mipt.oop.SmartHome.Door;
import ru.sbt.mipt.oop.SmartHome.Light;
import ru.sbt.mipt.oop.SmartHome.Room;
import ru.sbt.mipt.oop.SmartHome.SmartHome;


public class ActionableSmartHomeTest {

    HomeProvider homeProvider;
    SmartHome smartHome;

    @Test
    public void actionableLightsTest() {
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

        LightChecker.checkLight(smartHome, lightId, false);
    }

    @Test
    public void actionableDoorsTest() {
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

        DoorChecker.checkDoor(smartHome, hallDoorId, false);
    }

    @Test
    public void actionableRoomsTest() {
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

        DoorChecker.checkDoor(smartHome, hallDoorId, false);
    }

    @Test
    public void actionableSmartHomeTest() {
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

    private void setHome() {
        homeProvider = new JsonHomeProvider("smart-home-1.js");
        smartHome = homeProvider.provideHome();
    }

    private void checkAllDoorsOpen() {
        for (int id = 1; id < 5; ++id) {
            String doorId = Integer.valueOf(id).toString();
            DoorChecker.checkDoor(smartHome, doorId, true);
        }
    }

    private void checkAllLightsOn() {
        for (int id = 1; id < 9; ++id) {
            String lightId = Integer.valueOf(id).toString();
            LightChecker.checkLight(smartHome, lightId, true);
        }
    }
}
