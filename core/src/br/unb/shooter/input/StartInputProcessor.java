package br.unb.shooter.input;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

import br.unb.shooter.screen.StartScreen;

public class StartInputProcessor implements InputProcessor {

    private StartScreen screen;

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.ESCAPE) {
            screen.pressEscKey();
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public StartScreen getScreen() {
        return screen;
    }

    public void setScreen(StartScreen screen) {
        this.screen = screen;
    }

}
