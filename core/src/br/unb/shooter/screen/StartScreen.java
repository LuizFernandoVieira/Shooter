package br.unb.shooter.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

import br.unb.shooter.input.StartInputProcessor;
import br.unb.shooter.state.StateEventEnum;

public class StartScreen extends Screen {

    private Texture img;

    private SpriteBatch batch;

    private long startTime;

    private long deltaTime;

    private StartInputProcessor input;

    /**
     * Creates the screen.
     */
    @Override
    public void create() {
        batch = new SpriteBatch();

        img = new Texture(Gdx.files.internal("logo.png"));

        startTime = TimeUtils.millis();

        input = new StartInputProcessor();

        input.setScreen(this);

        Gdx.input.setInputProcessor(input);
    }

    /**
     * Updates the screen.
     */
    @Override
    public void update() {
        deltaTime = TimeUtils.timeSinceMillis(startTime);

        if (deltaTime > 5000L) {
            endTimer();
        }
    }

    /**
     * Draws the screen.
     */
    @Override
    public void draw() {
        batch.begin();

        batch.draw(img, 200, 200);

        batch.end();
    }

    /**
     * End timer event.
     */
    public void endTimer() {
        getMachine().handle(StateEventEnum.TIMER_END);
    }

    /**
     * Esc key event.
     */
    public void pressEscKey() {
        getMachine().handle(StateEventEnum.KEY_ESC);
    }

}
