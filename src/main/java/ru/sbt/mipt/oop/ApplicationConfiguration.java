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
import ru.sbt.mipt.oop.Notification.ContinuousNotifier;
import ru.sbt.mipt.oop.Notification.ContinuousNotifierImpl;
import ru.sbt.mipt.oop.Notification.Notifier;
import ru.sbt.mipt.oop.Notification.SMSNotifier;
import ru.sbt.mipt.oop.RemoteControl.Commands.*;
import ru.sbt.mipt.oop.RemoteControl.RemoteControlImpl;
import ru.sbt.mipt.oop.SensorsInteraction.SensorEventType;
import ru.sbt.mipt.oop.Signalization.Signalization;
import ru.sbt.mipt.oop.SmartHome.SmartHome;

import java.util.Collection;
import java.util.Map;

public class ApplicationConfiguration {

    @Bean
    public SmartHome smartHome() {
        HomeProvider homeProvider = new JsonHomeProvider("smart-home-1.json");
        return homeProvider.provideHome();
    }

    @Bean
    public Map<String, SensorEventType> eventAdapterMap() {
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
    public Notifier smsNotifier() {
        return new SMSNotifier();
    }

    @Bean
    public ContinuousNotifier continuousNotifierImpl() {
        return new ContinuousNotifierImpl(smsNotifier());
    }

    @Bean
    public EventProcessor lightEventProcessor() {
        return new SignalizationEventProcessorDecorator(signalization(),
                new LightEventProcessor(smartHome()),
                continuousNotifierImpl());
    }

    @Bean
    public EventProcessor doorEventProcessor() {
        return new SignalizationEventProcessorDecorator(signalization(),
                new DoorEventProcessor(smartHome()),
                continuousNotifierImpl());
    }

    @Bean
    public EventProcessor hallDoorEventProcessor() {
        return new SignalizationEventProcessorDecorator(signalization(),
                new HallDoorEventProcessor(homeControl(), smartHome()),
                continuousNotifierImpl());
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
                    new CCEventHandlerAdapter(processor, eventAdapterMap()));
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
