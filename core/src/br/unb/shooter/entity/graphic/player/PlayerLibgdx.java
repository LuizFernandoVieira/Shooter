package br.unb.shooter.entity.graphic.player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import br.unb.shooter.entity.Player;

public class PlayerLibgdx {

    private IPlayerState state;
    private IdlePlayerState idleState;
    private WalkingPlayerState walkingState;

    /**
     * Constructor.
     */
    public PlayerLibgdx() {
        idleState = new IdlePlayerState();
        walkingState = new WalkingPlayerState();
        state = idleState;
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
            state = walkingState;
        } else {
            state = idleState;
        }

        state.update(deltaTime, player);
    }

    public void draw(SpriteBatch batch, Player player) {
        state.draw(batch, player);
    }

}
