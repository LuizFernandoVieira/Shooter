package br.unb.shooter.state.enemy;

public interface IEnemyState {
    void create(EnemyStateMachine enemyStateMachine);

    void update();

    void draw();

    void dispose();

    void handle(EnemyStateEventEnum event);
}
