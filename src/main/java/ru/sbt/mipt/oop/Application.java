package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import rc.RemoteControl;
import rc.RemoteControlRegistry;

public class Application {

    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        SensorEventsManager sensorEventsManager = context.getBean(SensorEventsManager.class);

        RemoteControl remoteControl = context.getBean(RemoteControl.class);
        RemoteControlRegistry remoteControlRegistry = context.getBean(RemoteControlRegistry.class);

        String rcId = "1";
        remoteControlRegistry.registerRemoteControl(remoteControl, rcId);

        sensorEventsManager.start();
    }
}
