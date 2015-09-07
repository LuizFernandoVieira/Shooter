package br.unb.shooter.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import br.unb.shooter.net.ClientListener;
import br.unb.shooter.net.ServerListener;
import br.unb.shooter.net.message.ClientConnectMessage;
import br.unb.shooter.net.message.ClientServerNameMessage;
import br.unb.shooter.net.message.ServerUpdateLobbyMessage;

public class NetController {

    private static final Integer TCP_PORT = 5000;

    private static final Integer UDP_PORT = 6000;

    private Client client;

    private Server server;

    private List<InetAddress> ips;

    private String selectedServerIp;

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
    }

    /**
     * Create server.
     */
    public void createServerAndListener() {
        server = new Server();

        try {
            server.bind(TCP_PORT, UDP_PORT);

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
    public void connectClient(String serverIp) {
        try {
            client.connect(5000, serverIp, TCP_PORT);
        } catch (IOException e) {
            return;
        }

        ClientConnectMessage msg = new ClientConnectMessage();

        msg.setName(GameController.getInstance().getPlayer().getName());

        client.sendTCP(msg.toString());
    }

    /**
     * Add client listener.
     */
    public void addClientListener() {
        ClientListener listener = new ClientListener();

        client.addListener(listener);
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
        ips = client.discoverHosts(UDP_PORT, 5000);

        if (ips != null && !ips.isEmpty()) {
            NetController.getInstance().setIps(new ArrayList<InetAddress>());
            for (InetAddress ip : ips) {
                NetController.getInstance().getIps().add(ip);
            }
        }
    }

    /**
     * Ask server names.
     */
    public void askServerNames() {
        try {
            client.sendUDP(
                    new ClientServerNameMessage(this.ips, InetAddress.getLocalHost().getHostAddress()).toString());
        } catch (UnknownHostException e) {
            return;
        }
    }

    /**
     * Validates server ip.
     * 
     * @param ip Server's ip
     * @return Boolean
     */
    public Boolean isServerIp(String ip) {
        try {
            if (InetAddress.getLocalHost().getHostAddress().equals(ip)) {
                return true;
            }
        } catch (UnknownHostException e) {
            return false;
        }
        return false;
    }

    public void tellClientServerName(String clientIp) {

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

    public String getSelectedServerIp() {
        return selectedServerIp;
    }

    public void setSelectedServerIp(String selectedServerIp) {
        this.selectedServerIp = selectedServerIp;
    }

}
