package br.unb.shooter.entity.graphic.player;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import br.unb.shooter.entity.Player;

public class PlayerGdx {

    private HashMap<Integer, IPlayerState> stateMap;
    private IdlePlayerState idleState;
    private WalkingPlayerState walkingState;

    private Boolean canDraw = false;

    /**
     * Constructor.
     */
    public PlayerGdx() {
        idleState = new IdlePlayerState();
        walkingState = new WalkingPlayerState();
    }

    /**
     * Init player graphics.
     */
    public void initGraphics() {
        idleState.create();
        walkingState.create();
    }

    /**
     * Update player.
     * 
     * @param player Player
     */
    public void update(Player player, float deltaTime) {
        if (player.getIsMoving()) {
            stateMap.put(player.getId(), walkingState);
        } else {
            stateMap.put(player.getId(), idleState);
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

    public IdlePlayerState getIdleState() {
        return idleState;
    }

    public void setIdleState(IdlePlayerState idleState) {
        this.idleState = idleState;
    }

    public WalkingPlayerState getWalkingState() {
        return walkingState;
    }

    public void setWalkingState(WalkingPlayerState walkingState) {
        this.walkingState = walkingState;
    }

}
