package ru.sbt.mipt.oop.Signalization;

public class Alarm extends State {
    @Override
    public StateType stateType() {
        return StateType.ALARM;
    }
}
