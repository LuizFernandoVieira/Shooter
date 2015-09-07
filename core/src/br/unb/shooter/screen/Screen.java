package br.unb.shooter.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import br.unb.shooter.state.StateMachine;

public class Screen {

    private Stage stage;

    private Skin skin;

    private StateMachine machine;

    /**
     * Constructor.
     */
    public Screen() {
        setStage(new Stage());

        Gdx.input.setInputProcessor(stage);

        setSkin(new Skin(Gdx.files.internal("uiskin.json")));
    }

    public void draw() {
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    public void dispose() {
        stage.dispose();
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public StateMachine getMachine() {
        return machine;
    }

    public void setMachine(StateMachine machine) {
        this.machine = machine;
    }

}
