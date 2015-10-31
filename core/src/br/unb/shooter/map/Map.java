package br.unb.shooter.map;

import com.badlogic.gdx.graphics.OrthographicCamera;
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

    private TiledMapTileLayer mapWalls;

    private Integer tileWidth;

    private Integer tileHeight;

    public void create() {
        tiledLoader = new TmxMapLoader();

        tiledMap = tiledLoader.load("tiledmap.tmx");

        renderer = new OrthogonalTiledMapRenderer(tiledMap, 1);

        mapWalls = (TiledMapTileLayer) tiledMap.getLayers().get("foreground");

        this.tileWidth = (int) mapWalls.getTileWidth();

        this.tileHeight = (int) mapWalls.getTileHeight();

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

    public Integer getTileWidth() {
        return tileWidth;
    }

    public void setTileWidth(Integer tileWidth) {
        this.tileWidth = tileWidth;
    }

    public Integer getTileHeight() {
        return tileHeight;
    }

    public void setTileHeight(Integer tileHeight) {
        this.tileHeight = tileHeight;
    }

}
