package br.unb.shooter.collision;

import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

import br.unb.shooter.entity.Player;

public class MapCollision {
    private Player player;

    public MapCollision() {
    }

    public void setOldPlayerState(Player player) {
        this.player = new Player();

        this.player.setX(player.getX());
        this.player.setY(player.getY());
        this.player.setWidth(player.getWidth());
        this.player.setHeight(player.getHeight());
    }

    public Boolean checkMapCollisionX(TiledMapTileLayer mapLayer, Player player) {
        Integer x1 = (int) (player.getX() / mapLayer.getTileWidth());
        Integer y1 = (int) (player.getY() / mapLayer.getTileHeight());
        Integer x2 = (int) ((player.getX() + player.getWidth()) / mapLayer.getTileWidth());
        Integer y2 = (int) ((player.getY() + player.getHeight()) / mapLayer.getTileHeight());

        Boolean retorno = false;
        for (int i = y1; i <= y2; i++) {
            retorno = checkCellBlocked(x1, i, mapLayer, retorno);
            retorno = checkCellBlocked(x2, i, mapLayer, retorno);
        }

        return retorno;
    }

    public Boolean checkMapCollisionY(TiledMapTileLayer mapLayer, Player player) {
        Integer x1 = (int) (player.getX() / mapLayer.getTileWidth());
        Integer y1 = (int) (player.getY() / mapLayer.getTileHeight());
        Integer x2 = (int) ((player.getX() + player.getWidth()) / mapLayer.getTileWidth());
        Integer y2 = (int) ((player.getY() + player.getHeight()) / mapLayer.getTileHeight());

        Boolean retorno = false;
        for (int i = x1; i <= x2; i++) {
            retorno = checkCellBlocked(i, y1, mapLayer, retorno);
            retorno = checkCellBlocked(i, y2, mapLayer, retorno);
        }

        return retorno;
    }

    private Boolean checkCellBlocked(int x, int y, TiledMapTileLayer mapLayer, Boolean retorno) {
        if (retorno) {
            return true;
        }
        Cell cell = mapLayer.getCell(x, y);
        if (cell != null) {
            TiledMapTile tile = cell.getTile();
            if (tile != null) {
                if (((String) tile.getProperties().get("blocked")).equals("1")) {
                    return true;
                }
            }
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
