package br.unb.shooter.state;

import org.easymock.EasyMock;
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

    @Test
    public void testStartToCutsceneTransition() throws Exception {
        StateMachine machine = new StateMachine();

        StartState startState = EasyMock.createMockBuilder(StartState.class).addMockedMethod("dispose").createMock();

        startState.dispose();

        EasyMock.expectLastCall().times(1);

        EasyMock.replay(startState);

        machine.setState(startState);

        startState.setMachine(machine);

        startState.handle(StateEventEnum.TIMER_END);

        Assert.assertEquals(CutsceneState.class, machine.getState().getClass());

        EasyMock.verify(startState);
    }
}
