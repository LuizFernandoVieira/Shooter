package br.unb.shooter.collision;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import br.unb.shooter.controller.GdxController;
import br.unb.shooter.entity.Player;

public class PlayerCollision {

    private Player player;

    private Player oldPlayer;

    private MapCollision collision;

    public PlayerCollision() {
        player = new Player();
        oldPlayer = new Player();
        collision = new MapCollision();
    }

    public void saveOldPosition() {
        oldPlayer.setX(player.getX());
        oldPlayer.setY(player.getY());
    }

    public void update() {
        TiledMapTileLayer foreground = GdxController.getInstance().getMapGdx().getForeground();

        if (collision.checkMapCollisionX(foreground, player)) {
            Float x = player.getX();
            player.setX(oldPlayer.getX());
            if (collision.checkMapCollisionY(foreground, player)) {
                player.setX(x);
            }
        }

        if (collision.checkMapCollisionY(foreground, player)) {
            player.setY(oldPlayer.getY());
        }

        if (player.getX() < 0) {
            player.setX(oldPlayer.getX());
        }

        if (player.getY() < 0) {
            player.setY(oldPlayer.getY());
        }

        Float mapHeight = foreground.getTileHeight() * foreground.getHeight();
        Float mapWidth = foreground.getTileWidth() * foreground.getWidth();

        if ((player.getX() + player.getWidth()) > mapWidth) {
            player.setX(oldPlayer.getX());
        }

        if ((player.getY() + player.getHeight()) > mapHeight) {
            player.setY(oldPlayer.getY());
        }

    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getOldPlayer() {
        return oldPlayer;
    }

    public void setOldPlayer(Player oldPlayer) {
        this.oldPlayer = oldPlayer;
    }

    public MapCollision getCollision() {
        return collision;
    }

    public void setCollision(MapCollision collision) {
        this.collision = collision;
    }

}
