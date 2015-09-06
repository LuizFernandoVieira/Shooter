package br.unb.shooter.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {

    Vector2 position;

    public Entity(Vector2 position) {
        this.position = position;
    }

    public abstract void update(float deltaTime);

    public abstract void render(SpriteBatch batch);

    public abstract boolean isDead();

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

}
