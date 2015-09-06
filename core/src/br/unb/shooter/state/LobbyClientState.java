package br.unb.shooter.state;

import java.io.IOException;

import com.esotericsoftware.kryonet.Client;

import br.unb.shooter.controller.GameController;
import br.unb.shooter.controller.NetController;
import br.unb.shooter.net.ClientListener;
import br.unb.shooter.net.message.ConnectMessage;
import br.unb.shooter.screen.LobbyScreen;

public class LobbyClientState implements IState {

    private LobbyScreen screen;

    @Override
    public void create(StateMachine machine) {
        screen = new LobbyScreen();
        screen.setMachine(machine);

        Client client = new Client();

        client.start();

        try {
            client.connect(5000, "127.0.0.1", 5000);
        } catch (IOException e) {
            // TODO: handle exception properly
            System.out.println("Erro");
        }

        ClientListener listener = new ClientListener();

        client.addListener(listener);

        ConnectMessage msg = new ConnectMessage();

        msg.setName(GameController.getInstance().getPlayer().getName());

        client.sendTCP(msg.toString());

        NetController.getInstance().setClient(client);
    }

    @Override
    public void update() {
    }

    @Override
    public void draw() {
        screen.draw();
    }

    @Override
    public void dispose() {
    }

}
