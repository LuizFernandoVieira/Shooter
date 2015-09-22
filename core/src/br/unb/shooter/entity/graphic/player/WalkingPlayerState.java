package br.unb.shooter.entity.graphic.player;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import br.unb.shooter.entity.Player;

public class WalkingPlayerState implements IPlayerState {

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
    public void draw(SpriteBatch batch, Player player) {
        batch.draw(currentFrame, player.getPositionX(), player.getPositionY(), player.getWidth(), player.getHeight());
    }

    @Override
    public void update(float deltaTime, Player player) {
        stateTime += deltaTime;
        currentFrame = walkingAnimation.getKeyFrame(stateTime, true);
        player.setWidth(currentFrame.getRegionWidth());
        player.setHeight(currentFrame.getRegionHeight());
    }

    public TextureRegion[] getWalkingFrames() {
        return walkingFrames;
    }

    public void setWalkingFrames(TextureRegion[] walkingFrames) {
        this.walkingFrames = walkingFrames;
    }

}
