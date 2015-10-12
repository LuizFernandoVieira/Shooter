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
import br.unb.shooter.util.Constants;

public class GameScreen extends Screen {

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;

    private DebugGdx debugGdx;

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
        camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new ExtendViewport(Constants.CAMERA_WIDTH, Constants.CAMERA_HEIGHT, camera);

        // debugGdx = new DebugGdx(getStage(), getSkin());

        GdxController.getInstance().getPlayerGdx().initGraphics();

        GdxController.getInstance().getMarkGdx().initGraphics();

        GdxController.getInstance().getShotGdx().initGraphics();

        Gdx.input.setInputProcessor(new GameInputProcessor());

        // I didn't understand why 16, 13 to center the mouse cursor
        Gdx.input.setCursorImage(GdxController.getInstance().getMarkGdx().getPixmap(), 16, 13);
    }

    /**
     * Updates the screen.
     */
    @Override
    public void update() {
        GameController.getInstance().getPlayer().setFacing(GameController.getInstance().getMouseX(),
                GameController.getInstance().getMouseY());
        for (Player player : GameController.getInstance().getPlayersMap().values()) {
            player.update();
            GdxController.getInstance().getPlayerGdx().update(player, Gdx.graphics.getDeltaTime());
            if (player.getIsShooting()) {
                GameController.getInstance().createShot(player);
            }
        }
        List<Integer> ids = new ArrayList<Integer>();
        for (Shot shot : GameController.getInstance().getShotsMap().values()) {
            shot.update();
            if (shot.getFinish()) {
                ids.add(shot.getId());
            }
        }
        for (Integer id : ids) {
            GameController.getInstance().getShotsMap().remove(id);
        }
        // debugGdx.update(GameController.getInstance().getPlayer(),
        // GameController.getInstance().getMouseX(),
        // GameController.getInstance().getMouseY());
    }

    /**
     * Renders game screen.
     */
    @Override
    public void draw() {
        super.draw();

        batch.begin();
        for (Player player : GameController.getInstance().getPlayersMap().values()) {
            GdxController.getInstance().getPlayerGdx().draw(batch, player);
        }
        for (Shot shot : GameController.getInstance().getShotsMap().values()) {
            GdxController.getInstance().getShotGdx().draw(batch, shot);
        }
        batch.end();

        // debugGdx.draw(camera);
    }

    public void dispose() {
        batch.dispose();
        debugGdx.dispose();
    }

    public void resize() {
        viewport.update(Constants.CAMERA_WIDTH, Constants.CAMERA_HEIGHT);
    }

}
