package br.unb.shooter.state;

import org.junit.Assert;
import org.junit.Test;

import br.unb.shooter.screen.StartScreen;

public class StateMachineTest {
    @Test
    public void testCreateStateMachine() {
        StateMachine machine = new StateMachine();

        Assert.assertEquals(StartState.class, machine.getState().getClass());
    }

    @Test
    public void testStartToCutsceneTransition() {
        StateMachine machine = new StateMachine();

        StartState state = (StartState) machine.getState();

        StartScreen screen = (StartScreen) state.getScreen();

        screen.endTimer();

        Assert.assertEquals(CutsceneState.class, machine.getState().getClass());
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
