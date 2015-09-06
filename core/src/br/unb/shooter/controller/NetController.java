package br.unb.shooter.controller;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Server;

public class NetController {

    private Client client;

    private Server server;

    private static NetController instance;

    /**
     * Create a singleton from NetController.
     * 
     * @return NetController instance
     */
    public static NetController getInstance() {
        if (instance == null) {
            instance = new NetController();
        }
        return instance;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

}
