package br.unb.shooter.state;

import br.unb.shooter.controller.NetController;

public class StateMachine {

	private IState state = new StartState();

	public void create() {
		state.create(this);
	}

	public void update() {	    
		NetController.getInstance().getMessageQueue().executeMessages();

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
	public void changeState(IState state) {
		this.state.dispose();
		this.state = state;
		this.state.create(this);
	}

	/**
	 * Handle state transitions.
	 * 
	 * @param endTimer
	 */
	public void handle(StateEventEnum event) {
		this.state.handle(event);
	}

	public IState getState() {
		return state;
	}

	public void setState(IState state) {
		this.state = state;
	}

    public void resize (int width, int height)
    {
        this.state.resize(width, height);
    }   
}
