package br.unb.shooter.collision;

import java.util.HashMap;

import br.unb.shooter.entity.Player;
import br.unb.shooter.entity.Wall;

public class MapCollision {
	private Player player;

	private Float tileWidth;

	private Float tileHeight;

	private Float columns;

	public MapCollision(Float tileWidth, Float tileHeight, Float columns) {
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		this.columns = columns;
	}

	public void setOldPlayerState(Player player) {
		this.player = new Player();

		this.player.setPositionX(player.getPositionX());
		this.player.setPositionY(player.getPositionY());
		this.player.setWidth(player.getWidth());
		this.player.setHeight(player.getHeight());
	}

	public Boolean checkMapCollisionX(HashMap<Integer, Wall> walls, Player player) {
		Integer x1 = (int) (player.getPositionX() / tileWidth);
		Integer y1 = (int) (player.getPositionY() / tileHeight);
		Integer x2 = (int) ((player.getPositionX() + player.getWidth()) / tileWidth);
		Integer y2 = (int) ((player.getPositionY() + player.getHeight()) / tileHeight);

		for (int i = y1; i <= y2; i++) {
			if (walls.containsKey(x1 + i * columns) || walls.containsKey(x2 + i * columns)) {
				return true;
			}
		}

		return false;
	}

	public Boolean checkMapCollisionY(HashMap<Integer, Wall> walls, Player player) {
		Integer x1 = (int) (player.getPositionX() / tileWidth);
		Integer y1 = (int) (player.getPositionY() / tileHeight);
		Integer x2 = (int) ((player.getPositionX() + player.getWidth()) / tileWidth);
		Integer y2 = (int) ((player.getPositionY() + player.getHeight()) / tileHeight);

		for (int i = x1; i <= x2; i++) {
			if (walls.containsKey(i + y1 * columns) || walls.containsKey(i + y2 * columns)) {
				return true;
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
