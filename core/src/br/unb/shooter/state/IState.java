package br.unb.shooter.state;

/**
 * State Interface.
 * 
 * @author brunobernardi
 *
 */
public interface IState {
    void create(StateMachine machine);

    void update();

    void draw();

    void dispose();

    void handle(StateEventEnum event);

    void resize(int width, int height);
}
