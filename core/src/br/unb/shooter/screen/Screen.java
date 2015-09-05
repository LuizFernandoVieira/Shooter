package br.unb.shooter.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Screen {

	private Stage stage;

	public Screen() {
		setStage(new Stage());

		Gdx.input.setInputProcessor(stage);
	}

	public void draw() {
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

}
