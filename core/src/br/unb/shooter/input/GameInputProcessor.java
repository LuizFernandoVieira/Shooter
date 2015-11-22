package br.unb.shooter.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;

import br.unb.shooter.controller.GameController;
import br.unb.shooter.entity.Player;

public class GameInputProcessor implements InputProcessor, ControllerListener {

	@Override
	public boolean keyDown(int keycode) {
		Player player = GameController.getInstance().getPlayer();

		if (keycode == Keys.W) {
			player.setMoveUp(true);
		}
		if (keycode == Keys.A) {
			player.setMoveLeft(true);
		}
		if (keycode == Keys.S) {
			player.setMoveDown(true);
		}
		if (keycode == Keys.D) {
			player.setMoveRight(true);
		}

		player.setMovingState();

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		Player player = GameController.getInstance().getPlayer();

		if (keycode == Keys.W) {
			player.setMoveUp(false);
		}
		if (keycode == Keys.A) {
			player.setMoveLeft(false);
		}
		if (keycode == Keys.S) {
			player.setMoveDown(false);
		}
		if (keycode == Keys.D) {
			player.setMoveRight(false);
		}

		player.setMovingState();

		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		GameController.getInstance().getPlayer().setIsShooting(true);
		GameController.getInstance().getPlayer().setTargetX(Float.valueOf(screenX));
		GameController.getInstance().getPlayer().setTargetY(Float.valueOf(screenY));
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		GameController.getInstance().setMouseX(Float.valueOf(screenX));
		GameController.getInstance().setMouseY(Float.valueOf(screenY));
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void connected(Controller controller) {
		Gdx.app.log("XBOX", "connected");
	}

	@Override
	public void disconnected(Controller controller) {
		Gdx.app.log("XBOX", "connected");
	}

	@Override
	public boolean buttonDown(Controller controller, int buttonCode) {
		Gdx.app.log("XBOX", Integer.toString(buttonCode));
		return false;
	}

	@Override
	public boolean buttonUp(Controller controller, int buttonCode) {
		Gdx.app.log("XBOX", Integer.toString(buttonCode));
		return false;
	}

	@Override
	public boolean axisMoved(Controller controller, int axisCode, float value) {
		Gdx.app.log("XBOX", "axis: " + Integer.toString(axisCode) + " value: " + value);
		return false;
	}

	@Override
	public boolean povMoved(Controller controller, int povCode, PovDirection value) {
		Gdx.app.log("XBOX", "pov: " + Integer.toString(povCode) + " value: " + value);
		return false;
	}

	@Override
	public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
		Gdx.app.log("XBOX", "slider: " + Integer.toString(sliderCode) + " value: " + value);
		return false;
	}

	@Override
	public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
		Gdx.app.log("XBOX", "slider: " + Integer.toString(sliderCode) + " value: " + value);
		return false;
	}

	@Override
	public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
		return false;
	}

}
