package ru.sbt.mipt.oop.Signalization;

public class Deactivated extends State {

    private final Signalization signalization;

    Deactivated(Signalization signalization) {
        this.signalization = signalization;
    }

    @Override
    public StateType stateType() {
        return StateType.DEACTIVATED;
    }

    @Override
    public void activate(int code) {
        if (signalization.checkCode(code)) {
            signalization.setStateActivated();
        }
    }
}
