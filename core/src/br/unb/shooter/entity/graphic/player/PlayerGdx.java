package br.unb.shooter.entity.graphic.player;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import br.unb.shooter.entity.Player;

public class PlayerGdx {

    private HashMap<Integer, IPlayerState> stateMap;
    private IdlePlayerState idleUpState;
    private IdlePlayerState idleRightState;
    private IdlePlayerState idleDownState;
    private IdlePlayerState idleLeftState;
    private WalkingPlayerState walkingUpState;
    private WalkingPlayerState walkingRightState;
    private WalkingPlayerState walkingDownState;
    private WalkingPlayerState walkingLeftState;

    private PlayerTexture texture;

    private Boolean canDraw = false;

    /**
     * Constructor.
     */
    public PlayerGdx() {
        idleUpState = new IdlePlayerState();
        idleRightState = new IdlePlayerState();
        idleDownState = new IdlePlayerState();
        idleLeftState = new IdlePlayerState();
        walkingUpState = new WalkingPlayerState();
        walkingRightState = new WalkingPlayerState();
        walkingDownState = new WalkingPlayerState();
        walkingLeftState = new WalkingPlayerState();
    }

    /**
     * Init player graphics.
     */
    public void initGraphics() {
        texture = new PlayerTexture();
        idleUpState.setCurrentFrame(texture.getIdleUpFrame());
        idleRightState.setCurrentFrame(texture.getIdleRightFrame());
        idleDownState.setCurrentFrame(texture.getIdleDownFrame());
        idleLeftState.setCurrentFrame(texture.getIdleLeftFrame());
        walkingUpState.setWalkingFrames(texture.getWalkingUpFrames());
        walkingRightState.setWalkingFrames(texture.getWalkingRightFrames());
        walkingDownState.setWalkingFrames(texture.getWalkingDownFrames());
        walkingLeftState.setWalkingFrames(texture.getWalkingLeftFrames());
        idleUpState.create();
        idleRightState.create();
        idleDownState.create();
        idleLeftState.create();
        walkingUpState.create();
        walkingRightState.create();
        walkingDownState.create();
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
                stateMap.put(player.getId(), walkingUpState);
            }
            if (player.getFacing() == 1) {
                stateMap.put(player.getId(), walkingRightState);
            }
            if (player.getFacing() == 2) {
                stateMap.put(player.getId(), walkingDownState);
            }
            if (player.getFacing() == 3) {
                stateMap.put(player.getId(), walkingLeftState);
            }
        } else {
            if (player.getFacing() == 0) {
                stateMap.put(player.getId(), idleUpState);
            }
            if (player.getFacing() == 1) {
                stateMap.put(player.getId(), idleRightState);
            }
            if (player.getFacing() == 2) {
                stateMap.put(player.getId(), idleDownState);
            }
            if (player.getFacing() == 3) {
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
        return idleUpState;
    }
}
