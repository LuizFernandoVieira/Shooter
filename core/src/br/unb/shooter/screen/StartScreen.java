package br.unb.shooter.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

import br.unb.shooter.state.CutsceneState;

public class StartScreen extends Screen {

    private Texture img;

    private SpriteBatch batch;

    private long startTime;

    private long deltaTime;

    public void create() {
        batch = new SpriteBatch();

        img = new Texture(Gdx.files.internal("logo.png"));

        startTime = TimeUtils.millis();
    }

    public void update() {
        deltaTime = TimeUtils.timeSinceMillis(startTime);

        if (deltaTime > 5000L) {
            endTimer();
        }
    }

    public void draw() {
        batch.begin();

        batch.draw(img, 0, 0);

        batch.end();
    }

    public void endTimer() {
        getMachine().changeState(new CutsceneState());
    }

}
