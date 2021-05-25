package ru.sbt.mipt.oop.EventProcessor;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.SmartHome.*;


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
        smartHome.execute((object) -> {
            if (object instanceof Door) {
                Door door = (Door) object;
                if (door.getId().equals(doorId)) {
                    door.setOpen(true);
                    System.out.println("Door " + door.getId() + " was opened.");
                }
            }
        });
    }

    private void handleDoorClosed(String doorId)  {
        smartHome.execute((object) -> {
            if (object instanceof Door) {
                Door door = (Door) object;
                if (door.getId().equals(doorId)) {
                    door.setOpen(false);
                    System.out.println("Door " + door.getId() + " was closed.");
                }
            }
        });
    }
}
