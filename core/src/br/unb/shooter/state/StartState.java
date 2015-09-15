package br.unb.shooter.state;

import br.unb.shooter.screen.StartScreen;

public class StartState extends State {

    @Override
    public void create(StateMachine machine) {
        setScreen(new StartScreen());
        getScreen().setMachine(machine);

        getScreen().create();
    }

}
