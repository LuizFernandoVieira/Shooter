package br.unb.shooter.map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import br.unb.shooter.controller.GameController;
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