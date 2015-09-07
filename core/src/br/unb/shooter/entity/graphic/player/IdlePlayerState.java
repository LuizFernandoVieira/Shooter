package br.unb.shooter.entity.graphic.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import br.unb.shooter.entity.Player;

public class IdlePlayerState implements IPlayerState {

    private float stateTime;

    private TextureAtlas idleAtlas;
    private TextureRegion[] idleFrames;
    private Animation idleAnimation;

    private TextureRegion currentFrame;

    @Override
    public void create() {
        idleAtlas = new TextureAtlas(Gdx.files.internal("player_idle.pack"));

        idleFrames = new TextureRegion[2];
        for (int i = 0; i < 2; i++) {
            idleFrames[i] = idleAtlas.findRegion("player_idle_" + i);
            idleFrames[i].getTexture().setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        }

        idleAnimation = new Animation(0.5f, idleFrames);

        stateTime = 0;
    }

    @Override
    public void draw(SpriteBatch batch, Player player) {
        batch.draw(currentFrame, player.getPositionX(), player.getPositionY(), player.getWidth(), player.getHeight());
    }

    @Override
    public void update(float deltaTime, Player player) {
        stateTime += deltaTime;
        currentFrame = idleAnimation.getKeyFrame(stateTime, true);
        player.setWidth(currentFrame.getRegionWidth());
        player.setHeight(currentFrame.getRegionHeight());
    }

}
