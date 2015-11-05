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

	private Float tileWidth;

	private Float tileHeight;

	private Float cols;

	private Float rows;

	private OrthographicCamera camera;

	public Map(OrthographicCamera camera) {
		this.camera = camera;

		tiledLoader = new TmxMapLoader();

		tiledMap = tiledLoader.load("tiledmap.tmx");

		renderer = new OrthogonalTiledMapRenderer(tiledMap, 1);

		mapWalls = (TiledMapTileLayer) tiledMap.getLayers().get("foreground");

		this.tileWidth = mapWalls.getTileWidth();

		this.tileHeight = mapWalls.getTileHeight();

		this.cols = Float.valueOf(mapWalls.getWidth());

		this.rows = Float.valueOf(mapWalls.getHeight());

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

	public void update() {
		camera.update();
	}

	public void draw() {
		renderer.setView(camera);
		renderer.render();
	}

	public Float getTileWidth() {
		return tileWidth;
	}

	public void setTileWidth(Float tileWidth) {
		this.tileWidth = tileWidth;
	}

	public Float getTileHeight() {
		return tileHeight;
	}

	public void setTileHeight(Float tileHeight) {
		this.tileHeight = tileHeight;
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public void setCamera(OrthographicCamera camera) {
		this.camera = camera;
	}

	public Float getCols() {
		return cols;
	}

	public void setCols(Float cols) {
		this.cols = cols;
	}

	public Float getRows() {
		return rows;
	}

	public void setRows(Float rows) {
		this.rows = rows;
	}

	public TiledMapTileLayer getMapWalls() {
		return mapWalls;
	}

	public void setMapWalls(TiledMapTileLayer mapWalls) {
		this.mapWalls = mapWalls;
	}

}
