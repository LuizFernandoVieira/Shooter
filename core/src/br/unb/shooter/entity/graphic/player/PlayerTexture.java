package br.unb.shooter.entity.graphic.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import br.unb.shooter.util.Constants;

public class PlayerTexture {

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
			walkingRight[i] = new TextureRegion(textureWalking, i * Constants.PLAYER_WALKING_WIDTH, 0,
					Constants.PLAYER_WALKING_WIDTH, Constants.PLAYER_WALKING_HEIGHT);
		}

		for (int i = 0; i < 8; i++) {
			walkingLeft[i] = new TextureRegion(textureWalking, i * Constants.PLAYER_WALKING_WIDTH, 0,
					Constants.PLAYER_WALKING_WIDTH, Constants.PLAYER_WALKING_HEIGHT);
			walkingLeft[i].flip(true, false);
		}

		for (int i = 0; i < 8; i++) {
			idleRight[i] = new TextureRegion(textureIdle, i * Constants.PLAYER_IDLE_WIDTH, 0,
					Constants.PLAYER_IDLE_WIDTH, Constants.PLAYER_IDLE_HEIGHT);
		}

		for (int i = 0; i < 8; i++) {
			idleLeft[i] = new TextureRegion(textureIdle, i * Constants.PLAYER_IDLE_WIDTH, 0,
					Constants.PLAYER_IDLE_WIDTH, Constants.PLAYER_IDLE_HEIGHT);
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
