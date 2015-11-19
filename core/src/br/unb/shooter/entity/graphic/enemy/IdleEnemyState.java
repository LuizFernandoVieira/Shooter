package br.unb.shooter.entity.graphic.enemy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import br.unb.shooter.controller.GameController;
import br.unb.shooter.entity.Enemy;

public class IdleEnemyState implements IEnemyState {

    private TextureRegion currentFrame;

    @Override
    public void create() {
    }

    @Override
    public void draw(SpriteBatch batch, Enemy enemy) {
        Float mapX = GameController.getInstance().getMovement().getMap().getPositionX();
        Float mapY = GameController.getInstance().getMovement().getMap().getPositionY();
        batch.draw(currentFrame, enemy.getPositionX() - mapX, enemy.getPositionY() - mapY, enemy.getWidth(),
                enemy.getHeight());
    }

    @Override
    public void update(float deltaTime, Enemy enemy) {
        enemy.setWidth((float) currentFrame.getRegionWidth());
        enemy.setHeight((float) currentFrame.getRegionHeight());
    }

    public TextureRegion getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(TextureRegion currentFrame) {
        this.currentFrame = currentFrame;
    }

}
