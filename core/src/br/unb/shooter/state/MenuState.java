package br.unb.shooter.state;

import com.badlogic.gdx.Gdx;

import br.unb.shooter.controller.NetController;
import br.unb.shooter.screen.MenuScreen;

public class MenuState extends State {

    @Override
    public void create(final StateMachine machine) {
        setScreen(new MenuScreen());
        getScreen().setMachine(machine);
        getScreen().create();
        NetController.getInstance().setIsMultiplayer(false);
    }

    @Override
    public void handle(StateEventEnum event) {
        if (event.equals(StateEventEnum.BUTTON_CREDITS)) {
            getMachine().changeState(new CreditsState());
        } else if (event.equals(StateEventEnum.BUTTON_SINGLEPLAYER)) {
            getMachine().changeState(new SingleplayerState());
        } else if (event.equals(StateEventEnum.BUTTON_MULTIPLAYER)) {
            getMachine().changeState(new MultiplayerState());
        } else if (event.equals(StateEventEnum.BUTTON_SETTINGS)) {
            getMachine().changeState(new SettingsState());
        } else if (event.equals(StateEventEnum.BUTTON_CUTSCENE)) {
            getMachine().changeState(new CutsceneState());
        } else if (event.equals(StateEventEnum.BUTTON_QUIT)) {
            Gdx.app.exit();
        }
    }

}
