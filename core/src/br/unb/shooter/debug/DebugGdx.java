package br.unb.shooter.debug;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import br.unb.shooter.controller.GameController;
import br.unb.shooter.controller.GdxController;
import br.unb.shooter.entity.Enemy;
import br.unb.shooter.entity.Player;
import br.unb.shooter.entity.graphic.enemy.IEnemyState;
import br.unb.shooter.util.Constants;

public class DebugGdx {

    private ShapeRenderer shapeRenderer;
    private boolean drawGrid = true;
    private boolean drawCircles = true;
    private boolean drawRectangles = true;

    private Float mouseX;
    private Float mouseY;

    private Player player;

    private BitmapFont font;

    public DebugGdx() {
        shapeRenderer = new ShapeRenderer();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("ARIAL.TTF"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 12;
        parameter.color = Color.BLACK;
        font = generator.generateFont(parameter);
        generator.dispose();
    }

    public DebugGdx(Stage stage, Skin skin) {
        this.shapeRenderer = new ShapeRenderer();
    }

    public void update(Player player, Float mouseX, Float mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.player = player;
    }

    public void draw(OrthographicCamera camera, SpriteBatch batch) {
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

        batch.begin();
        drawEnemyState(batch);
        batch.end();
    }

    public void dispose() {
        shapeRenderer.dispose();
    }

    private void drawGrid() {
        int height = GdxController.getInstance().getMapGdx().getForeground().getHeight();
        int width = GdxController.getInstance().getMapGdx().getForeground().getWidth();

        shapeRenderer.begin(ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        for (int i = 0; i < 50; i++) {
            shapeRenderer.line(0.0f, i * 32.0f, width * 32.0f, i * 32.0f);
            shapeRenderer.line(i * 32.0f, 0.0f, i * 32.0f, height * 32.0f);
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

    private void drawEnemyState(SpriteBatch batch) {
        for (Enemy enemy : GameController.getInstance().getEnemiesMap().values()) {
            IEnemyState state = GdxController.getInstance().getEnemyGdx().getStateMap().get(enemy.getId());

            GlyphLayout glyphLayout = new GlyphLayout();
            String item = state.getClass().getSimpleName();
            glyphLayout.setText(font, item);
            Float fwidth = glyphLayout.width;

            font.draw(batch, state.getClass().getSimpleName(), enemy.getX() + enemy.getWidth() / 2 - fwidth / 2,
                    enemy.getY() + enemy.getHeight() + font.getCapHeight());
        }
    }

}
