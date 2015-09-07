package br.unb.shooter.state;

import br.unb.shooter.controller.NetController;
import br.unb.shooter.screen.LobbyScreen;

public class LobbyServerState implements IState {

    private LobbyScreen screen;

    @Override
    public void create(StateMachine machine) {
        screen = new LobbyScreen();
        screen.setMachine(machine);

        NetController.getInstance().createServer();
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
    }

}
