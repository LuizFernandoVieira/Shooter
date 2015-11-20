package br.unb.shooter.entity.graphic.shot;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool.PooledEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.particles.values.ScaledNumericValue;
import com.badlogic.gdx.utils.Array;

import br.unb.shooter.controller.GameController;
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
        Float mapX = GameController.getInstance().getMovement().getMap().getPositionX();
        Float mapY = GameController.getInstance().getMovement().getMap().getPositionY();
        batch.draw(shotTexture.getFrame(), shot.getPositionX() - mapX, shot.getPositionY() - mapY, shot.getWidth(),
                shot.getHeight());

//        waterEffect.getEmitters().first().setPosition(shot.getPositionX() - mapX, shot.getPositionY() - mapY);
//        waterEffect.getEmitters().first().getScale().setHigh(5, 20);
        
        waterEffect.update(Gdx.graphics.getDeltaTime());
//        waterEffect.draw(batch);
        if (waterEffect.isComplete())
            waterEffect.reset();
    }
}
