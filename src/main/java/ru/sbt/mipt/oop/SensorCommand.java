package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.RemoteControl.Command;

public class SensorCommand {
    private final CommandType type;
    private final String objectId;

    public SensorCommand(CommandType type, String objectId) {
        this.type = type;
        this.objectId = objectId;
    }

    @Override
    public String toString() {
        return "SensorCommand{" +
                "type=" + type +
                ", objectId='" + objectId + '\'' +
                '}';
    }

    public CommandType getType() {
        return type;
    }
}
