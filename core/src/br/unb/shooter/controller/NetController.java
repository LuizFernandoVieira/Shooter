package br.unb.shooter.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import br.unb.shooter.net.ClientListener;
import br.unb.shooter.net.ServerListener;
import br.unb.shooter.net.message.ClientConnectMessage;
import br.unb.shooter.net.message.ServerUpdateLobbyMessage;

public class NetController {

    private Client client;

    private Server server;

    private Integer tcpPort = 5000;

    private Integer udpPort = 6000;

    private List<InetAddress> ips;

    private Boolean isMultiplayer;

    private static NetController instance;

    public NetController() {
        isMultiplayer = false;
    }

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

    /**
     * Create client.
     */
    public void createClient() {
        client = new Client();

        client.start();

        try {
            client.connect(5000, "127.0.0.1", tcpPort);
        } catch (IOException e) {
            // TODO: handle exception properly
            System.out.println("Erro");
        }

        ClientListener listener = new ClientListener();

        client.addListener(listener);
    }

    /**
     * Create server.
     */
    public void createServer() {
        server = new Server();

        try {
            server.bind(5000);
            server.start();

            Listener listener = new ServerListener();

            server.addListener(listener);
        } catch (IOException e) {
            return;
        }

    }

    /**
     * Connect client.
     */
    public void connectClient() {
        ClientConnectMessage msg = new ClientConnectMessage();

        msg.setName(GameController.getInstance().getPlayer().getName());

        client.sendTCP(msg.toString());
    }

    /**
     * Update all clients lobby.
     */
    public void updateLobby() {
        ServerUpdateLobbyMessage msg = new ServerUpdateLobbyMessage(GameController.getInstance().getServerName(),
                GameController.getInstance().getPlayersMap());

        server.sendToAllTCP(msg.toString());
    }

    /**
     * Discover hosts on client.
     */
    public void discoverHosts() {
        // TODO: Implement the discover host feature

        // ips = client.discoverHosts(udpPort, 5000);
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

    public List<InetAddress> getIps() {
        return ips;
    }

    public void setIps(List<InetAddress> ips) {
        this.ips = ips;
    }

    public Boolean getIsMultiplayer() {
        return isMultiplayer;
    }

    public void setIsMultiplayer(Boolean isMultiplayer) {
        this.isMultiplayer = isMultiplayer;
    }

}
