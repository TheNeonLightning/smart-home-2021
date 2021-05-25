package ru.sbt.mipt.oop.EventProcessor;

import ru.sbt.mipt.oop.Notification.ContinuousNotifier;
import ru.sbt.mipt.oop.SensorsInteraction.SensorEvent;
import ru.sbt.mipt.oop.SensorsInteraction.SensorEventType;
import ru.sbt.mipt.oop.Signalization.Signalization;

public class SignalizationEventProcessorDecorator implements EventProcessor {

    private final Signalization signalization;
    private final EventProcessor eventProcessor;
    private final ContinuousNotifier notifier;

    public SignalizationEventProcessorDecorator(Signalization signalization,
                                                EventProcessor eventProcessor,
                                                ContinuousNotifier notifier) {
        this.signalization = signalization;
        this.eventProcessor = eventProcessor;
        this.notifier = notifier;
    }

    @Override
    public void processEvent(SensorEvent event) {

        if (isSignalizationEvent(event)) {
            eventProcessor.processEvent(event);

        } else if (signalization.isActivated()) {
            signalization.alarm();
            notifier.startNotifying();

        } else if (signalization.isAlarmed()) {
            notifier.startNotifying();

        } else {
            eventProcessor.processEvent(event);
        }
    }

    private boolean isSignalizationEvent(SensorEvent event) {
        return event.getType() == SensorEventType.ALARM_ACTIVATE ||
               event.getType() == SensorEventType.ALARM_DEACTIVATE;
    }
}
