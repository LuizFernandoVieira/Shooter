package br.unb.shooter.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;

import br.unb.shooter.entity.Enemy;
import br.unb.shooter.util.Constants;

public class HealthBar extends Actor {

    private ShapeRenderer shape;
    static private boolean projectionMatrixSet;

    public HealthBar() {
        shape = new ShapeRenderer();
        projectionMatrixSet = false;
    }

    public void draw(Batch batch, Enemy enemy) {
        batch.end();

        if (!projectionMatrixSet) {
            shape.setProjectionMatrix(batch.getProjectionMatrix());
        }

        shape.begin(ShapeType.Filled);
        shape.setColor(Color.BLACK);
        shape.rect(enemy.getX() + Constants.BEAR_HEALTH_OFFSET_X, enemy.getY() + Constants.BEAR_HEALTH_OFFSET_Y,
                Constants.BEAR_HEALTH_WIDTH, Constants.BEAR_HEALTH_HEIGHT);

        if (enemy.getHealth() > 0) {
            shape.setColor(Color.RED);
            shape.rect(enemy.getX() + Constants.BEAR_HEALTH_OFFSET_X, enemy.getY() + Constants.BEAR_HEALTH_OFFSET_Y,
                    (int) (Constants.BEAR_HEALTH_WIDTH * enemy.getHealth()) / Constants.BEAR_HEALTH,
                    Constants.BEAR_HEALTH_HEIGHT);
        }

        shape.end();

        batch.begin();
    }

}
