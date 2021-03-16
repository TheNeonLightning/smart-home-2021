package ru.sbt.mipt.oop.EventProcessor;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.SmartHome.*;


public class LightEventProcessor implements EventProcessor {

    private final SmartHome smartHome;

    public LightEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void processEvent(SensorEvent event) {
        switch (event.getType()) {
            case LIGHT_ON -> handleLightOn(event.getObjectId());
            case LIGHT_OFF -> handleLightOff(event.getObjectId());
        }
    }

    private void handleLightOn(String lightId) {
        smartHome.execute((object) -> {
            if (object instanceof Light) {
                Light light = (Light) object;
                if (light.getId().equals(lightId)) {
                    light.setOn(true);
                    System.out.println("Light " +
                            light.getId() + " was turned on.");
                }
            }
        });
    }

    private void handleLightOff(String lightId) {
        smartHome.execute((object) -> {
            if (object instanceof Light) {
                Light light = (Light) object;
                if (light.getId().equals(lightId)) {
                    light.setOn(false);
                    System.out.println("Light " +
                            light.getId() + " was turned off.");
                }
            }
        });
    }
}
