package ru.sbt.mipt.oop.RemoteControl.Commands;

import ru.sbt.mipt.oop.CommandType;
import ru.sbt.mipt.oop.HomeControl.HomeControl;
import ru.sbt.mipt.oop.RemoteControl.Command;
import ru.sbt.mipt.oop.SensorCommand;
import ru.sbt.mipt.oop.SmartHome.Light;
import ru.sbt.mipt.oop.SmartHome.SmartHome;

public class HomeLightsOffCommand implements Command {

    private final SmartHome smartHome;
    private final HomeControl homeControl;

    public HomeLightsOffCommand(SmartHome smartHome, HomeControl homeControl) {
        this.smartHome = smartHome;
        this.homeControl = homeControl;
    }

    @Override
    public void execute() {
        smartHome.execute((object) -> {
            if (object instanceof Light) {
                Light light = (Light) object;
                light.setOn(false);

                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF,
                        light.getId());
                homeControl.sendCommand(command);

                System.out.println("RC: turning off home lights");
            }
        });
    }
}
