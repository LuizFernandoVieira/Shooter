package br.unb.shooter.state;

import br.unb.shooter.screen.GameScreen;

public class GameState implements IState {

    private GameScreen screen;

    @Override
    public void create(final StateMachine machine) {
        screen = new GameScreen();
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

    public GameScreen getScreen() {
        return screen;
    }

    public void setScreen(GameScreen screen) {
        this.screen = screen;
    }

}
