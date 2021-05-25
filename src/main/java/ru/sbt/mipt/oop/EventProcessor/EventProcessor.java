package ru.sbt.mipt.oop.EventProcessor;

import ru.sbt.mipt.oop.SensorsInteraction.SensorEvent;

public interface EventProcessor {
    void processEvent(SensorEvent event);
}
