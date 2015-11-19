package br.unb.shooter.entity.graphic.enemy;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import br.unb.shooter.entity.Enemy;
import br.unb.shooter.entity.Player;
import br.unb.shooter.entity.graphic.player.IPlayerState;
import br.unb.shooter.entity.graphic.player.IdlePlayerState;
import br.unb.shooter.entity.graphic.player.PlayerTexture;
import br.unb.shooter.entity.graphic.player.WalkingPlayerState;

public class EnemyGdx {

    private HashMap<Integer, IEnemyState> stateMap;
    private IdleEnemyState idleRightState;
    private IdleEnemyState idleLeftState;
    private WalkingEnemyState walkingRightState;
    private WalkingEnemyState walkingLeftState;

    private EnemyTexture texture;

    private Boolean canDraw = false;

    /**
     * Constructor.
     */
    public EnemyGdx() {
        idleRightState = new IdleEnemyState();
        idleLeftState = new IdleEnemyState();
        walkingRightState = new WalkingEnemyState();
        walkingLeftState = new WalkingEnemyState();
    }

    /**
     * Init enemy graphics.
     */
    public void initGraphics() {
        texture = new EnemyTexture();
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
     * Update enemy.
     * 
     * @param enemy Enemy
     */
    public void update(Enemy enemy, float deltaTime) {
        stateMap.put(enemy.getId(), idleRightState);

        stateMap.get(enemy.getId()).update(deltaTime, enemy);

        canDraw = true;
    }

    public void draw(SpriteBatch batch, Enemy enemy) {
        if (canDraw) {
            stateMap.get(enemy.getId()).draw(batch, enemy);
        }
    }

    public HashMap<Integer, IEnemyState> getStateMap() {
        return stateMap;
    }

    public void setStateMap(HashMap<Integer, IEnemyState> stateMap) {
        this.stateMap = stateMap;
    }

    public IdleEnemyState getDefaultState() {
        return idleRightState;
    }    
    
}
