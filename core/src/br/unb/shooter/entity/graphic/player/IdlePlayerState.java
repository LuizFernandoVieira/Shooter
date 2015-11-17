package br.unb.shooter.entity.graphic.player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import br.unb.shooter.entity.Player;

public class IdlePlayerState implements IPlayerState {

    private TextureRegion currentFrame;

    @Override
    public void create() {
    }

    @Override
    public void draw(SpriteBatch batch, Player player) {
        batch.draw(currentFrame, player.getScreenX(), player.getScreenY(), player.getWidth(), player.getHeight());
    }

    @Override
    public void update(float deltaTime, Player player) {
        player.setWidth((float) currentFrame.getRegionWidth());
        player.setHeight((float) currentFrame.getRegionHeight());
    }

    public TextureRegion getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(TextureRegion currentFrame) {
        this.currentFrame = currentFrame;
    }

}
