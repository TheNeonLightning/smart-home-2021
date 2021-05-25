package ru.sbt.mipt.oop.HomeSupervision;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;

public class HomeSupervisionSimulator implements HomeSupervision {

    // original version of this function could generate an invalid combination
    // of event and object ID (e.g. LIGHT_OFF for door object ID
    // or object ID == 0 (ID should start from 1))
    //
    // so the function got changed a little bit
    public SensorEvent getNextSensorEvent() {

        if (Math.random() < 0.05) {
            return null;
        }

        int eventIndex = (int) (4 * Math.random());
        SensorEventType sensorEventType = SensorEventType.values()[eventIndex];
        String objectId;

        if (eventIndex < 2) {
            objectId = "" + ((int) (9 * Math.random()) + 1);
        } else {
            objectId = "" + ((int) (4 * Math.random()) + 1);
        }

        return new SensorEvent(sensorEventType, objectId);
    }
}
