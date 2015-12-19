package br.unb.shooter.entity.graphic.shot;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import br.unb.shooter.util.Constants;

public class ShotTexture {

    private Texture texture;

    private TextureRegion frame;

    public ShotTexture() {
        texture = new Texture(Gdx.files.internal("bubblesbullet.png"));

        frame = new TextureRegion(texture, 0, 0, Constants.SHOT_WIDTH.intValue(), Constants.SHOT_HEIGHT.intValue());
    }

    public TextureRegion getFrame() {
        return frame;
    }
}
