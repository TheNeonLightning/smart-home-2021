package ru.sbt.mipt.oop.EventProcessor;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.HomeControl.HomeControl;

import static ru.sbt.mipt.oop.SensorEventType.*;


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
        if (event.getType().equals(DOOR_CLOSED)) {
            handleDoorClosed(event.getObjectId());
        }
    }


    private void handleDoorClosed(String doorId)  {
        Room room = SmartHomeUtility.findRoom(smartHome, doorId);
        if (room.getName().equals("hall")) {
            for (Room homeRoom : smartHome.getRooms()) {
                for (Light light : homeRoom.getLights()) {
                    light.setOn(false);
                    SensorCommand command =
                            new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                    homeControl.sendCommand(command);
                }
            }
            System.out.println("Turning off all the lights, hall door was closed");
        }
    }
}
