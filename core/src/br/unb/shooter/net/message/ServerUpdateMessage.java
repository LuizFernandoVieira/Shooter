package br.unb.shooter.net.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.unb.shooter.controller.GameController;
import br.unb.shooter.entity.Player;
import br.unb.shooter.util.Constants;

public class ServerUpdateMessage extends Message {

    private Integer playersLength;

    private List<Player> players;

    public ServerUpdateMessage() {
        this.id = MessageEnum.SERVER_UPDATE.getId();
    }

    public ServerUpdateMessage(String message) {
        this.id = MessageEnum.SERVER_UPDATE.getId();
        translate(message);
    }

    /**
     * Constructor.
     * 
     * @param playersMap Player's map
     */
    public ServerUpdateMessage(HashMap<Integer, Player> playersMap) {
        this.id = MessageEnum.SERVER_UPDATE.getId();
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
            player.setPositionX(Integer.valueOf(slices[offset + 2]));
            player.setPositionY(Integer.valueOf(slices[offset + 3]));

            offset += 4;

            this.players.add(player);
        }
    }

    @Override
    public String toString() {
        String message = "";

        message = this.id + Constants.SPACE + this.playersLength;

        for (Player player : players) {
            message += (Constants.SPACE + player.getId() + Constants.SPACE + player.getName() + Constants.SPACE
                    + player.getPositionX() + Constants.SPACE + player.getPositionY());
        }

        return message;
    }

    @Override
    public void execute() {
        for (Player player : players) {
            Player playerOnClient = GameController.getInstance().getPlayersMap().get(player.getId());
            playerOnClient.setPositionX(player.getPositionX());
            playerOnClient.setPositionY(player.getPositionY());
        }
    }

}
