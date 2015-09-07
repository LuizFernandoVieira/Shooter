package br.unb.shooter.state;

import br.unb.shooter.controller.NetController;
import br.unb.shooter.screen.LobbyScreen;

public class LobbyClientState implements IState {

    private LobbyScreen screen;

    @Override
    public void create(StateMachine machine) {
        screen = new LobbyScreen(false);
        screen.setMachine(machine);

        NetController.getInstance().addClientListener();

        NetController.getInstance().connectClient(NetController.getInstance().getSelectedServerIp());
    }

    @Override
    public void update() {
        screen.update();
    }

    @Override
    public void draw() {
        screen.draw();
    }

    @Override
    public void dispose() {
        screen.dispose();
    }

}
