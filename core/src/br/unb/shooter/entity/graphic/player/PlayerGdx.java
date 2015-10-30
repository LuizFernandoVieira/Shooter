package br.unb.shooter.entity.graphic.player;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import br.unb.shooter.entity.Player;

public class PlayerGdx {

    private HashMap<Integer, IPlayerState> stateMap;
    private IdlePlayerState idleRightState;
    private IdlePlayerState idleLeftState;
    private WalkingPlayerState walkingRightState;
    private WalkingPlayerState walkingLeftState;

    private PlayerTexture texture;

    private Boolean canDraw = false;

    /**
     * Constructor.
     */
    public PlayerGdx() {
        idleRightState = new IdlePlayerState();
        idleLeftState = new IdlePlayerState();
        walkingRightState = new WalkingPlayerState();
        walkingLeftState = new WalkingPlayerState();
    }

    /**
     * Init player graphics.
     */
    public void initGraphics() {
        texture = new PlayerTexture();
        idleRightState.setCurrentFrame(texture.getIdleRightFrame());
        idleLeftState.setCurrentFrame(texture.getIdleLeftFrame());
        walkingRightState.setWalkingFrames(texture.getWalkingRightFrames());
        walkingLeftState.setWalkingFrames(texture.getWalkingLeftFrames());
        idleRightState.create();
        idleLeftState.create();
        walkingRightState.create();
        walkingLeftState.create();
    }

    /**
     * Update player.
     * 
     * @param player Player
     */
    public void update(Player player, float deltaTime) {
        if (player.getIsMoving()) {
            if (player.getFacing() == 0) {
                stateMap.put(player.getId(), walkingRightState);
            }
            if (player.getFacing() == 1) {
                stateMap.put(player.getId(), walkingLeftState);
            }
        } else {
            if (player.getFacing() == 0) {
                stateMap.put(player.getId(), idleRightState);
            }
            if (player.getFacing() == 1) {
                stateMap.put(player.getId(), idleLeftState);
            }
        }

        stateMap.get(player.getId()).update(deltaTime, player);

        canDraw = true;
    }

    public void draw(SpriteBatch batch, Player player) {
        if (canDraw) {
            stateMap.get(player.getId()).draw(batch, player);
        }
    }

    public HashMap<Integer, IPlayerState> getStateMap() {
        return stateMap;
    }

    public void setStateMap(HashMap<Integer, IPlayerState> stateMap) {
        this.stateMap = stateMap;
    }

    public IdlePlayerState getDefaultState() {
        return idleRightState;
    }
}
