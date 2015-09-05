package br.unb.shooter.state;

/**
 * State Interface.
 * 
 * @author brunobernardi
 *
 */
public interface IState {
    /**
     * 
     * @param machine
     */
    void create(StateMachine machine);

    /**
     * 
     */
    void update();

    /**
     * 
     */
    void draw();

    /**
     * 
     */
    void dispose();
}
