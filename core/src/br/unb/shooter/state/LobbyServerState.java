package br.unb.shooter.state;

import br.unb.shooter.controller.NetController;
import br.unb.shooter.screen.LobbyScreen;

public class LobbyServerState extends State {

    @Override
    public void create(StateMachine machine) {
        setScreen(new LobbyScreen(true));
        getScreen().setMachine(machine);

        NetController.getInstance().createServerAndListener();
    }

}
