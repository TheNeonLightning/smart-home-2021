package ru.sbt.mipt.oop.EventProcessor;

import ru.sbt.mipt.oop.*;


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
        Light light = SmartHomeUtility.findLight(smartHome, lightId);
        light.setOn(true);
        System.out.println("Light " + light.getId() + " was turned on.");
    }

    private void handleLightOff(String lightId) {
        Light light = SmartHomeUtility.findLight(smartHome, lightId);
        light.setOn(false);
        System.out.println("Light " + light.getId() + " was turned off.");
    }
}
