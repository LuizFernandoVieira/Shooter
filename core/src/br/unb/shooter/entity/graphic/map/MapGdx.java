package br.unb.shooter.entity.graphic.map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import br.unb.shooter.controller.GameController;
import br.unb.shooter.entity.Enemy;

public class MapGdx {

    private TiledMap tiledMap;

    private TmxMapLoader tiledLoader;

    private OrthogonalTiledMapRenderer renderer;

    private TiledMapTileLayer foreground;

    public void initGraphics() {
        tiledLoader = new TmxMapLoader();

        tiledMap = tiledLoader.load("tiledmap.tmx");

        renderer = new OrthogonalTiledMapRenderer(tiledMap, 1);

        foreground = (TiledMapTileLayer) tiledMap.getLayers().get("foreground");

        MapObjects objects = tiledMap.getLayers().get("objects").getObjects();
        for (MapObject object : objects) {
            if (object.getName() != null) {
                String name = object.getName();
                if (name.equals("enemy")) {
                    Enemy enemy = new Enemy();
                    enemy.setX((Float) object.getProperties().get("x"));
                    enemy.setY((Float) object.getProperties().get("y") + (Float) object.getProperties().get("height"));
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

    public TiledMapTileLayer getForeground() {
        return foreground;
    }

    public void setForeground(TiledMapTileLayer foreground) {
        this.foreground = foreground;
    }

}
