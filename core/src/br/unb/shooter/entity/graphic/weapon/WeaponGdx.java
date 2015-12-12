package br.unb.shooter.entity.graphic.weapon;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import br.unb.shooter.entity.Weapon;

public abstract class WeaponGdx {
    public abstract void initGraphics();

    public abstract void draw(SpriteBatch batch, Weapon weapon);

}