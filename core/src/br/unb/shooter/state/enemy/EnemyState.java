package br.unb.shooter.state.enemy;

public class EnemyState implements IEnemyState {

    private EnemyStateMachine machine;

    @Override
    public void create(EnemyStateMachine machine) {
        this.machine = machine;
    }

    @Override
    public void update() {
        
    }

    @Override
    public void draw() {
        
    }
    
    @Override
    public void handle(EnemyStateEventEnum event) {
        
    }
    
    @Override
    public void dispose() {
        
    }
    
    public EnemyStateMachine getMachine() {
        return machine;
    }

    public void setMachine(EnemyStateMachine machine) {
        this.machine = machine;
    }

}
