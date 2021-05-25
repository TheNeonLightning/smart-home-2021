package ru.sbt.mipt.oop.HomeControl;

import ru.sbt.mipt.oop.SensorCommand;

// interface HomeControl is used to separate home control
// logic (command part of interaction with home)
public interface HomeControl {
    void sendCommand(SensorCommand command);
}
