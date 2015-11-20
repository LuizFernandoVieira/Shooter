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
import br.unb.shooter.entity.Camera;
import br.unb.shooter.entity.Enemy;
import br.unb.shooter.entity.FireWeapon;
import br.unb.shooter.entity.Map;
import br.unb.shooter.entity.Player;
import br.unb.shooter.entity.Shot;
import br.unb.shooter.input.GameInputProcessor;
import br.unb.shooter.movement.Movement;
import br.unb.shooter.util.Constants;

public class GameScreen extends Screen {

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;

    private DebugGdx debugGdx;

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

        GdxController.getInstance().getEnemyGdx().initGraphics();

        GdxController.getInstance().getWeaponGdx().initGraphics();

        GdxController.getInstance().getMarkGdx().initGraphics();

        GdxController.getInstance().getShotGdx().initGraphics();

        GdxController.getInstance().getMapGdx().initGraphics();

        Gdx.input.setInputProcessor(new GameInputProcessor());

        Gdx.input.setCursorImage(GdxController.getInstance().getMarkGdx().getPixmap(), 16, 13);

        movement = GameController.getInstance().getMovement();
        movement.setPlayer(GameController.getInstance().getPlayer());
        Map map = new Map();
        map.setWidth(GdxController.getInstance().getMapGdx().getForeground().getWidth()
                * GdxController.getInstance().getMapGdx().getForeground().getTileWidth());
        map.setHeight(GdxController.getInstance().getMapGdx().getForeground().getHeight()
                * GdxController.getInstance().getMapGdx().getForeground().getTileHeight());
        map.setPositionX(0f);
        map.setPositionY(0f);
        map.setCols((float) GdxController.getInstance().getMapGdx().getForeground().getWidth());
        map.setRows((float) GdxController.getInstance().getMapGdx().getForeground().getHeight());
        map.getTile().setWidth(GdxController.getInstance().getMapGdx().getForeground().getTileWidth());
        map.getTile().setHeight(GdxController.getInstance().getMapGdx().getForeground().getTileHeight());
        movement.setMap(map);
        movement.setCamera(new Camera());
        movement.getCamera().setStartX(300f);
        movement.getCamera().setStartY(300f);
        movement.getCamera().setPositionX(300f);
        movement.getCamera().setPositionY(300f);
        movement.getCamera().setWidth(camera.viewportWidth);
        movement.getCamera().setHeight(camera.viewportHeight);
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
            GameController.getInstance().createShot(player, movement.getMap());
        }
        
        // Updates the weapon
        for (FireWeapon weapon : GameController.getInstance().getWeaponsMap().values()) {     
            weapon.update();
        }

        // Updates the enemies
        for (Enemy enemy : GameController.getInstance().getEnemiesMap().values()) {
            // enemy.update();
            GdxController.getInstance().getEnemyGdx().update(enemy, Gdx.graphics.getDeltaTime());
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
        GdxController.getInstance().getMapGdx().update();

        camera.position.x = movement.getCamera().getPositionX();
        camera.position.y = movement.getCamera().getPositionY();

        camera.update();
    }

    /**
     * Renders game screen.
     */
    @Override
    public void draw() {
        super.draw();

        GdxController.getInstance().getMapGdx().draw(camera);

        batch.begin();
        for (Enemy enemy : GameController.getInstance().getEnemiesMap().values()) {
            GdxController.getInstance().getEnemyGdx().draw(batch, enemy);
        }
        for (Player player : GameController.getInstance().getPlayersMap().values()) {
            GdxController.getInstance().getPlayerGdx().draw(batch, player);
        }
        for (Shot shot : GameController.getInstance().getShotsMap().values()) {
            GdxController.getInstance().getShotGdx().draw(batch, shot);
        }
        for (FireWeapon weapon : GameController.getInstance().getWeaponsMap().values()) {
            GdxController.getInstance().getWeaponGdx().draw(batch, weapon);
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