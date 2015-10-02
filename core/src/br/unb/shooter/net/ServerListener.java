package br.unb.shooter.net;

import com.esotericsoftware.kryonet.Connection;

import br.unb.shooter.screen.HostScreen;

public class ServerListener extends ShooterListener {
    private HostScreen screen;

    @Override
    public void connected(Connection connection) {
    }

    @Override
    public void disconnected(Connection connection) {
    }

    public HostScreen getScreen() {
        return screen;
    }

    public void setScreen(HostScreen screen) {
        this.screen = screen;
    }
}
