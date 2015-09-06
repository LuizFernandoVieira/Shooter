package br.unb.shooter.net;

import br.unb.shooter.screen.BrowserScreen;

public class ClientListener extends ShooterListener {
    private BrowserScreen screen;

    public BrowserScreen getScreen() {
        return screen;
    }

    public void setScreen(BrowserScreen screen) {
        this.screen = screen;
    }

}
