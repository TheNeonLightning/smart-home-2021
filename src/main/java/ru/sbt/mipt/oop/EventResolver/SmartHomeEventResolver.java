package ru.sbt.mipt.oop.EventResolver;

import ru.sbt.mipt.oop.EventProcessor.DoorEventProcessor;
import ru.sbt.mipt.oop.EventProcessor.EventProcessor;
import ru.sbt.mipt.oop.EventProcessor.HallDoorEventProcessor;
import ru.sbt.mipt.oop.EventProcessor.LightEventProcessor;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.SmartHomeUtility;

// contains event processors for different types of events
// separates logic of choosing event processor to resolve received event
public class SmartHomeEventResolver implements EventResolver {

    private final SmartHome smartHome;

    private final EventProcessor lightEventProcessor;
    private final EventProcessor doorEventProcessor;
    private final EventProcessor hallDoorEventProcessor;

    public SmartHomeEventResolver(
            SmartHome smartHome,
            LightEventProcessor lightEventProcessor,
            DoorEventProcessor doorEventProcessor,
            HallDoorEventProcessor hallDoorEventProcessor) {
        this.smartHome = smartHome;

        this.lightEventProcessor = lightEventProcessor;
        this.doorEventProcessor = doorEventProcessor;
        this.hallDoorEventProcessor = hallDoorEventProcessor;
    }

    @Override
    public void resolveEvent(SensorEvent event) {

        switch (event.getType()) {
            case LIGHT_OFF, LIGHT_ON ->
                    lightEventProcessor.processEvent(event);

            case DOOR_CLOSED, DOOR_OPEN -> {
                Room room = SmartHomeUtility.findRoom(smartHome, event.getObjectId());
                if (room.getName().equals("hall")) {
                    hallDoorEventProcessor.processEvent(event);
                } else {
                    doorEventProcessor.processEvent(event);
                }
            }
        }
    }
}
