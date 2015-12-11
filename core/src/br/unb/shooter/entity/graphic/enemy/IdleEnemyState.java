package br.unb.shooter.entity.graphic.enemy;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import br.unb.shooter.entity.Enemy;

public class IdleEnemyState implements IEnemyState {
    
    private float stateTime;

    private TextureRegion[] idleFrames;

    private TextureRegion currentFrame;

    private Animation idleAnimation;

    @Override
    public void create() {
        idleAnimation = new Animation(1.5f, idleFrames);
        stateTime = 0;
    }

    @Override
    public void draw(SpriteBatch batch, Enemy enemy) {
        batch.draw(currentFrame, enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());
    }

    @Override
    public void update(float deltaTime, Enemy enemy) {
        stateTime += deltaTime;
        currentFrame = idleAnimation.getKeyFrame(stateTime, true);
        enemy.setWidth((float) currentFrame.getRegionWidth());
        enemy.setHeight((float) currentFrame.getRegionHeight());
    }

    public TextureRegion[] getIdleFrames() {
        return idleFrames;
    }

    public void setIdleFrames(TextureRegion[] idleFrames) {
        this.idleFrames = idleFrames;
    }
    
    public TextureRegion getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(TextureRegion currentFrame) {
        this.currentFrame = currentFrame;
    }

}
