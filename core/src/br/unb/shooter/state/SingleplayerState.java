package br.unb.shooter.state;

import br.unb.shooter.controller.NetController;
import br.unb.shooter.screen.SelectPlayerScreen;

public class SingleplayerState extends State {

    @Override
    public void create(final StateMachine machine) {
        NetController.getInstance().setIsMultiplayer(false);
        setScreen(new SelectPlayerScreen());
        getScreen().setMachine(machine);
        getScreen().create();
    }

    @Override
    public void handle(StateEventEnum event) {
        if (event.equals(StateEventEnum.BUTTON_SELECT)) {
            getMachine().changeState(new GameState());
        }
    }

}
