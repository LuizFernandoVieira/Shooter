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
import br.unb.shooter.entity.TargetMark;
import br.unb.shooter.gamepad.XBox360Pad;

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
        Gdx.app.log("XBOX", "disconnected");
    }

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {
        Player player = GameController.getInstance().getPlayer();

        // up
        if (buttonCode == 0) {
            player.setMoveUp(true);
        }
        // down
        if (buttonCode == 1) {
            player.setMoveDown(true);
        }
        // left
        if (buttonCode == 2) {
            player.setMoveLeft(true);
        }
        // right
        if (buttonCode == 3) {
            player.setMoveRight(true);
        }
        // shoot
        if (buttonCode == 9) {
            GameController.getInstance().getPlayer().setIsShooting(true);
        }

        player.setMovingState();

        return false;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {
        Player player = GameController.getInstance().getPlayer();

        // up
        if (buttonCode == 0) {
            player.setMoveUp(false);
        }
        // down
        if (buttonCode == 1) {
            player.setMoveDown(false);
        }
        // left
        if (buttonCode == 2) {
            player.setMoveLeft(false);
        }
        // right
        if (buttonCode == 3) {
            player.setMoveRight(false);
        }

        player.setMovingState();

        return false;
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {
        Player player = GameController.getInstance().getPlayer();
        TargetMark targetMark = GameController.getInstance().getTargetMark();

        if (axisCode == XBox360Pad.AXIS_LEFT_X && value > 0.5) {
            player.setMoveRight(true);
        } else if (axisCode == XBox360Pad.AXIS_LEFT_X && value < 0.5) {
            player.setMoveRight(false);
        }
        if (axisCode == XBox360Pad.AXIS_LEFT_X && value < -0.5) {
            player.setMoveLeft(true);
        } else if (axisCode == XBox360Pad.AXIS_LEFT_X && value > -0.5) {
            player.setMoveLeft(false);
        }
        if (axisCode == XBox360Pad.AXIS_LEFT_Y && value > 0.5) {
            player.setMoveDown(true);
        } else if (axisCode == XBox360Pad.AXIS_LEFT_Y && value < 0.5) {
            player.setMoveDown(false);
        }
        if (axisCode == XBox360Pad.AXIS_LEFT_Y && value < -0.5) {
            player.setMoveUp(true);
        } else if (axisCode == XBox360Pad.AXIS_LEFT_Y && value > -0.5) {
            player.setMoveUp(false);
        }

        // Target mark
        if (axisCode == XBox360Pad.AXIS_RIGHT_X && value > 0) {
            targetMark.setMoveRight(true);
            targetMark.setIntensityX(value);
        }
        if (axisCode == XBox360Pad.AXIS_RIGHT_X && value < 0) {
            targetMark.setMoveLeft(true);
            targetMark.setIntensityX(value);
        }
        if (axisCode == XBox360Pad.AXIS_RIGHT_Y && value > 0) {
            targetMark.setMoveDown(true);
            targetMark.setIntensityY(value);
        }
        if (axisCode == XBox360Pad.AXIS_RIGHT_Y && value < 0) {
            targetMark.setMoveUp(true);
            targetMark.setIntensityY(value);
        }

        player.setMovingState();

        return false;
    }

    @Override
    public boolean povMoved(Controller controller, int povCode, PovDirection value) {
        return false;
    }

    @Override
    public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
        return false;
    }

    @Override
    public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
        return false;
    }

    @Override
    public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
        return false;
    }

}
