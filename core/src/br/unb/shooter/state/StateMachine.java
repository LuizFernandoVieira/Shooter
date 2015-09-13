package br.unb.shooter.state;

public class StateMachine {

    private IState state = new StartState();

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

    public IState getState() {
        return state;
    }

    public void setState(IState state) {
        this.state = state;
    }

}
