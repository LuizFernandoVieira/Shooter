package br.unb.shooter.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Weapon {

    private Entity owner;
    private Vector2 position;

    /**
     * Constructor.
     */
    public Weapon() {
        owner = null;
        position = new Vector2();
    }

    public abstract void update(float deltaTime);

    public abstract void render(SpriteBatch batch);

    public Entity getOwner() {
        return owner;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setOwner(Entity owner) {
        this.owner = owner;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

}
