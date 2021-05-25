package ru.sbt.mipt.oop.EventResolver;

import ru.sbt.mipt.oop.SensorsInteraction.SensorEvent;

public interface EventResolver {
    void resolveEvent(SensorEvent event);
}
