package remotecontrol.commands;

import ru.sbt.mipt.oop.SensorsInteraction.CommandType;
import ru.sbt.mipt.oop.HomeControl.HomeControl;
import ru.sbt.mipt.oop.SensorsInteraction.SensorCommand;

class TestHomeControl implements HomeControl {

    private CommandType type = null;

    @Override
    public void sendCommand(SensorCommand command) {
        type = command.getType();
    }

    public CommandType getType() {
        return type;
    }
}
