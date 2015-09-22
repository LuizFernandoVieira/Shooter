package br.unb.shooter.entity.graphic.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PlayerTexture {

    private static final Integer WIDTH = 32;

    private static final Integer HEIGHT = 32;

    private Texture texture;

    private TextureRegion[][] regions;

    public PlayerTexture() {
        texture = new Texture(Gdx.files.internal("spritesheet.png"));

        regions = new TextureRegion[8][12];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 12; j++) {
                regions[i][j] = new TextureRegion(texture, i * HEIGHT, j * WIDTH, WIDTH, HEIGHT);
            }
        }
    }

    public TextureRegion getIdleUpFrame() {
        return regions[1][3];
    }

    public TextureRegion getIdleRightFrame() {
        return regions[1][2];
    }

    public TextureRegion getIdleDownFrame() {
        return regions[1][0];
    }

    public TextureRegion getIdleLeftFrame() {
        return regions[1][1];
    }

    public TextureRegion[] getWalkingUpFrames() {
        TextureRegion[] frames = new TextureRegion[3];
        frames[0] = regions[1][3];
        frames[1] = regions[0][3];
        frames[2] = regions[2][3];
        return frames;
    }

    public TextureRegion[] getWalkingRightFrames() {
        TextureRegion[] frames = new TextureRegion[3];
        frames[0] = regions[1][2];
        frames[1] = regions[0][2];
        frames[2] = regions[2][2];
        return frames;
    }

    public TextureRegion[] getWalkingDownFrames() {
        TextureRegion[] frames = new TextureRegion[3];
        frames[0] = regions[1][0];
        frames[1] = regions[0][0];
        frames[2] = regions[2][0];
        return frames;
    }

    public TextureRegion[] getWalkingLeftFrames() {
        TextureRegion[] frames = new TextureRegion[3];
        frames[0] = regions[1][1];
        frames[1] = regions[0][1];
        frames[2] = regions[2][1];
        return frames;
    }
}
