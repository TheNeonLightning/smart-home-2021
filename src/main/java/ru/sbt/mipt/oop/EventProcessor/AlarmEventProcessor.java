package ru.sbt.mipt.oop.EventProcessor;

import ru.sbt.mipt.oop.SensorsInteraction.SensorEvent;
import ru.sbt.mipt.oop.SensorsInteraction.SensorEventType;
import ru.sbt.mipt.oop.Signalization.Signalization;

public class AlarmEventProcessor implements EventProcessor {

    private final Signalization signalization;

    public AlarmEventProcessor(Signalization signalization) {
        this.signalization = signalization;
    }

    @Override
    public void processEvent(SensorEvent event) {
        if (event.getType() == SensorEventType.ALARM_ACTIVATE) {
            signalization.activate(event.getType().code);
        }
        if (event.getType() == SensorEventType.ALARM_DEACTIVATE) {
            signalization.deactivate(event.getType().code);
        }
    }
}
