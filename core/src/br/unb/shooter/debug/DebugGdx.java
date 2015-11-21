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

    private Float mouseX;
    private Float mouseY;

    private Player player;

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

    public void update(Player player, Float mouseX, Float mouseY) {
        // text = "Player x: " + player.getPositionX() + " y: " +
        // player.getPositionY() + " Mouse x: " + mouseX + " y: "
        // + (Constants.CAMERA_HEIGHT - mouseY);
        // label.setText(text);
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.player = player;
    }

    public void draw(OrthographicCamera camera) {
        shapeRenderer.setProjectionMatrix(camera.combined);
        if (drawGrid) {
            drawGrid();
        }
        if (drawCircles) {
            drawCircles(camera);
        }
        if (drawRectangles) {
            drawRectangles();
        }
        drawTrajectory();
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

    private void drawCircles(OrthographicCamera camera) {
        shapeRenderer.setProjectionMatrix(camera.projection);
        shapeRenderer.begin(ShapeType.Line);
        shapeRenderer.setColor(Color.ORANGE);
        shapeRenderer.circle(mouseX - 300f, Constants.CAMERA_HEIGHT - mouseY - 300f, 10);
        shapeRenderer.end();
        shapeRenderer.setProjectionMatrix(camera.combined);
    }

    private void drawRectangles() {
        shapeRenderer.begin(ShapeType.Line);
        Player player = GameController.getInstance().getPlayer();
        float x = player.getX();
        float y = player.getY();
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(x, y, player.getWidth(), player.getHeight());
        shapeRenderer.end();
    }

    private void drawTrajectory() {

        Integer xOffset = 0;
        if (player.getFacing() == 0) {
            xOffset = 15;
        } else {
            xOffset = 27;
        }

        Float playerXCentered = player.getX() + xOffset;
        Float playerYCentered = player.getY() + 9 + 11;

        Float mouseX = player.getTargetX();
        Float mouseY = player.getTargetY();

        shapeRenderer.begin(ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.line(playerXCentered, playerYCentered, mouseX, mouseY);
        shapeRenderer.end();
    }

    public Skin getSkin() {
        return skin;
    }

}
