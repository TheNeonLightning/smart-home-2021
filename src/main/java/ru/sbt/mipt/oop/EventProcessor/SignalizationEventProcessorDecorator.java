package ru.sbt.mipt.oop.EventProcessor;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.Signalization.Alarm;
import ru.sbt.mipt.oop.Signalization.Signalization;
import static ru.sbt.mipt.oop.Signalization.StateType.ACTIVATED;
import static ru.sbt.mipt.oop.Signalization.StateType.ALARM;

public class SignalizationEventProcessorDecorator implements EventProcessor {

    private final Signalization signalization;
    private final EventProcessor eventProcessor;

    public SignalizationEventProcessorDecorator(Signalization signalization,
                                                EventProcessor eventProcessor) {
        this.signalization = signalization;
        this.eventProcessor = eventProcessor;
    }

    @Override
    public void processEvent(SensorEvent event) {

        if (isSignalizationEvent(event)) {
            eventProcessor.processEvent(event);
        }

        if (signalization.stateType() == ACTIVATED) {
            signalization.alarm();
            sendMessage();
        }

        // TODO sending messages should not depend on events
        if (signalization.stateType() == ALARM) {
            sendMessage();
        }
        eventProcessor.processEvent(event);
    }

    private boolean isSignalizationEvent(SensorEvent event) {
        return event.getType() == SensorEventType.ALARM_ACTIVATE ||
                event.getType() == SensorEventType.ALARM_DEACTIVATE;
    }

    private void sendMessage() {
        System.out.println("Sending sms");
    }
}
