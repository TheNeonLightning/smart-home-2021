package Signalization;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.Signalization.Signalization;

public class DeactivatedTest {

    private final int code = 4321;
    Signalization signalization = new Signalization(code);

    @Before
    public void setUpDeactivatedSignalization() {
        signalization.deactivate(code);
    }

    @Test
    public void activateTest() {
        signalization.activate(code);
        Assert.assertTrue(signalization.isActivated());
    }

    @Test
    public void repeatDeactivationTest() {
        signalization.deactivate(code);
        Assert.assertTrue(signalization.isDeactivated());
    }

    @Test
    public void wrongActivationTest() {
        signalization.activate(1234);
        Assert.assertTrue(signalization.isDeactivated());
    }

    @Test
    public void alarmTest() {
        signalization.alarm();
        Assert.assertTrue(signalization.isDeactivated());
    }
}
