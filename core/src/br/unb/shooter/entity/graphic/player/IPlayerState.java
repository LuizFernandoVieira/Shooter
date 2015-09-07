package br.unb.shooter.entity.graphic.player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import br.unb.shooter.entity.Player;

public interface IPlayerState {
    void create();

    void update(float deltaTime, Player player);

    void draw(SpriteBatch batch, Player player);
}
