package br.unb.shooter.entity.graphic.enemy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import br.unb.shooter.entity.Enemy;

public interface IEnemyState {
    void create();

    void update(float deltaTime, Enemy enemy);

    void draw(SpriteBatch batch, Enemy enemy);
}
