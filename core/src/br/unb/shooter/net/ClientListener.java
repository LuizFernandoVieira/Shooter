package br.unb.shooter.net;

import br.unb.shooter.screen.BrowseScreen;

public class ClientListener extends ShooterListener {
    private BrowseScreen screen;

    public BrowseScreen getScreen() {
        return screen;
    }

    public void setScreen(BrowseScreen screen) {
        this.screen = screen;
    }

}
