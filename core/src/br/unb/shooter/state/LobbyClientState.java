package br.unb.shooter.state;

import br.unb.shooter.controller.NetController;
import br.unb.shooter.screen.LobbyScreen;

public class LobbyClientState extends State {

    @Override
    public void create(StateMachine machine) {
        setMachine(machine);
        setScreen(new LobbyScreen(false));
        getScreen().setMachine(machine);
        getScreen().create();

        NetController.getInstance().addClientListener();

        NetController.getInstance().connectClient(NetController.getInstance().getSelectedServerIp());
    }

    @Override
    public void handle(StateEventEnum event) {
        if (event.equals(StateEventEnum.BUTTON_START) || event.equals(StateEventEnum.START_GAME)) {
            getMachine().changeState(new GameState(false));
        }
    }

}
