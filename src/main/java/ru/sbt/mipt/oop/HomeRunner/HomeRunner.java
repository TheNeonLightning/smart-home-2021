package ru.sbt.mipt.oop.HomeRunner;


// interface HomeRunner is used to separate logic of receiving and processing
// events:
// e.g. using while cycle (like in current realization)
// or receiving a group of events simultaneously and processing them according
// to the specific rules (might be doors first then lights)
// or sleeping until some event occurs instead of constant running
// and etc.
public interface HomeRunner {
    void runHome();
}
