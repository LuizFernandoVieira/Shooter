package br.unb.shooter.state;

import br.unb.shooter.controller.NetController;
import br.unb.shooter.screen.LobbyScreen;

public class LobbyClientState extends State {

    @Override
    public void create(StateMachine machine) {
        setScreen(new LobbyScreen(false));
        getScreen().setMachine(machine);

        NetController.getInstance().addClientListener();

        NetController.getInstance().connectClient(NetController.getInstance().getSelectedServerIp());
    }

    @Override
    public void handle(StateEventEnum event) {
        if (event.equals(StateEventEnum.BUTTON_START)) {
            getMachine().changeState(new GameState());
        }
    }

}
