package br.unb.shooter.entity.graphic.shot;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import br.unb.shooter.entity.Shot;

public class ShotGdx {
    private ShotTexture shotTexture;

    ParticleEffect waterEffect;

    public void initGraphics() {
        shotTexture = new ShotTexture();

        waterEffect = new ParticleEffect();
        waterEffect.load(Gdx.files.internal("data/water.p"), Gdx.files.internal("data"));

        waterEffect.start();
    }

    public void draw(SpriteBatch batch, Shot shot) {
        batch.draw(shotTexture.getFrame(), shot.getX(), shot.getY(), shot.getWidth(), shot.getHeight());

        // waterEffect.getEmitters().first().setPosition(shot.getPositionX(),
        // shot.getPositionY());
        // waterEffect.getEmitters().first().getScale().setHigh(5, 20);

        waterEffect.update(Gdx.graphics.getDeltaTime());
        // waterEffect.draw(batch);
        if (waterEffect.isComplete())
            waterEffect.reset();
    }
}
