package br.unb.shooter.entity.graphic.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class EnemyTexture {

    private static final Integer WIDTH = 37;

    private static final Integer HEIGHT = 55;

    private Texture textureWalking;

    private Texture textureIdle;

    private TextureRegion[] walkingRight;

    private TextureRegion[] walkingLeft;

    private TextureRegion[] idleRight;

    private TextureRegion[] idleLeft;

    public EnemyTexture() {
        textureWalking = new Texture(Gdx.files.internal("enemy.png"));

        textureIdle = new Texture(Gdx.files.internal("enemy.png"));

        walkingRight = new TextureRegion[8];

        walkingLeft = new TextureRegion[8];

        idleRight = new TextureRegion[4];

        idleLeft = new TextureRegion[4];

        for (int i = 0; i < 8; i++) {
            walkingRight[i] = new TextureRegion(textureWalking, i * WIDTH, 0, WIDTH, HEIGHT);
        }

        for (int i = 0; i < 8; i++) {
            walkingLeft[i] = new TextureRegion(textureWalking, i * WIDTH, 0, WIDTH, HEIGHT);
            walkingLeft[i].flip(true, false);
        }

        for (int i = 0; i < 4; i++) {
            idleRight[i] = new TextureRegion(textureIdle, i* WIDTH, 0, WIDTH, HEIGHT);
        }

        for (int i = 0; i < 4; i++) {
            idleLeft[i] = new TextureRegion(textureIdle, i* WIDTH, 0, WIDTH, HEIGHT);
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
