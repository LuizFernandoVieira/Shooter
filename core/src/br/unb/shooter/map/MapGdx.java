package br.unb.shooter.map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;

import br.unb.shooter.controller.GameController;
import br.unb.shooter.entity.Enemy;
import br.unb.shooter.entity.Wall;

public class MapGdx {

    private TiledMap tiledMap;

    private TmxMapLoader tiledLoader;

    private OrthogonalTiledMapRenderer renderer;

    private TiledMapTileLayer mapWalls;

    public void initGraphics() {
        tiledLoader = new TmxMapLoader();

        tiledMap = tiledLoader.load("tiledmap.tmx");

        renderer = new OrthogonalTiledMapRenderer(tiledMap, 1);

        mapWalls = (TiledMapTileLayer) tiledMap.getLayers().get("foreground");

        for (int i = 0; i < mapWalls.getWidth(); i++) {
            for (int j = 0; j < mapWalls.getHeight(); j++) {
                Cell cell = mapWalls.getCell(i, j);
                if (cell != null) {
                    GameController.getInstance().addWall(new Wall(i * mapWalls.getTileWidth(),
                            j * mapWalls.getTileHeight(), mapWalls.getTileWidth(), mapWalls.getTileHeight()));
                }
            }
        }

        MapObjects objects = tiledMap.getLayers().get("objects").getObjects();
        for (MapObject object : objects) {
            if( object.getName() != null) {
                String name = object.getName();
                if (name.equals("enemy")) {
                    Enemy enemy = new Enemy();
                    enemy.setPositionX((Float) object.getProperties().get("x"));
                    enemy.setPositionY((Float) object.getProperties().get("y") + (Float) object.getProperties().get("height"));
                    GameController.getInstance().addEnemy(enemy);
                }
            }
        }

    }

    public void update() {
    }

    public void draw(OrthographicCamera camera) {
        renderer.setView(camera);
        renderer.render();
    }

    public TiledMapTileLayer getMapWalls() {
        return mapWalls;
    }

    public void setMapWalls(TiledMapTileLayer mapWalls) {
        this.mapWalls = mapWalls;
    }

}
