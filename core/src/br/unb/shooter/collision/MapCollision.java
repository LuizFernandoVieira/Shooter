package br.unb.shooter.collision;

import java.util.HashMap;

import br.unb.shooter.entity.Player;
import br.unb.shooter.entity.Wall;

public class MapCollision {
    private Player player;

    private Integer tileWidth;

    private Integer tileHeight;

    public MapCollision(Integer tileWidth, Integer tileHeight) {
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
    }

    public void setOldPlayerState(Player player) {
        this.player = new Player();

        this.player.setPositionX(player.getPositionX());
        this.player.setPositionY(player.getPositionY());
        this.player.setWidth(player.getWidth());
        this.player.setHeight(player.getHeight());
    }

    public Boolean checkMapCollision(HashMap<Integer, Wall> walls, Player player) {
        Integer x1 = (int) (player.getPositionX() / tileWidth);
        Integer y1 = (int) (player.getPositionY() / tileHeight);
        Integer x2 = (int) ((player.getPositionX() + player.getWidth()) / tileWidth);
        Integer y2 = (int) ((player.getPositionY() + player.getHeight()) / tileHeight);
        Integer x3 = (int) ((player.getPositionX() + player.getWidth() / 2) / tileWidth);
        Integer y3 = (int) ((player.getPositionY() + player.getWidth() / 2) / tileWidth);

        Boolean v1 = walls.containsKey(x1 + y1 * 50);
        Boolean v2 = walls.containsKey(x1 + y2 * 50);
        Boolean v3 = walls.containsKey(x2 + y1 * 50);
        Boolean v4 = walls.containsKey(x2 + y2 * 50);
        Boolean v5 = walls.containsKey(x1 + y3 * 50);
        Boolean v6 = walls.containsKey(x2 + y3 * 50);
        Boolean v7 = walls.containsKey(x3 + y1 * 50);
        Boolean v8 = walls.containsKey(x3 + y2 * 50);

        if (v1 || v2 || v3 || v4 || v5 || v6 || v7 || v8) {
            return true;
        }

        return false;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

}
