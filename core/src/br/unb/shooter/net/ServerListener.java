package br.unb.shooter.net;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryonet.Connection;

import br.unb.shooter.screen.HostScreen;

public class ServerListener extends ShooterListener {
    private HostScreen screen;

    @Override
    public void connected(Connection connection) {
        Gdx.app.log("SERVER_LISTENER", "Client connected.");
    }

    @Override
    public void disconnected(Connection connection) {
        Gdx.app.log("SERVER_LISTENER", "Client disconnected.");
    }

    public HostScreen getScreen() {
        return screen;
    }

    public void setScreen(HostScreen screen) {
        this.screen = screen;
    }
}
