package ru.sbt.mipt.oop.EventResolver;

import ru.sbt.mipt.oop.EventProcessor.EventProcessor;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.Signalization.Signalization;

import java.util.List;

// contains event processors for different types of events
// separates logic of choosing event processor to resolve received event
public class SmartHomeEventResolver implements EventResolver {

    private final List<EventProcessor> processors;

    public SmartHomeEventResolver(List<EventProcessor> processors) {
        this.processors = processors;
    }

    @Override
    public void resolveEvent(SensorEvent event) {
        for (EventProcessor processor : processors) {
            processor.processEvent(event);
        }
    }
}
