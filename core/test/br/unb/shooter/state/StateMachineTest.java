package br.unb.shooter.state;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CutsceneState.class)
public class StateMachineTest {
    @Test
    public void testCreateStateMachine() {
        StateMachine machine = new StateMachine();

        Assert.assertEquals(StartState.class, machine.getState().getClass());
    }

}
