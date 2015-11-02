package br.unb.shooter.entity.graphic.weapon;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import br.unb.shooter.entity.FireWeapon;
import br.unb.shooter.entity.Weapon;

public class WeaponGdx {
    private WeaponTexture weaponTexture;

    public void initGraphics() {
        weaponTexture = new WeaponTexture();
    }

    public void draw(SpriteBatch batch, Weapon weapon) {
        Double rotation = ((FireWeapon) weapon).getAngle();
        System.out.println(rotation);
        batch.draw(weaponTexture.getFrame(), weapon.getPositionX(), weapon.getPositionY(), 0, 0, weapon.getWidth(), weapon.getHeight(), 1, 1, rotation.intValue());
    }
}
