package br.unb.shooter.state;

import br.unb.shooter.controller.NetController;
import br.unb.shooter.screen.BrowseScreen;

public class BrowseState implements IState {

    private BrowseScreen screen;

    @Override
    public void create(StateMachine machine) {
        screen = new BrowseScreen();
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
        screen.dispose();
    }

}
