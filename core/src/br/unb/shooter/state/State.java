package br.unb.shooter.state;

import br.unb.shooter.screen.Screen;

public abstract class State implements IState {

    private Screen screen;

    private StateMachine machine;

    @Override
    public void create(StateMachine machine) {
        this.machine = machine;
        screen.create();
    }

    @Override
    public void update() {
        screen.update();
    }

    @Override
    public void draw() {
        screen.draw();
    }

    @Override
    public void dispose() {
        screen.dispose();
    }
    
    @Override
    public void resize (int width, int height)
    {
        screen.resize(width, height);
    }   

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public StateMachine getMachine() {
        return machine;
    }

    public void setMachine(StateMachine machine) {
        this.machine = machine;
    }

}
