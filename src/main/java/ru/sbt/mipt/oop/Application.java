package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.EventResolver.EventResolver;
import ru.sbt.mipt.oop.EventResolver.SmartHomeEventResolver;
import ru.sbt.mipt.oop.EventProcessor.DoorEventProcessor;
import ru.sbt.mipt.oop.EventProcessor.HallDoorEventProcessor;
import ru.sbt.mipt.oop.EventProcessor.LightEventProcessor;
import ru.sbt.mipt.oop.HomeControl.HomeControl;
import ru.sbt.mipt.oop.HomeControl.HomeControlSimulator;
import ru.sbt.mipt.oop.HomeProvider.HomeProvider;
import ru.sbt.mipt.oop.HomeProvider.JsonHomeProvider;
import ru.sbt.mipt.oop.HomeRunner.HomeRunner;
import ru.sbt.mipt.oop.HomeRunner.SmartHomeRunner;
import ru.sbt.mipt.oop.HomeSupervision.HomeSupervision;
import ru.sbt.mipt.oop.HomeSupervision.HomeSupervisionSimulator;
import ru.sbt.mipt.oop.SmartHome.SmartHome;

import java.util.Arrays;

// class Application is used to set up specific realizations of interfaces
// and to start smart home activity
public class Application {

    public static void main(String... args) {

        HomeControl homeControl = new HomeControlSimulator();
        HomeSupervision homeSupervision = new HomeSupervisionSimulator();

        HomeProvider homeProvider = new JsonHomeProvider("smart-home-1.js");
        SmartHome smartHome = homeProvider.provideHome();

        EventResolver eventResolver = new SmartHomeEventResolver(
                Arrays.asList(
                        new LightEventProcessor(smartHome),
                        new DoorEventProcessor(smartHome),
                        new HallDoorEventProcessor(homeControl, smartHome))
        );

        HomeRunner homeRunner = new SmartHomeRunner(homeSupervision, eventResolver);
        homeRunner.runHome();
    }
}