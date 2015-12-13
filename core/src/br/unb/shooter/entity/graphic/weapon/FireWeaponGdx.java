package br.unb.shooter.entity.graphic.weapon;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import br.unb.shooter.entity.FireWeapon;
import br.unb.shooter.util.Constants;

public class FireWeaponGdx {
    private FireWeaponTexture weaponTexture;

    public void initGraphics() {
        weaponTexture = new FireWeaponTexture();
    }

    public void draw(SpriteBatch batch, FireWeapon fireWeapon) {
        Double rotation = fireWeapon.getAngle();

        batch.draw(weaponTexture.getFrame(), fireWeapon.getX(), fireWeapon.getY(), Constants.WEAPON_ORIGIN_X,
                Constants.WEAPON_ORIGIN_Y, fireWeapon.getWidth(), fireWeapon.getHeight(), 1,
                fireWeapon.getFacing() == 0 ? 1 : -1, rotation.intValue());
    }
}
