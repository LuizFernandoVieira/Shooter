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

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("ARIBLK.TTF"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 10;
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
        drawWeapon();
        drawShotCreation();

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

    private void drawWeapon() {
        shapeRenderer.begin(ShapeType.Line);

        Player player = GameController.getInstance().getPlayer();

        float offsetX = Constants.WEAPON_OFFSET_FROM_PLAYER_X_FACING_RIGHT;
        float offsetY = Constants.WEAPON_OFFSET_FROM_PLAYER_Y;

        if (player.getFacing() == 0) {
            offsetX = Constants.WEAPON_OFFSET_FROM_PLAYER_X_FACING_RIGHT;
        } else {
            offsetX = Constants.WEAPON_OFFSET_FROM_PLAYER_X_FACING_LEFT;
        }

        Player p = player;

        Float deltaX = 0f;
        Float deltaY = 0f;

        Float playerXCentered = p.getX() + (p.getWidth() / 2);
        Float playerYCentered = p.getY() + (p.getHeight() / 2);

        Float mouseX = p.getTargetX();
        Float mouseY = p.getTargetY();

        deltaX = (mouseX - playerXCentered);
        deltaY = (mouseY - playerYCentered);

        Double angle = Math.atan2(deltaY.doubleValue(), deltaX.doubleValue());

        Double rotation = (Math.toDegrees(angle));

        float x = player.getX() + offsetX;
        float y = player.getY() + offsetY;
        shapeRenderer.setColor(Color.ORANGE);
        shapeRenderer.rect(x, y, Constants.WEAPON_ORIGIN_X, Constants.WEAPON_ORIGIN_Y, Constants.WEAPON_WIDTH,
                Constants.WEAPON_HEIGHT, 1, player.getFacing() == 0 ? 1 : -1, rotation.intValue());
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.circle(x + Constants.WEAPON_ORIGIN_X, y + Constants.WEAPON_ORIGIN_Y, 2);
        shapeRenderer.end();

    }

    public void drawShotCreation() {
        float offsetX = Constants.WEAPON_OFFSET_FROM_PLAYER_X_FACING_RIGHT;
        float offsetY = Constants.WEAPON_OFFSET_FROM_PLAYER_Y;

        if (player.getFacing() == 0) {
            offsetX = Constants.WEAPON_OFFSET_FROM_PLAYER_X_FACING_RIGHT;
        } else {
            offsetX = Constants.WEAPON_OFFSET_FROM_PLAYER_X_FACING_LEFT;
        }

        Player p = player;

        Float originX = p.getX() + offsetX + Constants.WEAPON_ORIGIN_X;
        Float originY = p.getY() + offsetY + Constants.WEAPON_ORIGIN_Y;

        Float mouseX = player.getTargetX();
        Float mouseY = player.getTargetY();

        Float deltaX = (mouseX - originX);
        Float deltaY = (mouseY - originY);

        Double angle = Math.atan2(deltaY.doubleValue(), deltaX.doubleValue());

        Float x = (float) Math.cos(angle.floatValue());
        Float y = (float) Math.sin(angle.floatValue());

        Float calculatedX = originX + x * Constants.SHOT_DISTANCE_FROM_ORIGIN;
        Float calculatedY = originY + y * Constants.SHOT_DISTANCE_FROM_ORIGIN;

        shapeRenderer.begin(ShapeType.Line);
        shapeRenderer.setColor(Color.ORANGE);
        shapeRenderer.circle(calculatedX, calculatedY, 2);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.circle(originX, originY, 39);
        shapeRenderer.end();
    }

    private void drawTrajectory() {
        float offsetX = Constants.WEAPON_OFFSET_FROM_PLAYER_X_FACING_RIGHT;
        float offsetY = Constants.WEAPON_OFFSET_FROM_PLAYER_Y;

        if (player.getFacing() == 0) {
            offsetX = Constants.WEAPON_OFFSET_FROM_PLAYER_X_FACING_RIGHT;
        } else {
            offsetX = Constants.WEAPON_OFFSET_FROM_PLAYER_X_FACING_LEFT;
        }

        Player p = player;

        Float originX = p.getX() + offsetX + Constants.WEAPON_ORIGIN_X;
        Float originY = p.getY() + offsetY + Constants.WEAPON_ORIGIN_Y;

        Float mouseX = player.getTargetX();
        Float mouseY = player.getTargetY();

        Float deltaX = (mouseX - originX);
        Float deltaY = (mouseY - originY);

        Double angle = Math.atan2(deltaY.doubleValue(), deltaX.doubleValue());

        Float x = (float) Math.cos(angle.floatValue());
        Float y = (float) Math.sin(angle.floatValue());

        Float calculatedX = originX + x * Constants.SHOT_DISTANCE_FROM_ORIGIN;
        Float calculatedY = originY + y * Constants.SHOT_DISTANCE_FROM_ORIGIN;

        shapeRenderer.begin(ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.line(calculatedX, calculatedY, mouseX, mouseY);
        shapeRenderer.end();
    }

    private void drawEnemyState(SpriteBatch batch) {
        for (Enemy enemy : GameController.getInstance().getEnemiesMap().values()) {
            IEnemyState state = GdxController.getInstance().getEnemyGdx().getStateMap().get(enemy.getId());

            GlyphLayout glyphLayout = new GlyphLayout();
            String item = state.getClass().getSimpleName();
            glyphLayout.setText(font, item);
            Float fwidth = glyphLayout.width;

            font.draw(batch, item, enemy.getX() + enemy.getWidth() / 2 - fwidth / 2,
                    enemy.getY() + enemy.getHeight() + font.getCapHeight());
        }
    }

}
