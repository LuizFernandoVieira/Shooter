package br.unb.shooter.entity.graphic.weapon;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import br.unb.shooter.entity.FireWeapon;

public class FireWeaponGdx {
    private FireWeaponTexture weaponTexture;

    public void initGraphics() {
        weaponTexture = new FireWeaponTexture();
    }

    public void draw(SpriteBatch batch, FireWeapon fireWeapon) {
        Double rotation = fireWeapon.getAngle();
        
        if(fireWeapon.getFacing() == 0) {
            batch.draw(weaponTexture.getFrame(), fireWeapon.getPositionX(), fireWeapon.getPositionY(), 0, 0, fireWeapon.getWidth(),
                    fireWeapon.getHeight(), 1, 1, rotation.intValue());
        } else {
            batch.draw(weaponTexture.getFrame(), fireWeapon.getPositionX(), fireWeapon.getPositionY(), 0, 0, fireWeapon.getWidth(),
                    fireWeapon.getHeight(), 1, -1, rotation.intValue());
        }
    }
}
