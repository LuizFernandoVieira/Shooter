package br.unb.shooter.state;

import br.unb.shooter.controller.NetController;
import br.unb.shooter.screen.BrowserScreen;

public class BrowserState implements IState {

    private BrowserScreen screen;

    @Override
    public void create(StateMachine machine) {
        screen = new BrowserScreen();
        screen.setMachine(machine);

        NetController.getInstance().discoverHosts();
    }

    @Override
    public void update() {
        // TODO: Create a time based udate every 5000 ms
        NetController.getInstance().discoverHosts();
    }

    @Override
    public void draw() {
        screen.draw();
    }

    @Override
    public void dispose() {
    }

}
