package br.unb.shooter.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import br.unb.shooter.controller.GameController;
import br.unb.shooter.controller.GdxController;
import br.unb.shooter.debug.DebugGdx;
import br.unb.shooter.entity.Player;
import br.unb.shooter.input.GameInputProcessor;
import br.unb.shooter.util.Constants;

public class GameScreen extends Screen {

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;

    private DebugGdx debugGdx;

    private Table table;

    private Label labelFps;

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

        debugGdx = new DebugGdx();

        GdxController.getInstance().getPlayerGdx().initGraphics();

        GdxController.getInstance().getMarkGdx().initGraphics();

        Gdx.input.setInputProcessor(new GameInputProcessor());

        Gdx.input.setCursorImage(GdxController.getInstance().getMarkGdx().getPixmap(), 6, 6);

        table = new Table();
        table.setWidth(Constants.CAMERA_WIDTH);
        table.setHeight(Constants.CAMERA_HEIGHT);

        getStage().addActor(table);

        labelFps = new Label("", getSkin());

        table.add(labelFps);
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
        }

        labelFps.setText(Gdx.graphics.getFramesPerSecond() + " fps");
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
