package br.unb.shooter.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import br.unb.shooter.controller.GameController;
import br.unb.shooter.entity.graphic.player.PlayerLibgdx;
import br.unb.shooter.input.ShooterInputProcessor;
import br.unb.shooter.util.Constants;

public class GameScreen extends Screen {

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;

    private PlayerLibgdx playerLibgdx;

    private Table table;

    private Label labelFps;

    /**
     * Constructor.
     */
    public GameScreen() {
        super();

        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new ExtendViewport(Constants.CAMERA_WIDTH, Constants.CAMERA_HEIGHT, camera);

        playerLibgdx = new PlayerLibgdx();

        playerLibgdx.initGraphics();

        Gdx.input.setInputProcessor(new ShooterInputProcessor());

        table = new Table();
        table.setWidth(600);
        table.setHeight(600);

        getStage().addActor(table);

        labelFps = new Label("", getSkin());

        table.add(labelFps);
    }

    /**
     * Renders game screen.
     */
    public void draw() {
        super.draw();

        batch.begin();
        playerLibgdx.draw(batch, GameController.getInstance().getPlayer());
        batch.end();
    }

    /**
     * Update state.
     */
    public void update() {
        playerLibgdx.update(GameController.getInstance().getPlayer(), Gdx.graphics.getDeltaTime());

        labelFps.setText(Gdx.graphics.getFramesPerSecond() + " fps");
    }

    public void dispose() {
        batch.dispose();
    }

    public void resize() {
        viewport.update(Constants.CAMERA_WIDTH, Constants.CAMERA_HEIGHT);
    }

}
