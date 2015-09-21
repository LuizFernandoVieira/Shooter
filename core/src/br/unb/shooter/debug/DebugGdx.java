package br.unb.shooter.debug;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import br.unb.shooter.controller.GameController;
import br.unb.shooter.entity.Player;

public class DebugGdx {

    ShapeRenderer shapeRenderer;
    boolean drawGrid = true;
    boolean drawFunction = true;
    boolean drawCircles = true;
    boolean drawRectangles = true;
    boolean drawPoints = true;
    boolean drawTriangles = true;

    public DebugGdx() {
        shapeRenderer = new ShapeRenderer();
    }

    public void draw(OrthographicCamera camera) {
        shapeRenderer.setProjectionMatrix(camera.combined);
        if (drawGrid) {
            drawGrid();
        }
        if (drawCircles) {
            drawCircles();
        }
        if (drawRectangles) {
            drawRectangles();
        }
    }

    public void dispose() {
        shapeRenderer.dispose();
    }

    private void drawGrid() {
        shapeRenderer.begin(ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        for (int i = 0; i < 50; i++) {
            shapeRenderer.line(0.0f, i * 32.0f, 1024.0f, i * 32.0f);
            shapeRenderer.line(i * 32.0f, 0.0f, i * 32.0f, 1024.0f);
        }
        shapeRenderer.end();
    }

    private void drawCircles() {
    }

    private void drawRectangles() {
        shapeRenderer.begin(ShapeType.Line);
        Player player = GameController.getInstance().getPlayer();
        float x = player.getPositionX();
        float y = player.getPositionY();
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(x, -(y-576), player.getWidth(), player.getHeight());
        shapeRenderer.end();
    }

}
