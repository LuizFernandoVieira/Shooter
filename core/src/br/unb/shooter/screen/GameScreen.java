package br.unb.shooter.screen;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import br.unb.shooter.controller.GameController;
import br.unb.shooter.controller.GdxController;
import br.unb.shooter.debug.DebugGdx;
import br.unb.shooter.entity.Player;
import br.unb.shooter.entity.Shot;
import br.unb.shooter.input.GameInputProcessor;
import br.unb.shooter.map.Map;
import br.unb.shooter.movement.Movement;
import br.unb.shooter.util.Constants;

public class GameScreen extends Screen {

	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Viewport viewport;

	private DebugGdx debugGdx;

	private Map map;

	// private MapCollision mapCollision;

	private Movement movement;

	/**
	 * Constructor.
	 */
	public GameScreen() {
		super();
	}

	@Override
	public void create() {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		viewport = new ExtendViewport(Constants.CAMERA_WIDTH, Constants.CAMERA_HEIGHT, camera);

		GdxController.getInstance().getPlayerGdx().initGraphics();

		GdxController.getInstance().getWeaponGdx().initGraphics();

		GdxController.getInstance().getMarkGdx().initGraphics();

		GdxController.getInstance().getShotGdx().initGraphics();

		Gdx.input.setInputProcessor(new GameInputProcessor());

		Gdx.input.setCursorImage(GdxController.getInstance().getMarkGdx().getPixmap(), 16, 13);

		map = new Map(camera);

		// mapCollision = new MapCollision(map.getTileWidth(),
		// map.getTileHeight(), map.getRows());

		movement = new Movement();

		movement.setCamera(camera);
		movement.setPlayer(GameController.getInstance().getPlayer());
		movement.setMapWidth(map.getCols() * map.getTileWidth());
		movement.setMapHeight(map.getRows() * map.getTileHeight());
		movement.setMapX(0f);
		movement.setMapY(0f);
		movement.setMovementBoxHeight(300f);
		movement.setMovementBoxWidth(300f);
		movement.setMovementBoxX(150f);
		movement.setMovementBoxY(150f);
		movement.setMapCols(map.getCols());
		movement.setMapRows(map.getRows());
		movement.setMapTileHeight(map.getTileHeight());
		movement.setMapTileWidth(map.getTileWidth());
		movement.setWalls(GameController.getInstance().getWallsMap());
	}

	/**
	 * Updates the screen.
	 */
	@Override
	public void update() {
		// Updates the mouse.
		GameController.getInstance().getPlayer().setFacing(GameController.getInstance().getMouseX(),
				GameController.getInstance().getMouseY());

		// Updates the player.
		Player player = GameController.getInstance().getPlayer();
		movement.setPlayer(player);
		movement.update();

		GdxController.getInstance().getPlayerGdx().update(player, Gdx.graphics.getDeltaTime());
		if (player.getIsShooting()) {
			GameController.getInstance().createShot(player);
		}

		// Updates shots.
		List<Integer> ids = new ArrayList<Integer>();
		for (Shot shot : GameController.getInstance().getShotsMap().values()) {
			shot.update();
			if (shot.getFinish()) {
				ids.add(shot.getId());
				GameController.getInstance().getRemovedShots().add(shot.getId());
			}
		}
		for (Integer id : ids) {
			GameController.getInstance().getShotsMap().remove(id);
		}

		// Updates map.
		map.update();
	}

	/**
	 * Renders game screen.
	 */
	@Override
	public void draw() {
		super.draw();

		map.draw();

		batch.begin();
		for (Player player : GameController.getInstance().getPlayersMap().values()) {
			GdxController.getInstance().getPlayerGdx().draw(batch, player);
			GdxController.getInstance().getWeaponGdx().draw(batch, player.getWeapon());
		}
		for (Shot shot : GameController.getInstance().getShotsMap().values()) {
			GdxController.getInstance().getShotGdx().draw(batch, shot);
		}
		batch.end();

	}

	public void dispose() {
		batch.dispose();
		debugGdx.dispose();
	}

	public void resize() {
		viewport.update(Constants.CAMERA_WIDTH, Constants.CAMERA_HEIGHT);
	}

}