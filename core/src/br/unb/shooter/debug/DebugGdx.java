package br.unb.shooter.debug;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

import br.unb.shooter.controller.GameController;
import br.unb.shooter.entity.Player;
import br.unb.shooter.util.Constants;

public class DebugGdx {

    private ShapeRenderer shapeRenderer;
    private Stage stage;
    private Skin skin;
    private boolean drawGrid = true;
    private boolean drawCircles = true;
    private boolean drawRectangles = true;

    private String text;

    private Label label;

    private Integer mouseX;
    private Integer mouseY;

    public DebugGdx() {
        shapeRenderer = new ShapeRenderer();
    }

    public DebugGdx(Stage stage, Skin skin) {
        this.shapeRenderer = new ShapeRenderer();

        this.stage = stage;

        Table table = new Table();
        table.setWidth(Constants.CAMERA_WIDTH);
        table.setHeight(Constants.CAMERA_HEIGHT);
        this.stage.addActor(table);

        label = new Label(text, skin);

        table.add(label).align(Align.topLeft);
    }

    public void update(Player player, Integer mouseX, Integer mouseY) {
        text = "Player x: " + player.getPositionX() + " y: " + player.getPositionY() + " Mouse x: " + mouseX + " y: "
                + mouseY;
        label.setText(text);
        this.mouseX = mouseX;
        this.mouseY = mouseY;
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
        shapeRenderer.begin(ShapeType.Line);
        shapeRenderer.setColor(Color.ORANGE);
        shapeRenderer.circle(mouseX, mouseY, 10);
        shapeRenderer.end();
    }

    private void drawRectangles() {
        shapeRenderer.begin(ShapeType.Line);
        Player player = GameController.getInstance().getPlayer();
        float x = player.getPositionX();
        float y = player.getPositionY();
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(x, (Constants.CAMERA_HEIGHT - y) - player.getHeight(), player.getWidth(),
                player.getHeight());
        shapeRenderer.end();
    }

    public Skin getSkin() {
        return skin;
    }

}
