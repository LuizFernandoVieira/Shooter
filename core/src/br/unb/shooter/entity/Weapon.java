package br.unb.shooter.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Weapon extends Entity{

    private Entity owner;

    /**
     * Constructor.
     */
    public Weapon() {
        owner = null;
        setPositionX(0f);
        setPositionY(0f);
    }

    public abstract void update();

    public abstract void render(SpriteBatch batch);

    public Entity getOwner() {
        return owner;
    }

    public void setOwner(Entity owner) {
        this.owner = owner;
        setPositionX(this.owner.getPositionX());
        setPositionY(this.owner.getPositionY());
    }

}
