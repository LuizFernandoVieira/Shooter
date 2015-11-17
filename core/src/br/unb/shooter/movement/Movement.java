package br.unb.shooter.movement;

import br.unb.shooter.entity.Camera;
import br.unb.shooter.entity.Map;
import br.unb.shooter.entity.Player;

public class Movement {

	private static final Float CAMERA_TOP = 1300f;
	private static final Float CAMERA_BOTTOM = 300f;
	private static final Float CAMERA_LEFT = 300f;
	private static final Float CAMERA_RIGHT = 1300f;

	private Player player;

	private Player oldPlayer;

	private Camera camera;

	private Camera oldCamera;

	private Map map;

	public Movement() {
		player = new Player();
		oldPlayer = new Player();
		camera = new Camera();
		oldCamera = new Camera();
	}

	public void update() {
		oldPlayer.setPositionX(player.getPositionX());
		oldPlayer.setPositionY(player.getPositionY());

		player.update();

		oldCamera.setPositionX(camera.getPositionX());
		oldCamera.setPositionY(camera.getPositionY());

		map.setPositionX(camera.getPositionX() - camera.getStartX());
		map.setPositionY(camera.getPositionY() - camera.getStartY());

		camera.setPositionX(player.getPositionX() + player.getOffsetX());
		camera.setPositionY(player.getPositionY() + player.getOffsetY());

		if (player.getPositionX() < 0) {
			player.setPositionX(oldPlayer.getPositionX());
		}

		if (player.getPositionY() < 0) {
			player.setPositionY(oldPlayer.getPositionY());
		}

		if ((player.getPositionX() + player.getWidth()) > map.getWidth()) {
			player.setPositionX(oldPlayer.getPositionX());
		}

		if ((player.getPositionY() + player.getHeight()) > map.getHeight()) {
			player.setPositionY(oldPlayer.getPositionY());
		}

		if ((player.getPositionX() + player.getOffsetX()) < CAMERA_LEFT) {
			player.setScreenX(player.getPositionX());
		}

		if ((player.getPositionY() + player.getOffsetY()) < CAMERA_BOTTOM) {
			player.setScreenY(player.getPositionY());
		}

		if ((player.getPositionX() + player.getOffsetX()) > CAMERA_RIGHT) {
			player.setScreenX(player.getPositionX() - (map.getWidth() - camera.getWidth()));
		}

		if ((player.getPositionY() + player.getOffsetY()) > CAMERA_TOP) {
			player.setScreenY(player.getPositionY() - (map.getHeight() - camera.getHeight()));
		}

		// Fix camera position.
		if (camera.getPositionX() < CAMERA_LEFT) {
			camera.setPositionX(oldCamera.getPositionX());
		}
		if (camera.getPositionY() < CAMERA_BOTTOM) {
			camera.setPositionY(oldCamera.getPositionY());
		}
		if (camera.getPositionX() > CAMERA_RIGHT) {
			camera.setPositionX(oldCamera.getPositionX());
		}
		if (camera.getPositionY() > CAMERA_TOP) {
			camera.setPositionY(oldCamera.getPositionY());
		}
	}

	public static Float getPlayerOffsetX(float playerStartX) {
		return Math.abs(CAMERA_LEFT - playerStartX);
	}

	public static Float getPlayerOffsetY(float playerStartY) {
		return Math.abs(CAMERA_BOTTOM - playerStartY);
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public Player getOldPlayer() {
		return oldPlayer;
	}

	public void setOldPlayer(Player oldPlayer) {
		this.oldPlayer = oldPlayer;
	}

}
