package br.unb.shooter.state;

import br.unb.shooter.screen.StartScreen;

/**
 * Start state.
 * 
 * @author brunobernardi
 *
 */
public class StartState implements IState {

    /**
     * Start screen.
     */
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

    /**
     * @return the screen
     */
    public final StartScreen getScreen() {
        return screen;
    }

    /**
     * @param screen
     *            the screen to set
     */
    public final void setScreen(final StartScreen screen) {
        this.screen = screen;
    }

}
