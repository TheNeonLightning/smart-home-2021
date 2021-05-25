package ru.sbt.mipt.oop.EventProcessor;

import ru.sbt.mipt.oop.SensorEvent;

public interface EventProcessor {
    void processEvent(SensorEvent event);
}
