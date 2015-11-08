package br.unb.shooter.entity.graphic.weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class FireWeaponTexture extends WeaponTexture {

    public FireWeaponTexture() {
        
        texture = new Texture(Gdx.files.internal("bubblesgun.png"));

        frame = new TextureRegion(texture, 0, 0, 39, 28);
        
    }

}
