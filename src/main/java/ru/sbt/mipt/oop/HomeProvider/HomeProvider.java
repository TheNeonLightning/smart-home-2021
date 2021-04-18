package ru.sbt.mipt.oop.HomeProvider;


import ru.sbt.mipt.oop.SmartHome;

// interface HomeProvider is used to separate getting SmartHome logic
public interface HomeProvider {
    SmartHome provideHome();
}
