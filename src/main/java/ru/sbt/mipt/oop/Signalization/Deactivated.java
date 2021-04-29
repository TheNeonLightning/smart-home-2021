package ru.sbt.mipt.oop.Signalization;


public class Deactivated implements State {

    private final Signalization signalization;

    Deactivated(Signalization signalization) {
        this.signalization = signalization;
    }

    @Override
    public void activate(int code) {
        if (signalization.checkCode(code)) {
            signalization.setStateActivated();
        }
    }

    @Override
    public void deactivate(int code) {}

    @Override
    public void alarm() {}
}
