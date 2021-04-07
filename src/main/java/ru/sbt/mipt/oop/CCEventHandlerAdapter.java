package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.EventProcessor.EventProcessor;

public class CCEventHandlerAdapter implements com.coolcompany.smarthome.events.EventHandler {

    EventProcessor eventProcessor;

    public CCEventHandlerAdapter(EventProcessor eventProcessor) {
        this.eventProcessor = eventProcessor;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        eventProcessor.processEvent(adaptEvent(event));
    }

    private SensorEvent adaptEvent(CCSensorEvent event) {
        return new SensorEvent(adaptEventType(event.getEventType()), event.getObjectId());
    }

    /**
     * Available event types in library v3.7.1:
     *    "LightIsOn", "LightIsOff", "DoorIsOpen", "DoorIsClosed",
     *    "DoorIsLocked", "DoorIsUnlocked"
     */
    private SensorEventType adaptEventType(String eventType) {
        return switch (eventType) {
            case ("LightIsOn") -> SensorEventType.LIGHT_ON;
            case ("LightIsOff") -> SensorEventType.LIGHT_OFF;
            case ("DoorIsOpen") -> SensorEventType.DOOR_OPEN;
            case ("DoorIsClosed") -> SensorEventType.DOOR_CLOSED;
            case ("DoorIsLocked") -> SensorEventType.DOOR_LOCKED;
            case ("DoorIsUnlocked") -> SensorEventType.DOOR_UNLOCKED;
            default -> throw new IllegalStateException("Unexpected value: " + eventType);
        };
    }
}
