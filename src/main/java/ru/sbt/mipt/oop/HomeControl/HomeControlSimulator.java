package ru.sbt.mipt.oop.HomeControl;

import ru.sbt.mipt.oop.SensorCommand;


public class HomeControlSimulator implements HomeControl {

    public void sendCommand(SensorCommand command) {
        System.out.println("Pretend we're sending command " + command);
    }
}
