package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.EventProcessor.*;
import ru.sbt.mipt.oop.EventResolver.EventResolver;
import ru.sbt.mipt.oop.EventResolver.SmartHomeEventResolver;
import ru.sbt.mipt.oop.HomeControl.HomeControl;
import ru.sbt.mipt.oop.HomeControl.HomeControlSimulator;
import ru.sbt.mipt.oop.HomeProvider.HomeProvider;
import ru.sbt.mipt.oop.HomeProvider.JsonHomeProvider;
import ru.sbt.mipt.oop.HomeRunner.HomeRunner;
import ru.sbt.mipt.oop.HomeRunner.SmartHomeRunner;
import ru.sbt.mipt.oop.HomeSupervision.HomeSupervision;
import ru.sbt.mipt.oop.HomeSupervision.HomeSupervisionSimulator;
import ru.sbt.mipt.oop.Notification.ContinuousNotifier;
import ru.sbt.mipt.oop.Notification.ContinuousNotifierImpl;
import ru.sbt.mipt.oop.Notification.SMSNotifier;
import ru.sbt.mipt.oop.Signalization.Signalization;
import ru.sbt.mipt.oop.SmartHome.SmartHome;


import java.util.Arrays;
import java.util.List;


// class Application is used to set up specific realizations of interfaces
// and to start smart home activity
public class Application {

    public static void main(String... args) {

        HomeControl homeControl = new HomeControlSimulator();
        HomeSupervision homeSupervision = new HomeSupervisionSimulator();

        HomeProvider homeProvider = new JsonHomeProvider("smart-home-1.js");
        SmartHome smartHome = homeProvider.provideHome();

        Signalization signalization = new Signalization(1234);

        ContinuousNotifier notifier = new ContinuousNotifierImpl(new SMSNotifier(), 100);

        List<EventProcessor> processors =
                Arrays.asList(
                        new SignalizationEventProcessorDecorator(signalization,
                                new LightEventProcessor(smartHome), notifier),

                        new SignalizationEventProcessorDecorator(signalization,
                                new DoorEventProcessor(smartHome), notifier),

                        new SignalizationEventProcessorDecorator(signalization,
                                new HallDoorEventProcessor(homeControl,
                                        smartHome), notifier),

                        new AlarmEventProcessor(signalization)
                );

        EventResolver eventResolver = new SmartHomeEventResolver(processors);

        HomeRunner homeRunner = new SmartHomeRunner(homeSupervision, eventResolver);
        homeRunner.runHome();
    }
}