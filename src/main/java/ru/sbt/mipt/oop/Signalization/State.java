package ru.sbt.mipt.oop.Signalization;

public interface State {

    void activate(int code);

    void deactivate(int code);

    void alarm();
}
