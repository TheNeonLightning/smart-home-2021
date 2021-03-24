package ru.sbt.mipt.oop.Signalization;

public abstract class State {

    void activate(int code) {}

    void deactivate(int code) {}

    void alarm() {}

    abstract StateType stateType();
}
