package ru.sbt.mipt.oop.Signalization;

import static ru.sbt.mipt.oop.Signalization.StateType.ALARM;


public class Alarm implements State {

    private final Signalization signalization;

    Alarm(Signalization signalization) {
        this.signalization = signalization;
    }

    @Override
    public void activate(int code) {}

    @Override
    public void deactivate(int code) {
        if (signalization.checkCode(code)) {
            signalization.setStateDeactivated();
        } else {
            signalization.setStateAlarm();
        }
    }

    @Override
    public void alarm() {}

    @Override
    public StateType stateType() {
        return ALARM;
    }
}
