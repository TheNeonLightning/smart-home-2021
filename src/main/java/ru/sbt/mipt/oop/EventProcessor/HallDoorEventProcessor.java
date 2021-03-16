package ru.sbt.mipt.oop.EventProcessor;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.HomeControl.HomeControl;


public class HallDoorEventProcessor implements EventProcessor {

    private final SmartHome smartHome;
    private final HomeControl homeControl;

    public HallDoorEventProcessor(HomeControl homeControl, SmartHome smartHome) {
        this.homeControl = homeControl;
        this.smartHome = smartHome;
    }

    @Override
    public void processEvent(SensorEvent event) {
        switch (event.getType()) {
            case DOOR_OPEN -> handleDoorOpen(event.getObjectId());
            case DOOR_CLOSED -> handleDoorClosed(event.getObjectId());
        }
    }

    private void handleDoorOpen(String doorId) {
        Door door = SmartHomeUtility.findDoor(smartHome, doorId);
        door.setOpen(true);
        System.out.println("Hall door " + door.getId() + " was opened.");
    }

    private void handleDoorClosed(String doorId)  {
        Door door = SmartHomeUtility.findDoor(smartHome, doorId);
        door.setOpen(false);
        System.out.println("Hall door " + door.getId() + " was closed.");

        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                homeControl.sendCommand(command);
            }
        }
    }
}
