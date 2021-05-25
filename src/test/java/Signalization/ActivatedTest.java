package Signalization;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.Signalization.Signalization;

public class ActivatedTest {

    private final int code = 4321;
    Signalization signalization = new Signalization(code);

    @Before
    public void setUpActivatedSignalization() {
        signalization.activate(code);
    }

    @Test
    public void repeatedActivationTest() {
        signalization.activate(code);
        Assert.assertTrue(signalization.isActivated());
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
    public void alarmTest() {
        signalization.alarm();
        Assert.assertTrue(signalization.isAlarmed());
    }
}
