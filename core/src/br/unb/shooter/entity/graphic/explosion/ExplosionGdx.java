package br.unb.shooter.entity.graphic.explosion;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool.PooledEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class ExplosionGdx {

    private ParticleEffectPool bombEffectPool;

    private Array<PooledEffect> effects;

    private ParticleEffect bombEffect;

    public void initGraphics() {
        effects = new Array<PooledEffect>();
        bombEffect = new ParticleEffect();
        bombEffect.load(Gdx.files.internal("explosion.p"), Gdx.files.internal("data"));
        bombEffect.setEmittersCleanUpBlendFunction(false);
        bombEffectPool = new ParticleEffectPool(bombEffect, 1, 2);
    }

    public void create(Float x, Float y) {
        PooledEffect effect = bombEffectPool.obtain();
        effect.setPosition(x, y);
        effects.add(effect);
    }

    public void draw(SpriteBatch batch, Float deltaTime) {
        for (int i = effects.size - 1; i >= 0; i--) {
            PooledEffect effect = effects.get(i);
            effect.draw(batch, deltaTime);
            if (effect.isComplete()) {
                effect.free();
                effects.removeIndex(i);
            }
        }
    }

    public void reset() {
        for (int i = effects.size - 1; i >= 0; i--)
            effects.get(i).free();
        effects.clear();
    }

}
