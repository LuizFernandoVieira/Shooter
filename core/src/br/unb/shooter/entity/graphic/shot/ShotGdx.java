package br.unb.shooter.entity.graphic.shot;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import br.unb.shooter.entity.Shot;

public class ShotGdx {
    private ShotTexture shotTexture;

    public void initGraphics() {
        shotTexture = new ShotTexture();
    }

    public void draw(SpriteBatch batch, Shot shot) {
        batch.draw(shotTexture.getFrame(), shot.getX() - (shot.getWidth() / 2), shot.getY() - (shot.getHeight() / 2),
                shot.getWidth(), shot.getHeight());
    }
}
