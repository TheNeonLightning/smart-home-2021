package ru.sbt.mipt.oop.Signalization;

import static ru.sbt.mipt.oop.Signalization.StateType.*;


public class Signalization {

    private State state;
    private final int code;

    private final Alarm alarm;
    private final Activated activated;
    private final Deactivated deactivated;

    public Signalization(int code) {
        this.code = code;
        alarm = new Alarm(this);
        activated = new Activated(this);
        deactivated = new Deactivated(this);
        state = deactivated;
    }

    public boolean isAlarmed() {
        return state.stateType() == ALARM;
    }

    public boolean isActivated() {
        return state.stateType() == ACTIVATED;
    }

    public boolean isDeactivated() {
        return state.stateType() == DEACTIVATED;
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
