package br.unb.shooter.state;

import java.io.IOException;

import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import br.unb.shooter.controller.NetController;
import br.unb.shooter.net.ServerListener;
import br.unb.shooter.screen.LobbyScreen;

public class LobbyServerState implements IState {

    private LobbyScreen screen;

    @Override
    public void create(StateMachine machine) {
        screen = new LobbyScreen();
        screen.setMachine(machine);

        Server server = new Server();

        try {
            server.bind(5000);
            server.start();

            Listener listener = new ServerListener();

            server.addListener(listener);
        } catch (IOException e) {
            return;
        }

        NetController.getInstance().setServer(server);
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

}
