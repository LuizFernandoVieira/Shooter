package br.unb.shooter.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import br.unb.shooter.entity.Player;
import br.unb.shooter.net.ClientListener;
import br.unb.shooter.net.ServerListener;
import br.unb.shooter.net.message.ClientConnectMessage;
import br.unb.shooter.net.message.ClientInputMessage;
import br.unb.shooter.net.message.Message;
import br.unb.shooter.net.message.MessageQueue;
import br.unb.shooter.net.message.ServerStartMessage;
import br.unb.shooter.net.message.ServerUpdateLobbyMessage;
import br.unb.shooter.net.message.ServerUpdateMessage;

public class NetController {

    private static final Integer TCP_PORT = 5000;

    private static final Integer UDP_PORT = 6000;

    private Client client;

    private Server server;

    private HashMap<String, InetAddress> ips;

    private String selectedServerIp;

    private Boolean isMultiplayer;

    private Boolean isServer;

    private static NetController instance;

    private LinkedList<Message> clientMessages;

    private Long lastInputTime;

    private MessageQueue messageQueue;

    public NetController() {
        isMultiplayer = false;
        isServer = true;
        clientMessages = new LinkedList<Message>();
        lastInputTime = 0L;
        messageQueue = new MessageQueue();
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
            client.connect(5000, serverIp, TCP_PORT, UDP_PORT);
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
        List<InetAddress> ips = client.discoverHosts(UDP_PORT, 2000);

        if (ips != null && !ips.isEmpty()) {
            if (this.ips == null) {
                this.ips = new HashMap<String, InetAddress>();
            }

            for (InetAddress address : ips) {
                if (!this.ips.containsKey(address.getHostAddress())) {
                    this.ips.put(address.getHostAddress(), address);
                }
            }
        }
    }

    /**
     * Start game on all clients.
     */
    public void startGame() {
        ServerStartMessage msg = new ServerStartMessage(GameController.getInstance().getPlayersMap());

        server.sendToAllTCP(msg.toString());
    }

    /**
     * Update game on all clients.
     */
    public void updateGame() {
        ServerUpdateMessage msg = new ServerUpdateMessage(GameController.getInstance().getPlayersMap(),
                GameController.getInstance().getShotsMap(), lastInputTime,
                GameController.getInstance().getRemovedShots());

        server.sendToAllTCP(msg.toString());

        GameController.getInstance().setRemovedShots(new ArrayList<Integer>());
    }

    /**
     * Send input.
     * 
     * @param player
     */
    public void sendPlayerInput(Player player) {
        ClientInputMessage msg = new ClientInputMessage(player, player.getTargetX(), player.getTargetY());

        clientMessages.add(msg);

        client.sendTCP(msg.toString());
    }

    /**
     * Send messages for update. Client send update to server and vice-versa.
     */
    public void sendMessages() {
        if (isMultiplayer) {
            if (isServer) {
                updateGame();
            }
            if (!isServer) {
                if (GameController.getInstance().getPlayer().getIsChangingState()
                        || GameController.getInstance().getPlayer().getIsShooting()) {
                    sendPlayerInput(GameController.getInstance().getPlayer());
                }
            }
        }
    }

    /**
     * Remove past inputs from client list.
     *
     * @param lastInput
     */
    public void removePastInputs(Long lastInput) {
        if (lastInput != 0) {
            List<Message> removables = new ArrayList<Message>();
            for (Message message : clientMessages) {
                if (message.getTimestamp() < lastInput) {
                    removables.add(message);
                }
            }
            for (Message message : removables) {
                clientMessages.remove(message);
            }
        }
    }

    /**
     * Stop server and listener.
     */
    public void stopServer() {
        server.stop();
    }

    /**
     * Stop client and listener.
     */
    public void stopClient() {
        client.stop();
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

    public HashMap<String, InetAddress> getIps() {
        return ips;
    }

    public void setIps(HashMap<String, InetAddress> ips) {
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

    public Boolean getIsServer() {
        return isServer;
    }

    public void setIsServer(Boolean isServer) {
        this.isServer = isServer;
    }

    public LinkedList<Message> getClientMessages() {
        return clientMessages;
    }

    public void setClientMessages(LinkedList<Message> clientMessages) {
        this.clientMessages = clientMessages;
    }

    public Long getLastInputTime() {
        return lastInputTime;
    }

    public void setLastInputTime(Long lastInputTime) {
        this.lastInputTime = lastInputTime;
    }

    public MessageQueue getMessageQueue() {
        return messageQueue;
    }

    public void setMessageQueue(MessageQueue messageQueue) {
        this.messageQueue = messageQueue;
    }

}
