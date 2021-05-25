package Signalization;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.Signalization.Signalization;

public class AlarmTest {

    private final int code = 4321;
    Signalization signalization = new Signalization(code);

    @Before
    public void setUpAlarmedSignalization() {
        signalization.activate(code);
        signalization.alarm();
    }

    @Test
    public void activationTest() {
        signalization.activate(code);
        Assert.assertTrue(signalization.isAlarmed());
    }

    @Test
    public void correctDeactivationTest() {
        signalization.deactivate(code);
        Assert.assertTrue(signalization.isDeactivated());
    }

    @Test
    public void wrongDeactivationTest() {
        signalization.deactivate(1234);
        Assert.assertTrue(signalization.isAlarmed());
    }

    @Test
    public void repeatedAlarmTest() {
        signalization.alarm();
        Assert.assertTrue(signalization.isAlarmed());
    }
}
