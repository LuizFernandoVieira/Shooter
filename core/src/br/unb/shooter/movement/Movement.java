package br.unb.shooter.movement;

import java.util.HashMap;

import com.badlogic.gdx.graphics.OrthographicCamera;

import br.unb.shooter.collision.MapCollision;
import br.unb.shooter.entity.Player;
import br.unb.shooter.entity.Wall;

public class Movement {

	private Player player;

	private OrthographicCamera camera;

	private Float movementBoxX;

	private Float movementBoxY;

	private Float movementBoxWidth;

	private Float movementBoxHeight;

	private Float mapX;

	private Float mapY;

	private Float mapWidth;

	private Float mapHeight;

	private Float mapTileHeight;

	private Float mapTileWidth;

	private HashMap<Integer, Wall> walls;

	private MapCollision mapCollision;

	private Float mapCols;

	private Float mapRows;

	public Movement() {
		mapCollision = new MapCollision(mapTileWidth, mapTileHeight, mapCols);
	}

	public void update() {
		mapCollision.setOldPlayerState(player);
		// if player is inside movementBox or
		// if player is out of movement box and scroll reaches boundaries
		player.update();
		if (player.getPositionX() < movementBoxX || player.getPositionX() > (movementBoxX + movementBoxWidth)
				|| player.getPositionY() < movementBoxY || player.getPositionY() > (movementBoxY + movementBoxHeight)) {
			player.setPositionY(mapCollision.getPlayer().getPositionY());
			player.setPositionX(mapCollision.getPlayer().getPositionX());
		}

		// if
		// (mapCollision.checkMapCollisionX(GameController.getInstance().getWallsMap(),
		// player)) {
		// Float x = player.getPositionX();
		// player.setPositionX(mapCollision.getPlayer().getPositionX());
		// if
		// (mapCollision.checkMapCollisionY(GameController.getInstance().getWallsMap(),
		// player)) {
		// player.setPositionX(x);
		// }
		// }
		// if
		// (mapCollision.checkMapCollisionY(GameController.getInstance().getWallsMap(),
		// player)) {
		// player.setPositionY(mapCollision.getPlayer().getPositionY());
		// }
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public void setCamera(OrthographicCamera camera) {
		this.camera = camera;
	}

	public Float getMovementBoxX() {
		return movementBoxX;
	}

	public void setMovementBoxX(Float movementBoxX) {
		this.movementBoxX = movementBoxX;
	}

	public Float getMovementBoxY() {
		return movementBoxY;
	}

	public void setMovementBoxY(Float movementBoxY) {
		this.movementBoxY = movementBoxY;
	}

	public Float getMovementBoxWidth() {
		return movementBoxWidth;
	}

	public void setMovementBoxWidth(Float movementBoxWidth) {
		this.movementBoxWidth = movementBoxWidth;
	}

	public Float getMovementBoxHeight() {
		return movementBoxHeight;
	}

	public void setMovementBoxHeight(Float movementBoxHeight) {
		this.movementBoxHeight = movementBoxHeight;
	}

	public Float getMapX() {
		return mapX;
	}

	public void setMapX(Float mapX) {
		this.mapX = mapX;
	}

	public Float getMapY() {
		return mapY;
	}

	public void setMapY(Float mapY) {
		this.mapY = mapY;
	}

	public Float getMapWidth() {
		return mapWidth;
	}

	public void setMapWidth(Float mapWidth) {
		this.mapWidth = mapWidth;
	}

	public Float getMapHeight() {
		return mapHeight;
	}

	public void setMapHeight(Float mapHeight) {
		this.mapHeight = mapHeight;
	}

	public HashMap<Integer, Wall> getWalls() {
		return walls;
	}

	public void setWalls(HashMap<Integer, Wall> walls) {
		this.walls = walls;
	}

	public Float getMapCols() {
		return mapCols;
	}

	public void setMapCols(Float mapCols) {
		this.mapCols = mapCols;
	}

	public Float getMapTileHeight() {
		return mapTileHeight;
	}

	public void setMapTileHeight(Float mapTileHeight) {
		this.mapTileHeight = mapTileHeight;
	}

	public Float getMapTileWidth() {
		return mapTileWidth;
	}

	public void setMapTileWidth(Float mapTileWidth) {
		this.mapTileWidth = mapTileWidth;
	}

	public Float getMapRows() {
		return mapRows;
	}

	public void setMapRows(Float mapRows) {
		this.mapRows = mapRows;
	}

}
