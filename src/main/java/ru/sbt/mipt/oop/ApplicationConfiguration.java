package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import rc.RemoteControl;
import rc.RemoteControlRegistry;
import ru.sbt.mipt.oop.EventProcessor.*;
import ru.sbt.mipt.oop.HomeControl.HomeControl;
import ru.sbt.mipt.oop.HomeControl.HomeControlSimulator;
import ru.sbt.mipt.oop.HomeProvider.HomeProvider;
import ru.sbt.mipt.oop.HomeProvider.JsonHomeProvider;
import ru.sbt.mipt.oop.RemoteControl.Commands.*;
import ru.sbt.mipt.oop.RemoteControl.RemoteControlImpl;
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

////////////////////////////////////////////////////////////////////////////////
/// Remote Control

    @Bean
    public RemoteControl remoteControl() {
        RemoteControlImpl remoteControl = new RemoteControlImpl();

        remoteControl.setCommand("A", alarmOnCommand());
        remoteControl.setCommand("B", hallDoorCloseCommand());
        remoteControl.setCommand("C", hallLightsOnCommand());
        remoteControl.setCommand("D", homeLightsOffCommand());
        remoteControl.setCommand("1", homeLightsOnCommand());
        remoteControl.setCommand("2", signalizationActivateCommand());

        return new RemoteControlImpl();
    }

    @Bean
    RemoteControlRegistry remoteControlRegistry() {
        RemoteControlRegistry remoteControlRegistry = new RemoteControlRegistry();
        String rcId = "1";
        remoteControlRegistry.registerRemoteControl(remoteControl(), rcId);
        return remoteControlRegistry;
    }

////////////////////////////////////////////////////////////////////////////////
/// Remote Control Commands

    @Bean
    public AlarmOnCommand alarmOnCommand() {
        return new AlarmOnCommand(signalization());
    }

    @Bean
    public HallDoorCloseCommand hallDoorCloseCommand() {
        return new HallDoorCloseCommand(smartHome(), homeControl());
    }

    @Bean
    public HallLightsOnCommand hallLightsOnCommand() {
        return new HallLightsOnCommand(smartHome(), homeControl());
    }

    @Bean
    public HomeLightsOffCommand homeLightsOffCommand() {
        return new HomeLightsOffCommand(smartHome(), homeControl());
    }

    @Bean
    public HomeLightsOnCommand homeLightsOnCommand() {
        return new HomeLightsOnCommand(smartHome(), homeControl());
    }

    @Bean
    public SignalizationActivateCommand signalizationActivateCommand() {
        return new SignalizationActivateCommand(signalization());
    }
}
