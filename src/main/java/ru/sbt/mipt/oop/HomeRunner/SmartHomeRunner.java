package ru.sbt.mipt.oop.HomeRunner;

import ru.sbt.mipt.oop.EventResolver.EventResolver;
import ru.sbt.mipt.oop.HomeSupervision.HomeSupervision;
import ru.sbt.mipt.oop.SensorsInteraction.SensorEvent;

public class SmartHomeRunner implements HomeRunner {

    private final HomeSupervision homeSupervision;
    private final EventResolver eventResolver;

    public SmartHomeRunner(HomeSupervision homeSupervision, EventResolver eventResolver) {
        this.homeSupervision = homeSupervision;
        this.eventResolver = eventResolver;
    }

    @Override
    public void runHome() {
        SensorEvent event = homeSupervision.getNextSensorEvent();

        while (event != null) {
            System.out.println("Got event: " + event);
            eventResolver.resolveEvent(event);
            event = homeSupervision.getNextSensorEvent();
        }
    }

}
