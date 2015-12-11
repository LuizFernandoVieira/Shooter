package br.unb.shooter.entity.graphic.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import br.unb.shooter.util.Constants;

public class EnemyTexture {

    private Texture textureWalking;

    private Texture textureIdle;

    private TextureRegion[] walkingRight;

    private TextureRegion[] walkingLeft;

    private TextureRegion[] idleRight;

    private TextureRegion[] idleLeft;

    public EnemyTexture() {
        textureWalking = new Texture(Gdx.files.internal("bearwalking.png"));

        textureIdle = new Texture(Gdx.files.internal("bearidle.png"));

        walkingRight = new TextureRegion[8];

        walkingLeft = new TextureRegion[8];

        idleRight = new TextureRegion[4];

        idleLeft = new TextureRegion[4];

        
        Integer walkingWidth = Constants.BEAR_WALKING_WIDTH.intValue();
        Integer walkingHeight = Constants.BEAR_WALKING_HEIGHT.intValue();
        Integer idleWidth = Constants.BEAR_IDLE_WIDTH.intValue();
        Integer idleHeight = Constants.BEAR_IDLE_HEIGHT.intValue();
        
        for (int i = 0; i < 8; i++) {
            walkingRight[i] = new TextureRegion(textureWalking, i * walkingWidth, 0, walkingWidth, walkingHeight);
        }

        for (int i = 0; i < 8; i++) {
            walkingLeft[i] = new TextureRegion(textureWalking, i * walkingWidth, 0, walkingWidth, walkingHeight);
            walkingLeft[i].flip(true, false);
        }

        for (int i = 0; i < 4; i++) {
            idleRight[i] = new TextureRegion(textureIdle, i* idleWidth, 0, idleWidth, idleHeight);
        }

        for (int i = 0; i < 4; i++) {
            idleLeft[i] = new TextureRegion(textureIdle, i* idleWidth, 0, idleWidth, idleHeight);
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
