package br.unb.shooter.entity.graphic.weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class WeaponTexture {
    
    protected Texture texture;

    protected TextureRegion frame;

    public WeaponTexture() {
        texture = new Texture(Gdx.files.internal("bubblesgun.png"));

        frame = new TextureRegion(texture, 0, 0, 39, 28);
    }

    public TextureRegion getFrame() {
        return frame;
    }
}
