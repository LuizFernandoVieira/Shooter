package br.unb.shooter.entity.graphic.healthbar;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import br.unb.shooter.entity.HealthBar;

public class HealthBarGdx {

    private ShapeRenderer shape;

    public HealthBarGdx() {
    }

    public void initGraphics() {
        shape = new ShapeRenderer();
    }

    public void draw(OrthographicCamera camera, HealthBar healthBar) {
        shape.setProjectionMatrix(camera.combined);
        shape.begin(ShapeType.Filled);

        shape.setColor(Color.BLACK);
        shape.rect(healthBar.getX(), healthBar.getY(), healthBar.getMaxWidth(), healthBar.getHeight());

        if (healthBar.getWidth() > 0) {
            shape.setColor(Color.RED);
            shape.rect(healthBar.getX(), healthBar.getY(), healthBar.getWidth(), healthBar.getHeight());
        }

        shape.end();
    }

}
