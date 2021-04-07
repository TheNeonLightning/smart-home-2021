package ru.sbt.mipt.oop.Signalization;

public class Signalization {

    private State state;
    private final int code;

    private final Alarm alarm;
    private final Activated activated;
    private final Deactivated deactivated;

    public Signalization(int code) {
        this.code = code;
        alarm = new Alarm();
        activated = new Activated(this);
        deactivated = new Deactivated(this);
        state = deactivated;
    }

    public boolean checkCode(int code) {
       return this.code == code;
    }

    public StateType stateType() {
        return state.stateType();
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
