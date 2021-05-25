package ru.sbt.mipt.oop.SmartHome;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable {
    Collection<Room> rooms;

    @Override
    public void execute(Action action) {
        for (Room room : rooms) {
            room.execute(action);
        }
        action.applyAction(this);
    }

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }
}
