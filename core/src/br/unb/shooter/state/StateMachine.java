package br.unb.shooter.state;

public class StateMachine {

    private IState state = new GameState();

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
     * @param state state
     */
    public void changeState(IState state) {
        this.state.dispose();
        this.state = state;
        state.create(this);
    }

}
