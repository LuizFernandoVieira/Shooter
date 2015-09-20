package br.unb.shooter.screen;

import br.unb.shooter.state.StateEventEnum;

public class SelectPlayerScreen extends Screen {

    @Override
    public void create() {
        getMachine().handle(StateEventEnum.BUTTON_SELECT);
    }

}
