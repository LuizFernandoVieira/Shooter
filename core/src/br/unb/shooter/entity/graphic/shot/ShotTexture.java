package br.unb.shooter.entity.graphic.shot;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ShotTexture {

    private Texture texture;

    private TextureRegion frame;

    public ShotTexture() {
        texture = new Texture(Gdx.files.internal("bubblesbullet.png"));

        frame = new TextureRegion(texture, 0, 0, 12, 12);
    }

    public TextureRegion getFrame() {
        return frame;
    }
}
