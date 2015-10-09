package br.unb.shooter.net.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.unb.shooter.controller.GameController;
import br.unb.shooter.entity.Player;
import br.unb.shooter.util.Constants;

public class ServerStartMessage extends Message {

    private List<Player> players;

    private Integer playersLength;

    /**
     * Constructor.
     */
    public ServerStartMessage() {
        super();
        this.id = MessageEnum.SERVER_START.getId();
    }

    /**
     * Constructor.
     * 
     * @param message
     */
    public ServerStartMessage(String message) {
        super();
        this.id = MessageEnum.SERVER_START.getId();
        translate(message);
    }

    /**
     * Constructor.
     * 
     * @param playersMap
     */
    public ServerStartMessage(HashMap<Integer, Player> playersMap) {
        super();
        this.id = MessageEnum.SERVER_START.getId();
        this.playersLength = playersMap.size();
        this.players = new ArrayList<Player>();
        this.players.addAll(playersMap.values());
    }

    private void translate(String message) {
        String[] slices = message.split(Constants.SPACE);

        this.id = slices[0];

        this.playersLength = Integer.valueOf(slices[1]);

        if (players == null) {
            players = new ArrayList<Player>();
        }

        int offset = 2;

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

        message = this.id + Constants.SPACE + this.playersLength;

        for (Player player : players) {
            message += (Constants.SPACE + player.getId() + Constants.SPACE + player.getName());
        }

        return message;
    }

    @Override
    public void execute() {
        if (GameController.getInstance().getPlayersMap() == null) {
            GameController.getInstance().setPlayersMap(new HashMap<Integer, Player>());
        }

        GameController.getInstance().setIsStarted(true);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

}
