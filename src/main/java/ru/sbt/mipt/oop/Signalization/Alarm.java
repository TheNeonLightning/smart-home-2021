package ru.sbt.mipt.oop.Signalization;


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
}
