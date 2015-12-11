package br.unb.shooter.screen;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import br.unb.shooter.collision.PlayerCollision;
import br.unb.shooter.controller.GameController;
import br.unb.shooter.controller.GdxController;
import br.unb.shooter.controller.MusicController;
import br.unb.shooter.controller.NetController;
import br.unb.shooter.debug.DebugGdx;
import br.unb.shooter.entity.Enemy;
import br.unb.shooter.entity.FireWeapon;
import br.unb.shooter.entity.Player;
import br.unb.shooter.entity.Shot;
import br.unb.shooter.input.GameInputProcessor;
import br.unb.shooter.util.Constants;

public class GameScreen extends Screen {

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;

    private DebugGdx debugGdx;

    private PlayerCollision playerCollision;

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
        viewport = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        GdxController.getInstance().getPlayerGdx().initGraphics();

        GdxController.getInstance().getEnemyGdx().initGraphics();

        GdxController.getInstance().getWeaponGdx().initGraphics();

        GdxController.getInstance().getMarkGdx().initGraphics();

        GdxController.getInstance().getShotGdx().initGraphics();

        GdxController.getInstance().getMapGdx().initGraphics();

        GameInputProcessor input = new GameInputProcessor();

        Gdx.input.setInputProcessor(input);

        if (Constants.CONTROLLER) {
            Controllers.addListener(input);
        }

        if (!Constants.CONTROLLER) {
            Gdx.input.setCursorImage(GdxController.getInstance().getMarkGdx().getPixmap(), 16, 13);
        }

        playerCollision = GameController.getInstance().getMovement();
        playerCollision.setPlayer(GameController.getInstance().getPlayer());

        // debugGdx = new DebugGdx();

        MusicController.getInstance().stop();
        MusicController.getInstance().start("tela2intro.wav");
    }

    /**
     * Updates the screen.
     */
    @Override
    public void update() {
        Vector3 mousePosition = new Vector3(GameController.getInstance().getMouseX(),
                GameController.getInstance().getMouseY(), 0);

        camera.unproject(mousePosition);

        GameController.getInstance().getPlayer().saveOldTargetXY();
        if (Constants.CONTROLLER) {
            GameController.getInstance().getPlayer()
                    .setTargetX(GameController.getInstance().getTargetMark().getX() + Constants.TARGET_MARK_WIDTH / 2);
            GameController.getInstance().getPlayer()
                    .setTargetY(GameController.getInstance().getTargetMark().getY() + Constants.TARGET_MARK_HEIGHT / 2);
        } else {
            GameController.getInstance().getPlayer().setTargetX(mousePosition.x);
            GameController.getInstance().getPlayer().setTargetY(mousePosition.y);
        }

        playerCollision.saveOldPosition();

        // Updates players.
        for (Player player : GameController.getInstance().getPlayersMap().values()) {
            player.update();
            GdxController.getInstance().getPlayerGdx().update(player, Gdx.graphics.getDeltaTime());
            if (player.getIsShooting()) {
                GameController.getInstance().createShot(player);
            }
        }

        playerCollision.update();

        // Updates the weapon
        for (FireWeapon weapon : GameController.getInstance().getWeaponsMap().values()) {
            weapon.update();
        }

        // Updates the enemies
        for (Enemy enemy : GameController.getInstance().getEnemiesMap().values()) {
            enemy.update();
            GdxController.getInstance().getEnemyGdx().update(enemy, Gdx.graphics.getDeltaTime());
        }

        // Update targetMark.
        if (Constants.CONTROLLER) {
            GameController.getInstance().getTargetMark().update();
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

        float oldCameraX = camera.position.x;
        float oldCameraY = camera.position.y;

        camera.position.x = GameController.getInstance().getPlayer().getX()
                + GameController.getInstance().getPlayer().getOffsetX();
        camera.position.y = GameController.getInstance().getPlayer().getY()
                + GameController.getInstance().getPlayer().getOffsetY();

        if (camera.position.x < (camera.viewportWidth / 2)) {
            camera.position.x = oldCameraX;
        }
        if (camera.position.y < (camera.viewportHeight / 2)) {
            camera.position.y = oldCameraY;
        }

        float mapWidth = GdxController.getInstance().getMapGdx().getForeground().getTileWidth()
                * GdxController.getInstance().getMapGdx().getForeground().getWidth();
        float mapHeight = GdxController.getInstance().getMapGdx().getForeground().getTileHeight()
                * GdxController.getInstance().getMapGdx().getForeground().getHeight();

        if (camera.position.x < (camera.viewportWidth / 2)) {
            camera.position.x = oldCameraX;
        }
        if (camera.position.y < (camera.viewportHeight / 2)) {
            camera.position.y = oldCameraY;
        }
        if (camera.position.x > (mapWidth - (camera.viewportWidth / 2))) {
            camera.position.x = oldCameraX;
        }
        if (camera.position.y > (mapHeight - (camera.viewportHeight / 2))) {
            camera.position.y = oldCameraY;
        }

        camera.update();

        if (NetController.getInstance().getIsServer()) {
            // Check collision shots and enemies
            for (Enemy enemy : GameController.getInstance().getEnemiesMap().values()) {
                for (Shot shot : GameController.getInstance().getShotsMap().values()) {
                    Boolean collisionX = false;
                    Boolean collisionY = false;
                    if (enemy.getX() < (shot.getX() + shot.getWidth())
                            && (enemy.getX() + enemy.getWidth()) > shot.getX()) {
                        collisionX = true;
                    }
                    if (enemy.getY() < (shot.getY() + shot.getHeight())
                            && (enemy.getY() + enemy.getHeight()) > shot.getY()) {
                        collisionY = true;
                    }
                    if (collisionX && collisionY) {
                        shot.setFinish(true);
                    }
                }
            }
        }

        // debugGdx.update(player, GameController.getInstance().getMouseX(),
        // GameController.getInstance().getMouseY());

        if (!MusicController.getInstance().getMusic().isPlaying()) {
            MusicController.getInstance().start("tela2loop.wav");
            MusicController.getInstance().getMusic().setLooping(true);
        }

    }

    /**
     * Renders game screen.
     */
    @Override
    public void draw() {
        super.draw();

        GdxController.getInstance().getMapGdx().draw(camera);

        batch.setProjectionMatrix(camera.combined);
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
        if (Constants.CONTROLLER) {
            GdxController.getInstance().getMarkGdx().draw(batch, GameController.getInstance().getTargetMark());
        }
        batch.end();

        // debugGdx.draw(camera);

    }

    public void dispose() {
        batch.dispose();
        debugGdx.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

}