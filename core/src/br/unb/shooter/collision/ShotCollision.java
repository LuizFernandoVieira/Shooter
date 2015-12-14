package br.unb.shooter.collision;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import br.unb.shooter.controller.GdxController;
import br.unb.shooter.entity.Shot;

public class ShotCollision {

    private MapCollision mapCollision;

    public ShotCollision() {
        mapCollision = new MapCollision();
    }

    public Boolean update(Shot shot) {
        Boolean collide = false;

        TiledMapTileLayer foreground = GdxController.getInstance().getMapGdx().getForeground();

        if (mapCollision.checkMapCollision(foreground, shot)) {
            shot.setFinish(true);
            collide = true;
        }

        return collide;
    }

}
