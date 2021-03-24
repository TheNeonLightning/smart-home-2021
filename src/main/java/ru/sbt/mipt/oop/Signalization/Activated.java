package ru.sbt.mipt.oop.Signalization;

public class Activated extends State {

    private final Signalization signalization;

    Activated(Signalization signalization) {
        this.signalization = signalization;
    }

    @Override
    public StateType stateType() {
        return StateType.ACTIVATED;
    }

    @Override
    public void deactivate(int code) {
        if (signalization.checkCode(code)) {
            signalization.setStateDeactivated();
        } else {
            signalization.setStateAlarm();
        }
    }
}
