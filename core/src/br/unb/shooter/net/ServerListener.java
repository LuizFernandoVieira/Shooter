package br.unb.shooter.net;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import br.unb.shooter.screen.HostScreen;

public class ServerListener extends Listener {
    private HostScreen screen;

    public void connected(Connection c) {
        System.out.println("Conectou");
    }

    public void received(Connection c, Object p) {
        String message = p.toString();

        String prefix = message.substring(0, 2);

        if (prefix.equals("CN")) {
            screen.concat(p.toString());
        }
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
