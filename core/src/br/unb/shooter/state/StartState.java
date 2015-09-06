package br.unb.shooter.state;

import br.unb.shooter.screen.StartScreen;

public class StartState implements IState {

    private StartScreen screen;

    @Override
    public void create(final StateMachine machine) {
    }

    @Override
    public void update() {
    }

    @Override
    public void draw() {
    }

    @Override
    public void dispose() {
    }

    public final StartScreen getScreen() {
        return screen;
    }

    public final void setScreen(final StartScreen screen) {
        this.screen = screen;
    }

}
