package br.unb.shooter.map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Map {

    private TiledMap tiledMap;

    private TmxMapLoader tiledLoader;

    private OrthogonalTiledMapRenderer renderer;

    public void create() {
        tiledLoader = new TmxMapLoader();

        tiledMap = tiledLoader.load("sewers.tmx");

        renderer = new OrthogonalTiledMapRenderer(tiledMap, 1);
    }

    public void draw(OrthographicCamera camera) {
        renderer.setView(camera);
        renderer.render();
    }

}
