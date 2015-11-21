package br.unb.shooter.entity.graphic.enemy;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import br.unb.shooter.entity.Enemy;

public class WalkingEnemyState implements IEnemyState {

    private float stateTime;

    private TextureRegion[] walkingFrames;

    private TextureRegion currentFrame;

    private Animation walkingAnimation;

    @Override
    public void create() {
        walkingAnimation = new Animation(0.1f, walkingFrames);
        stateTime = 0;
    }

    @Override
    public void draw(SpriteBatch batch, Enemy enemy) {
        batch.draw(currentFrame, enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());
    }

    @Override
    public void update(float deltaTime, Enemy enemy) {
        stateTime += deltaTime;
        currentFrame = walkingAnimation.getKeyFrame(stateTime, true);
        enemy.setWidth((float) currentFrame.getRegionWidth());
        enemy.setHeight((float) currentFrame.getRegionHeight());
    }

    public TextureRegion[] getWalkingFrames() {
        return walkingFrames;
    }

    public void setWalkingFrames(TextureRegion[] walkingFrames) {
        this.walkingFrames = walkingFrames;
    }

}
