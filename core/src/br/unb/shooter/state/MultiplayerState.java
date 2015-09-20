package br.unb.shooter.state;

import br.unb.shooter.controller.NetController;
import br.unb.shooter.screen.MultiplayerScreen;

public class MultiplayerState extends State {

    @Override
    public void create(StateMachine machine) {
        NetController.getInstance().setIsMultiplayer(true);
        setScreen(new MultiplayerScreen());
        getScreen().setMachine(machine);
    }

    @Override
    public void handle(StateEventEnum event) {
        if (event.equals(StateEventEnum.BUTTON_HOST)) {
            getMachine().changeState(new HostState());
        } else if (event.equals(StateEventEnum.BUTTON_BROWSE)) {
            getMachine().changeState(new BrowseState());
        } else if (event.equals(StateEventEnum.BUTTON_BACK)) {
            getMachine().changeState(new MenuState());
        }
    }

}
