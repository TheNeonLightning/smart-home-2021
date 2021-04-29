package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.EventProcessor.EventProcessor;

import java.util.Map;

public class CCEventHandlerAdapter implements com.coolcompany.smarthome.events.EventHandler {

    private final EventProcessor eventProcessor;
    private final Map<String, SensorEventType> sensorEventTypeMap;

    public CCEventHandlerAdapter(EventProcessor eventProcessor,
                                 Map<String, SensorEventType> sensorEventTypeMap) {
        this.eventProcessor = eventProcessor;
        this.sensorEventTypeMap = sensorEventTypeMap;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        eventProcessor.processEvent(adaptEvent(event));
    }

    private SensorEvent adaptEvent(CCSensorEvent event) {
        return new SensorEvent(
                sensorEventTypeMap.get(event.getEventType()),
                event.getObjectId()
        );
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
