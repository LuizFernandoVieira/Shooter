package br.unb.shooter.entity.graphic.enemy;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import br.unb.shooter.entity.Enemy;

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
        idleRightState.setIdleFrames(texture.getIdleRightFrame());
        idleLeftState.setIdleFrames(texture.getIdleLeftFrame());
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
        if (enemy.getIsMoving()) {
            if (enemy.getFacing() == 0) {
                stateMap.put(enemy.getId(), walkingRightState);
            }
            if (enemy.getFacing() == 1) {
                stateMap.put(enemy.getId(), walkingLeftState);
            }
        } else {
            if (enemy.getFacing() == 0) {
                stateMap.put(enemy.getId(), idleRightState);
            }
            if (enemy.getFacing() == 1) {
                stateMap.put(enemy.getId(), idleLeftState);
            }
        }

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
