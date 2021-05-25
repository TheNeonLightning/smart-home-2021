package ru.sbt.mipt.oop.Signalization;

public class Signalization {

    private State state;
    private final int code;

    private final State alarm;
    private final State activated;
    private final State deactivated;

    public Signalization(int code) {
        this.code = code;
        alarm = new Alarm(this);
        activated = new Activated(this);
        deactivated = new Deactivated(this);
        state = deactivated;
    }

    public boolean isAlarmed() {
        return state instanceof Alarm;
    }

    public boolean isActivated() {
        return state instanceof Activated;
    }

    public boolean isDeactivated() {
        return state instanceof Deactivated;
    }

    public void activate(int code) {
        state.activate(code);
    }

    public void deactivate(int code) {
        state.deactivate(code);
    }

    public void alarm() {
        state.alarm();
    }

    boolean checkCode(int code) {
       return this.code == code;
    }

    void setStateAlarm() {
        state = alarm;
    }

    void setStateActivated() {
        state = activated;
    }

    void setStateDeactivated() {
        state = deactivated;
    }
}
