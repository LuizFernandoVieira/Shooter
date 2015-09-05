package br.unb.shooter.state;

import br.unb.shooter.screen.MenuScreen;

/**
 * 
 * @author brunobernardi
 *
 */
public class MenuState implements IState {

    private MenuScreen screen;

    @Override
    public void create(final StateMachine machine) {
        screen = new MenuScreen();
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
