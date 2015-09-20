package br.unb.shooter.screen;

import br.unb.shooter.state.StateEventEnum;

public class CutsceneScreen extends Screen {

    @Override
    public void create() {
        changeState();
    }

    public void changeState() {
        getMachine().handle(StateEventEnum.STATE_END);
    }

}
