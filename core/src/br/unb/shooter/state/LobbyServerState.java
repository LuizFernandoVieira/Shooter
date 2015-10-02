package br.unb.shooter.state;

import br.unb.shooter.controller.NetController;
import br.unb.shooter.screen.LobbyScreen;

public class LobbyServerState extends State {

    @Override
    public void create(StateMachine machine) {
        Boolean isServer = true;

        setMachine(machine);
        setScreen(new LobbyScreen(isServer));
        getScreen().setMachine(machine);
        getScreen().create();

        NetController.getInstance().createServerAndListener();
        NetController.getInstance().setIsServer(isServer);
    }

    @Override
    public void handle(StateEventEnum event) {
        if (event.equals(StateEventEnum.BUTTON_START)) {
            getMachine().changeState(new GameState());
        }
    }

}
