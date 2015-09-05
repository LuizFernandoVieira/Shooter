package br.unb.shooter.state;

public interface IState {
	public void create(StateMachine machine);

	public void update();

	public void draw();

	public void dispose();
}
