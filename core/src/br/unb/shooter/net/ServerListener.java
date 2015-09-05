package br.unb.shooter.net;

import com.esotericsoftware.kryonet.Connection;

import br.unb.shooter.screen.HostScreen;

public class ServerListener extends ShooterListener {
    private HostScreen screen;

    public void connected(Connection c) {
        System.out.println("Conectou");
    }

    public void disconnected(Connection c) {
        System.out.println("Desconectou");
    }

    public HostScreen getScreen() {
        return screen;
    }

    public void setScreen(HostScreen screen) {
        this.screen = screen;
    }
}
