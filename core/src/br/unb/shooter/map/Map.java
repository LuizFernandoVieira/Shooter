package br.unb.shooter.map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import br.unb.shooter.controller.GameController;
import br.unb.shooter.entity.Wall;

public class Map {

    private TiledMap tiledMap;

    private TmxMapLoader tiledLoader;

    private OrthogonalTiledMapRenderer renderer;

    private MapObjects mapObjects;

    private TiledMapTileLayer mapWalls;

    public void create() {
        tiledLoader = new TmxMapLoader();

        tiledMap = tiledLoader.load("tiledmap.tmx");

        renderer = new OrthogonalTiledMapRenderer(tiledMap, 1);

        mapObjects = tiledMap.getLayers().get("objects").getObjects();

        mapWalls = (TiledMapTileLayer) tiledMap.getLayers().get("foreground");

        for (int i = 0; i < mapWalls.getWidth(); i++) {
            for (int j = 0; j < mapWalls.getHeight(); j++) {
                Cell cell = mapWalls.getCell(i, j);
                if (cell != null) {
                    GameController.getInstance()
                            .addWall(new Wall(i * mapWalls.getTileWidth(), j * mapWalls.getTileHeight(),
                                    Float.valueOf(mapWalls.getTileWidth()).intValue(),
                                    Float.valueOf(mapWalls.getTileHeight()).intValue()));
                }
            }
        }

    }

    public void draw(OrthographicCamera camera) {
        camera.setToOrtho(false);
        renderer.setView(camera);
        renderer.render();
        camera.setToOrtho(true);
    }

}
