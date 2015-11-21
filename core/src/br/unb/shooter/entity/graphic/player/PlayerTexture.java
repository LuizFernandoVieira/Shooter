package br.unb.shooter.entity.graphic.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PlayerTexture {

    private static final Integer WIDTH = 41;

    private static final Integer HEIGHT = 66;
    
    private static final Integer IDLE_WIDTH = 42;
    
    private static final Integer IDLE_HEIGHT = 64;

    private Texture textureWalking;

    private Texture textureIdle;

    private TextureRegion[] walkingRight;

    private TextureRegion[] walkingLeft;

    private TextureRegion[] idleRight;

    private TextureRegion[] idleLeft;

    public PlayerTexture() {
        textureWalking = new Texture(Gdx.files.internal("bubbleswalking.png"));

        textureIdle = new Texture(Gdx.files.internal("bubblesidle.png"));

        walkingRight = new TextureRegion[8];

        walkingLeft = new TextureRegion[8];

        idleRight = new TextureRegion[8];

        idleLeft = new TextureRegion[8];

        for (int i = 0; i < 8; i++) {
            walkingRight[i] = new TextureRegion(textureWalking, i * WIDTH, 0, WIDTH, HEIGHT);
        }

        for (int i = 0; i < 8; i++) {
            walkingLeft[i] = new TextureRegion(textureWalking, i * WIDTH, 0, WIDTH, HEIGHT);
            walkingLeft[i].flip(true, false);
        }

        for (int i = 0; i < 8; i++) {
            idleRight[i] = new TextureRegion(textureIdle, i * IDLE_WIDTH, 0, IDLE_WIDTH, IDLE_HEIGHT);
        }

        for (int i = 0; i < 8; i++) {
            idleLeft[i] = new TextureRegion(textureIdle, i * IDLE_WIDTH, 0, IDLE_WIDTH, IDLE_HEIGHT);
            idleLeft[i].flip(true, false);
        }
        
    }

    public TextureRegion[] getIdleRightFrame() {
        return idleRight;
    }

    public TextureRegion[] getIdleLeftFrame() {
        return idleLeft;
    }

    public TextureRegion[] getWalkingRightFrames() {
        return walkingRight;
    }

    public TextureRegion[] getWalkingLeftFrames() {
        return walkingLeft;
    }

}
