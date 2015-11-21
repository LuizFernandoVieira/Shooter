package br.unb.shooter.movement;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import br.unb.shooter.collision.MapCollision;
import br.unb.shooter.controller.GdxController;
import br.unb.shooter.entity.Player;

public class Movement {

    private Player player;

    private Player oldPlayer;

    private MapCollision collision;

    public Movement() {
        player = new Player();
        oldPlayer = new Player();
        collision = new MapCollision();
    }

    public void update() {
        oldPlayer.setX(player.getX());
        oldPlayer.setY(player.getY());

        player.update();

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

        // TODO: query for map borders
        if (player.getX() < 0) {
            player.setX(oldPlayer.getX());
        }

        if (player.getY() < 0) {
            player.setY(oldPlayer.getY());
        }

        if ((player.getX() + player.getWidth()) > 1600f) {
            player.setX(oldPlayer.getX());
        }

        if ((player.getY() + player.getHeight()) > 1600f) {
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
