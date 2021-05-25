package ru.sbt.mipt.oop.HomeSupervision;

import ru.sbt.mipt.oop.SensorsInteraction.SensorEvent;

// interface HomeSupervision is used to separate sensor events
// logic (receiving events from sensors)
public interface HomeSupervision {
    SensorEvent getNextSensorEvent();
}
