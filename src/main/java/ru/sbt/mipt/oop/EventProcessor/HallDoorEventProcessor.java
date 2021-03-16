package ru.sbt.mipt.oop.EventProcessor;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.HomeControl.HomeControl;
import ru.sbt.mipt.oop.SmartHome.*;


public class HallDoorEventProcessor implements EventProcessor {

    private final SmartHome smartHome;
    private final HomeControl homeControl;

    public HallDoorEventProcessor(HomeControl homeControl, SmartHome smartHome) {
        this.homeControl = homeControl;
        this.smartHome = smartHome;
    }

    @Override
    public void processEvent(SensorEvent event) {
        // Even if the door is hall door it would get closed/opened by generic
        // DoorEventProcessor; Here we only have to chek if the door
        // being closed is in hall
        // and if it is we turn off all the lights;
        //
        // With this we don't have to check type off door in DoorEventProcessor:
        // all doors behave the same considering closing/opening.
        if (event.getType() == SensorEventType.DOOR_CLOSED) {
            handleDoorClosed(event.getObjectId());
        }
    }

    private void turnOffLights() {
        smartHome.execute((object) -> {
            if (object instanceof Light) {
                Light light = (Light) object;
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF,
                        light.getId());
                homeControl.sendCommand(command);
            }
        });
    }

    private void handleDoorClosed(String doorId) {
        smartHome.execute(object -> {
            if (object instanceof Room) {
                Room room = (Room) object;
                if (room.getName().equals("hall")) {
                    room.execute(localObject -> {
                        if (localObject instanceof Door) {
                            Door door = (Door) localObject;
                            if (door.getId().equals(doorId)) {
                                turnOffLights();
                                System.out.println("Turning off all the lights, " +
                                        "as the hall door was closed");
                            }
                        }
                    });
                }
            }
        });
    }
}
