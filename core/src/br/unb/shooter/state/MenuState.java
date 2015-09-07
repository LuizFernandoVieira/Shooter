package br.unb.shooter.state;

import br.unb.shooter.controller.NetController;
import br.unb.shooter.screen.MenuScreen;

public class MenuState implements IState {

    private MenuScreen screen;

    @Override
    public void create(final StateMachine machine) {
        screen = new MenuScreen();
        screen.setMachine(machine);

        NetController.getInstance().setIsMultiplayer(false);
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
        screen.dispose();
    }

}
