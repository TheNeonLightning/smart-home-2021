package ru.sbt.mipt.oop.SmartHome;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;

public class Door implements Actionable {
    private final String id;
    private boolean isOpen;

    @Override
    public void execute(Action action) {
        action.applyAction(this);
    }

    public Door(boolean isOpen, String id) {
        this.isOpen = isOpen;
        this.id = id;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public String getId() {
        return id;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
