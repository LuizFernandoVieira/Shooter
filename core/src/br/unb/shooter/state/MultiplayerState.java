package br.unb.shooter.state;

import br.unb.shooter.controller.NetController;
import br.unb.shooter.screen.MultiplayerScreen;

public class MultiplayerState implements IState {

    private MultiplayerScreen screen;

    @Override
    public void create(StateMachine machine) {
        screen = new MultiplayerScreen();
        screen.setMachine(machine);

        NetController.getInstance().setIsMultiplayer(true);
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
