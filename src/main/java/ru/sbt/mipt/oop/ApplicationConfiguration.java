package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import ru.sbt.mipt.oop.EventProcessor.*;
import ru.sbt.mipt.oop.HomeControl.HomeControl;
import ru.sbt.mipt.oop.HomeControl.HomeControlSimulator;
import ru.sbt.mipt.oop.HomeProvider.HomeProvider;
import ru.sbt.mipt.oop.HomeProvider.JsonHomeProvider;
import ru.sbt.mipt.oop.Signalization.Signalization;
import ru.sbt.mipt.oop.SmartHome.SmartHome;

import java.util.Collection;
import java.util.Map;

public class ApplicationConfiguration {

    @Bean
    public SmartHome smartHome() {
        HomeProvider homeProvider = new JsonHomeProvider("smart-home-1.js");
        return homeProvider.provideHome();
    }

    @Bean
    public Map<String, SensorEventType> sensorEventTypeMap() {
        return Map.of(
            "LightIsOn", SensorEventType.LIGHT_ON,
            "LightIsOff", SensorEventType.LIGHT_OFF,
            "DoorIsOpen", SensorEventType.DOOR_OPEN,
            "DoorIsClosed", SensorEventType.DOOR_CLOSED,
            "DoorIsLocked", SensorEventType.DOOR_LOCKED,
            "DoorIsUnlocked", SensorEventType.DOOR_UNLOCKED
        );
    }
    
    @Bean
    public EventProcessor lightEventProcessor() {
        return new SignalizationEventProcessorDecorator(signalization(),
                new LightEventProcessor(smartHome()));
    }

    @Bean
    public EventProcessor doorEventProcessor() {
        return new SignalizationEventProcessorDecorator(signalization(),
                new DoorEventProcessor(smartHome()));
    }

    @Bean
    public EventProcessor hallDoorEventProcessor() {
        return new SignalizationEventProcessorDecorator(signalization(),
                new HallDoorEventProcessor(homeControl(), smartHome()));
    }

    @Bean
    public EventProcessor alarmEventProcessor() {
        return new AlarmEventProcessor(signalization());
    }

    @Bean
    public SensorEventsManager sensorEventsManager(Collection<EventProcessor> eventProcessors) {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();

        for (EventProcessor processor : eventProcessors) {
            sensorEventsManager.registerEventHandler(
                    new CCEventHandlerAdapter(processor, sensorEventTypeMap()));
        }
        return sensorEventsManager;
    }

    @Bean
    public Signalization signalization() {
        return new Signalization(1234);
    }

    @Bean
    public HomeControl homeControl() {
        return new HomeControlSimulator();
    }
}
