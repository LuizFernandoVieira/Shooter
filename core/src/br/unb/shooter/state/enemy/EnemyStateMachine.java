package br.unb.shooter.state.enemy;

public class EnemyStateMachine {

    private IEnemyState state = new EnemyIdleState();

    public void create() {
        state.create(this);
    }

    public void update() {      
        state.update();
    }

    public void draw() {
        state.draw();
    }

    public void dispose() {
        state.dispose();
    }

    /**
     * Change state of the machine.
     * 
     * @param state
     *            state
     */
    public void changeState(IEnemyState state) {
        this.state.dispose();
        this.state = state;
        this.state.create(this);
    }

    /**
     * Handle state transitions.
     * 
     * @param endTimer
     */
    public void handle(EnemyStateEventEnum event) {
        this.state.handle(event);
    }

    public IEnemyState getState() {
        return state;
    }

    public void setState(IEnemyState state) {
        this.state = state;
    }
}
