package br.unb.shooter.entity.graphic.player;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import br.unb.shooter.entity.Player;

public class IdlePlayerState implements IPlayerState {
    
    private float stateTime;

    private TextureRegion[] idleFrames;

    private TextureRegion currentFrame;

    private Animation idleAnimation;

    @Override
    public void create() {
        idleAnimation = new Animation(0.1f, idleFrames);
        stateTime = 0;
    }

    @Override
    public void draw(SpriteBatch batch, Player player) {
        batch.draw(currentFrame, player.getX(), player.getY(), player.getWidth(), player.getHeight());
    }

    @Override
    public void update(float deltaTime, Player player) {
        stateTime += deltaTime;
        currentFrame = idleAnimation.getKeyFrame(stateTime, true);
        player.setWidth((float) currentFrame.getRegionWidth());
        player.setHeight((float) currentFrame.getRegionHeight());
    }

    public TextureRegion[] getIdleFrames() {
        return idleFrames;
    }

    public void setIdleFrames(TextureRegion[] idleFrames) {
        this.idleFrames = idleFrames;
    }

}
