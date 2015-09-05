package br.unb.shooter.net;

import br.unb.shooter.screen.BrowserScreen;

public class ClientListener extends ShooterListener {
    private BrowserScreen screen;

    /**
     * @return the screen
     */
    public BrowserScreen getScreen() {
        return screen;
    }

    /**
     * @param screen
     *            the screen to set
     */
    public void setScreen(BrowserScreen screen) {
        this.screen = screen;
    }

}
