package ru.sbt.mipt.oop.EventProcessor;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.SmartHome.Door;
import ru.sbt.mipt.oop.SmartHome.Room;
import ru.sbt.mipt.oop.SmartHome.SmartHome;


public class DoorEventProcessor implements EventProcessor {

    private final SmartHome smartHome;

    public DoorEventProcessor(SmartHome smartHome) {
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
        Door door = findDoor(doorId);
        door.setOpen(true);
        System.out.println("Door " + door.getId() + " was opened.");
    }

    private void handleDoorClosed(String doorId)  {
        Door door = findDoor(doorId);
        door.setOpen(false);
        System.out.println("Door " + door.getId() + " was closed.");
    }

    private Door findDoor(String doorId) {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(doorId)) {
                    return door;
                }
            }
        }
        throw new RuntimeException("Door ID not found");
    }
}
