package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.EventProcessor.EventProcessor;

import java.util.Map;

public class CCEventHandlerAdapter implements com.coolcompany.smarthome.events.EventHandler {

    private final EventProcessor eventProcessor;
    private final Map<String, SensorEventType> eventAdapterMap;

    public CCEventHandlerAdapter(EventProcessor eventProcessor,
                                 Map<String, SensorEventType> eventAdapterMap) {
        this.eventProcessor = eventProcessor;
        this.eventAdapterMap = eventAdapterMap;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        eventProcessor.processEvent(adaptEvent(event));
    }

    private SensorEvent adaptEvent(CCSensorEvent event) {
        return new SensorEvent(
                eventAdapterMap.get(event.getEventType()),
                event.getObjectId()
        );
    }
}
