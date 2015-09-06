package br.unb.shooter.state;

import br.unb.shooter.screen.GameScreen;

public class GameState implements IState {

    private GameScreen screen;

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

    public GameScreen getScreen() {
        return screen;
    }

    public void setScreen(GameScreen screen) {
        this.screen = screen;
    }

}
