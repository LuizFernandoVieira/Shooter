package br.unb.shooter.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player extends Entity {

    public enum State {
        IDLE, WALKING
    }

    private State state;
    private float stateTime;

    private Vector2 velocity;
    private float damping;
    private boolean facingRight;
    private int health;

    TextureAtlas idleAtlas;
    TextureAtlas walkingAtlas;
    TextureRegion[] idleFrames;
    TextureRegion[] walkingFrames;
    Animation idleAnimation;
    Animation walkingAnimation;
    TextureRegion currentFrame;

    private int width;
    private int height;

    /**
     * Constructor.
     * 
     * @param position position
     */
    public Player(Vector2 position) {
        super(position);

        state = State.IDLE;
        stateTime = 0;

        velocity = new Vector2();
        damping = 0.9f;
        facingRight = true;
        health = 100;

        initGraphics();

        width = currentFrame.getRegionWidth();
        height = currentFrame.getRegionHeight();
    }

    private void initGraphics() {
        idleAtlas = new TextureAtlas(Gdx.files.internal("player_idle.pack"));
        walkingAtlas = new TextureAtlas(Gdx.files.internal("player_walking.pack"));

        idleFrames = new TextureRegion[2];
        for (int i = 0; i < 2; i++) {
            idleFrames[i] = idleAtlas.findRegion("player_idle_" + i);
            idleFrames[i].getTexture().setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        }

        walkingFrames = new TextureRegion[2];
        for (int i = 0; i < 2; i++) {
            walkingFrames[i] = walkingAtlas.findRegion("player_walking_" + i);
            walkingFrames[i].getTexture().setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        }

        idleAnimation = new Animation(0.5f, idleFrames);
        walkingAnimation = new Animation(0.5f, walkingFrames);
        currentFrame = idleAnimation.getKeyFrame(stateTime, true);
    }

    @Override
    public void update(float deltaTime) {
        stateTime += deltaTime;
        handleVelocity(deltaTime);
    }

    private void handleVelocity(float deltaTime) {
        velocity.x *= damping;
        velocity.y *= damping;
        position.add(velocity.cpy().scl(deltaTime));
    }

    @Override
    public void render(SpriteBatch batch) {
        switch (state) {
            case IDLE:
                if (velocity.x >= -0.8 && velocity.x <= 0.8) {
                    if (velocity.y >= -0.8 && velocity.y <= 0.8) {
                        currentFrame = idleAnimation.getKeyFrame(stateTime, true);
                    } else {
                        currentFrame = walkingAnimation.getKeyFrame(stateTime, true);
                    }
                }
                break;
            case WALKING:
                currentFrame = walkingAnimation.getKeyFrame(stateTime, true);
                break;
            default:
                break;
        }
        batch.draw(currentFrame, getPosition().x, getPosition().y, getWidth(), getHeight());
    }

    @Override
    public boolean isDead() {
        if (health < 0) {
            return true;
        }
        return false;
    }

    public State getState() {
        return state;
    }

    public float getStateTime() {
        return stateTime;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public float getDamping() {
        return damping;
    }

    public boolean getFacingRight() {
        return facingRight;
    }

    public int getHealth() {
        return health;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setStateTime(float stateTime) {
        this.stateTime = stateTime;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public void setDamping(float damping) {
        this.damping = damping;
    }

    public void setFacingRight(boolean facingRight) {
        this.facingRight = facingRight;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
