package br.unb.shooter.entity.graphic.shot;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ShotTexture {

    private Texture texture;

    private TextureRegion frame;

    public ShotTexture() {
        texture = new Texture(Gdx.files.internal("shot.png"));

        frame = new TextureRegion(texture, 13, 13, 6, 6);
    }

    public TextureRegion getFrame() {
        return frame;
    }
}
