package ru.sbt.mipt.oop.RemoteControl.Commands;

import ru.sbt.mipt.oop.CommandType;
import ru.sbt.mipt.oop.HomeControl.HomeControl;
import ru.sbt.mipt.oop.RemoteControl.Command;
import ru.sbt.mipt.oop.SensorCommand;
import ru.sbt.mipt.oop.SmartHome.Light;
import ru.sbt.mipt.oop.SmartHome.SmartHome;

public class HomeLightsOnCommand implements Command {

    private final SmartHome smartHome;
    private final HomeControl homeControl;

    public HomeLightsOnCommand(SmartHome smartHome, HomeControl homeControl) {
        this.smartHome = smartHome;
        this.homeControl = homeControl;
    }

    @Override
    public void execute() {
        smartHome.execute((object) -> {
            if (object instanceof Light) {
                Light light = (Light) object;
                light.setOn(true);

                SensorCommand command = new SensorCommand(CommandType.LIGHT_ON,
                        light.getId());
                homeControl.sendCommand(command);

                System.out.println("RC: turing on home lights");
            }
        });
    }
}
