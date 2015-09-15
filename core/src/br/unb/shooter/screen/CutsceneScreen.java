package br.unb.shooter.screen;

import br.unb.shooter.state.MenuState;

public class CutsceneScreen extends Screen {

    @Override
    public void create() {
        changeState();
    }

    public void endScene() {
        changeState();
    }

    public void escKey() {
        changeState();
    }

    public void changeState() {
        getMachine().changeState(new MenuState());
    }

}
