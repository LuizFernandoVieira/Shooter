package br.unb.shooter.entity.graphic.weapon;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import br.unb.shooter.controller.GameController;
import br.unb.shooter.entity.FireWeapon;

public class FireWeaponGdx {
    private FireWeaponTexture weaponTexture;

    public void initGraphics() {
        weaponTexture = new FireWeaponTexture();
    }

    public void draw(SpriteBatch batch, FireWeapon fireWeapon) {
        Double rotation = fireWeapon.getAngle();

        Float mapX = GameController.getInstance().getMovement().getMap().getPositionX();
        Float mapY = GameController.getInstance().getMovement().getMap().getPositionY();

        if (fireWeapon.getFacing() == 0) {
            batch.draw(weaponTexture.getFrame(), fireWeapon.getPositionX() - mapX, fireWeapon.getPositionY() - mapY, 0,
                    16, fireWeapon.getWidth(), fireWeapon.getHeight(), 1, 1, rotation.intValue());
        } else {
            batch.draw(weaponTexture.getFrame(), fireWeapon.getPositionX() - mapX, fireWeapon.getPositionY() - mapY, 0,
                    16, fireWeapon.getWidth(), fireWeapon.getHeight(), 1, -1, rotation.intValue());
        }
    }
}
