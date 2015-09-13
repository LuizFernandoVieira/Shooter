package br.unb.shooter.state;

import org.junit.Assert;
import org.junit.Test;

public class StateMachineTest {
    @Test
    public void testCreateStateMachine() {
        StateMachine machine = new StateMachine();

        Assert.assertEquals(StartState.class, machine.getState().getClass());
    }

    @Test
    public void testStartToCutsceneTransition() {
    }

    @Test
    public void testCutsceneToMenuTransition() {
    }

    @Test
    public void testMenuToSingleplayerTransition() {
    }

    @Test
    public void testMenuToMultiplayerTransition() {
    }

    @Test
    public void testSingleplayerToGameTransition() {
    }

    @Test
    public void testMultiplayerToHostTransition() {
    }

    @Test
    public void testMultiplayerToBrowseTransition() {
    }
}
