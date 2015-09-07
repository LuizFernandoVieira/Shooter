package br.unb.shooter.entity.graphic.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import br.unb.shooter.entity.Player;

public class WalkingPlayerState implements IPlayerState {

    private float stateTime;

    private TextureAtlas walkingAtlas;
    private TextureRegion[] walkingFrames;
    private Animation walkingAnimation;

    private TextureRegion currentFrame;

    @Override
    public void create() {
        walkingAtlas = new TextureAtlas(Gdx.files.internal("player_walking.pack"));

        walkingFrames = new TextureRegion[2];
        for (int i = 0; i < 2; i++) {
            walkingFrames[i] = walkingAtlas.findRegion("player_walking_" + i);
            walkingFrames[i].getTexture().setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        }

        walkingAnimation = new Animation(0.5f, walkingFrames);

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

}
