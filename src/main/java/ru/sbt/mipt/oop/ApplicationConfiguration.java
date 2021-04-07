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

public class ApplicationConfiguration {

    @Bean
    public SmartHome smartHome() {
        HomeProvider homeProvider = new JsonHomeProvider("smart-home-1.js");
        return homeProvider.provideHome();
    }

    @Bean
    public LightEventProcessor lightEventProcessor() {
        return new LightEventProcessor(smartHome());
    }

    @Bean
    public DoorEventProcessor doorEventProcessor() {
        return new DoorEventProcessor(smartHome());
    }

    @Bean
    public HallDoorEventProcessor hallDoorEventProcessor() {
        return new HallDoorEventProcessor(homeControl(), smartHome());
    }

    @Bean
    public AlarmEventProcessor alarmEventProcessor() {
        return new AlarmEventProcessor(signalization());
    }

    @Bean
    public SensorEventsManager sensorEventsManager() {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();

        EventProcessor eventProcessor =
                new SignalizationEventProcessorDecorator(signalization(),
                        lightEventProcessor());
        sensorEventsManager.registerEventHandler(
                new CCEventHandlerAdapter(eventProcessor));

        eventProcessor =
                new SignalizationEventProcessorDecorator(signalization(),
                        doorEventProcessor());
        sensorEventsManager.registerEventHandler(
                new CCEventHandlerAdapter(eventProcessor));

        eventProcessor =
                new SignalizationEventProcessorDecorator(signalization(),
                        hallDoorEventProcessor());
        sensorEventsManager.registerEventHandler(
                new CCEventHandlerAdapter(eventProcessor));

        sensorEventsManager.registerEventHandler(
                new CCEventHandlerAdapter(alarmEventProcessor()));

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
