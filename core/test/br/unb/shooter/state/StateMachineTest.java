package br.unb.shooter.state;

import java.io.File;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import br.unb.shooter.screen.Screen;
import br.unb.shooter.screen.StartScreen;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Screen.class)
public class StateMachineTest {
    @Test
    public void testCreateStateMachine() {
        StateMachine machine = new StateMachine();

        Assert.assertEquals(StartState.class, machine.getState().getClass());
    }

    @Test
    public void testStartToCutsceneTransition() throws Exception {
        // Mock stage
        Stage stage = PowerMock.createMock(Stage.class);

        PowerMock.expectNew(Stage.class).andReturn(stage).times(1);

        PowerMock.replay(stage, Stage.class);

        // Mock Gdx.input
        Input input = EasyMock.createMock(Input.class);

        input.setInputProcessor(EasyMock.anyObject(InputProcessor.class));

        EasyMock.expectLastCall().anyTimes();

        EasyMock.replay(input);

        Gdx.input = input;

        // Mock skin
        Skin skin = PowerMock.createMock(Skin.class);

        PowerMock.expectNew(Skin.class, FileHandle.class).andReturn(skin).times(1);

        PowerMock.replay(skin, Skin.class);

        // Mock Gdx.files
        Files files = EasyMock.createMock(Files.class);

        EasyMock.expect(files.internal(EasyMock.anyString())).andReturn(new FileHandle(new File("/local.cfg"))).times(1);

        EasyMock.replay(files);

        Gdx.files = files;

        StateMachine machine = new StateMachine();

        machine.create();

        PowerMock.verify(stage);

        EasyMock.verify(input);

        PowerMock.verify(skin);

        EasyMock.verify(files);

        StartState startState = (StartState) machine.getState();

        StartScreen startScreen = (StartScreen) startState.getScreen();

        startScreen.endTimer();

        Assert.assertEquals(CutsceneState.class, machine.getState().getClass());
    }
    /*
     * @Test public void testCutsceneToMenuTransition() { StateMachine machine =
     * new StateMachine();
     * 
     * machine.create();
     * 
     * StartState startState = (StartState) machine.getState();
     * 
     * StartScreen startScreen = (StartScreen) startState.getScreen();
     * 
     * startScreen.endTimer();
     * 
     * CutsceneState cutsceneState = (CutsceneState) machine.getState();
     * 
     * CutsceneScreen cutsceneScreen = (CutsceneScreen)
     * cutsceneState.getScreen();
     * 
     * cutsceneScreen.changeState();
     * 
     * Assert.assertEquals(MenuState.class, machine.getState().getClass()); }
     */
}
