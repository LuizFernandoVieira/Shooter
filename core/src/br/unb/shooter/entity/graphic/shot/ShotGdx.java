package br.unb.shooter.entity.graphic.shot;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import br.unb.shooter.controller.GameController;
import br.unb.shooter.entity.Shot;

public class ShotGdx {
    private ShotTexture shotTexture;

    public void initGraphics() {
        shotTexture = new ShotTexture();
    }

    public void draw(SpriteBatch batch, Shot shot) {
        Float mapX = GameController.getInstance().getMovement().getMap().getPositionX();
        Float mapY = GameController.getInstance().getMovement().getMap().getPositionY();
        batch.draw(shotTexture.getFrame(), shot.getPositionX() - mapX, shot.getPositionY() - mapY, shot.getWidth(),
                shot.getHeight());
    }
}
