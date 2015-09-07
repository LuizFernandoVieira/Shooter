package br.unb.shooter.input;

import com.badlogic.gdx.Input.Keys;

import br.unb.shooter.entity.player.Player;

import com.badlogic.gdx.InputProcessor;

public class ShooterInputProcessor implements InputProcessor {

    @Override
    public boolean keyDown(int keycode) {
        Player player = Player.getInstance();

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
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        Player player = Player.getInstance();

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
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // TODO Auto-generated method stub
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
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        // TODO Auto-generated method stub
        return false;
    }

}
