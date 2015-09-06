package br.unb.shooter.state;

import br.unb.shooter.screen.BrowserScreen;

public class BrowserState implements IState {

    private BrowserScreen screen;

    @Override
    public void create(StateMachine machine) {
        screen = new BrowserScreen();
        screen.setMachine(machine);
    }

    @Override
    public void update() {
    }

    @Override
    public void draw() {
        screen.draw();
    }

    @Override
    public void dispose() {
    }

}
