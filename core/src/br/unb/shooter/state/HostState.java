package br.unb.shooter.state;

import br.unb.shooter.screen.HostScreen;

public class HostState implements IState {

    private HostScreen screen;

    @Override
    public void create(StateMachine machine) {
        screen = new HostScreen();
        screen.setMachine(machine);
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
