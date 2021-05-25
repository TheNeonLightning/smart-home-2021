package ru.sbt.mipt.oop.EventProcessor;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.SmartHome.Light;
import ru.sbt.mipt.oop.SmartHome.Room;
import ru.sbt.mipt.oop.SmartHome.SmartHome;


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
        Light light = findLight(lightId);
        light.setOn(true);
        System.out.println("Light " + light.getId() + " was turned on.");
    }

    private void handleLightOff(String lightId) {
        Light light = findLight(lightId);
        light.setOn(false);
        System.out.println("Light " + light.getId() + " was turned off.");
    }

    private Light findLight(String lightId) {
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(lightId)) {
                    return light;
                }
            }
        }
        throw new RuntimeException("Light ID not found");
    }
}
