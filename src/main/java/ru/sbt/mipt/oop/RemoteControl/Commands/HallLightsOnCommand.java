package ru.sbt.mipt.oop.RemoteControl.Commands;

import ru.sbt.mipt.oop.CommandType;
import ru.sbt.mipt.oop.HomeControl.HomeControl;
import ru.sbt.mipt.oop.RemoteControl.Command;
import ru.sbt.mipt.oop.SensorCommand;
import ru.sbt.mipt.oop.SmartHome.Light;
import ru.sbt.mipt.oop.SmartHome.Room;
import ru.sbt.mipt.oop.SmartHome.SmartHome;

public class HallLightsOnCommand implements Command {

    private final SmartHome smartHome;
    private final HomeControl homeControl;

    public HallLightsOnCommand(SmartHome smartHome, HomeControl homeControl) {
        this.smartHome = smartHome;
        this.homeControl = homeControl;
    }

    @Override
    public void execute() {
        smartHome.execute(object -> {
            if (object instanceof Room) {
                Room room = (Room) object;
                if (room.getName().equals("hall")) {
                    room.execute(localObject -> {
                        if (localObject instanceof Light) {
                            Light light = (Light) localObject;
                            light.setOn(true);

                            SensorCommand command = new SensorCommand(CommandType.LIGHT_ON,
                                    light.getId());
                            homeControl.sendCommand(command);

                            System.out.println("RC: turning on hall lights");
                        }
                    });
                }
            }
        });
    }
}
