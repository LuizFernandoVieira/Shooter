package br.unb.shooter.net.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.unb.shooter.controller.GameController;
import br.unb.shooter.controller.GdxController;
import br.unb.shooter.entity.Player;
import br.unb.shooter.util.Constants;

public class ServerUpdateLobbyMessage extends Message {

    private String serverName;

    private Integer playersLength;

    private List<Player> players;

    /**
     * Constructor.
     */
    public ServerUpdateLobbyMessage() {
        super();
        this.id = MessageEnum.SERVER_UPDATE_LOBBY.getId();
    }

    /**
     * Constructor.
     * 
     * @param message
     */
    public ServerUpdateLobbyMessage(String message) {
        super();
        this.id = MessageEnum.SERVER_UPDATE_LOBBY.getId();
        translate(message);
    }

    /**
     * Constructor.
     * 
     * @param serverName Server's name
     * @param playersMap Player's map
     */
    public ServerUpdateLobbyMessage(String serverName, HashMap<Integer, Player> playersMap) {
        this.id = MessageEnum.SERVER_UPDATE_LOBBY.getId();
        this.serverName = serverName;
        this.playersLength = playersMap.size();
        this.players = new ArrayList<Player>();
        this.players.addAll(playersMap.values());
    }

    private void translate(String message) {
        String[] slices = message.split(Constants.SPACE);

        this.id = slices[0];

        this.serverName = slices[1];

        this.playersLength = Integer.valueOf(slices[2]);

        if (players == null) {
            players = new ArrayList<Player>();
        }

        int offset = 3;

        for (int i = 0; i < playersLength; i++) {
            Player player = new Player();

            player.setId(Integer.valueOf(slices[offset + 0]));
            player.setName(slices[offset + 1]);

            offset += 2;

            this.players.add(player);
        }
    }

    @Override
    public String toString() {
        String message = "";

        message = this.id + Constants.SPACE + this.serverName + Constants.SPACE + this.playersLength;

        for (Player player : players) {
            message += (Constants.SPACE + player.getId() + Constants.SPACE + player.getName());
        }

        return message;
    }

    @Override
    public void execute() {
        GameController.getInstance().setServerName(this.serverName);

        if (GameController.getInstance().getPlayersMap() == null) {
            GameController.getInstance().setPlayersMap(new HashMap<Integer, Player>());
        }

        for (Player player : players) {
            GameController.getInstance().getPlayersMap().put(player.getId(), player);
            if (player.getName().equals(GameController.getInstance().getPlayer().getName())) {
                GameController.getInstance().setPlayer(player);
            }
            GdxController.getInstance().addPlayer(player);
        }
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public Integer getPlayersLength() {
        return playersLength;
    }

    public void setPlayersLength(Integer playersLength) {
        this.playersLength = playersLength;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

}
