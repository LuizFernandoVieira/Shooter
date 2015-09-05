package br.unb.shooter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import br.unb.shooter.state.StateMachine;

public class Shooter extends ApplicationAdapter {

	private StateMachine machine;

	@Override
	public void create() {
		machine = new StateMachine();

		machine.create();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		machine.update();

		machine.draw();
	}
}
