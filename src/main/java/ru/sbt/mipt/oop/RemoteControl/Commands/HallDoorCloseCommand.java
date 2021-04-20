package ru.sbt.mipt.oop.RemoteControl.Commands;

import ru.sbt.mipt.oop.CommandType;
import ru.sbt.mipt.oop.HomeControl.HomeControl;
import ru.sbt.mipt.oop.RemoteControl.Command;
import ru.sbt.mipt.oop.SensorCommand;
import ru.sbt.mipt.oop.SmartHome.Door;
import ru.sbt.mipt.oop.SmartHome.Room;
import ru.sbt.mipt.oop.SmartHome.SmartHome;

public class HallDoorCloseCommand implements Command {

    private final SmartHome smartHome;
    private final HomeControl homeControl;

    public HallDoorCloseCommand(SmartHome smartHome, HomeControl homeControl) {
        this.smartHome = smartHome;
        this.homeControl = homeControl;
    }

    // TODO if where are several doors in hall should check doorId
    @Override
    public void execute() {
        smartHome.execute(object -> {
            if (object instanceof Room) {
                Room room = (Room) object;
                if (room.getName().equals("hall")) {
                    room.execute(localObject -> {
                        if (localObject instanceof Door) {
                            Door door = (Door) localObject;
                            door.setOpen(false);

                            SensorCommand command = new SensorCommand(CommandType.DOOR_CLOSE,
                                    door.getId());
                            homeControl.sendCommand(command);

                            System.out.println("RC: Closing hall door");
                        }
                    });
                }
            }
        });
    }
}
