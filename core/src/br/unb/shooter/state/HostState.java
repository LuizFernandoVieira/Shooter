package br.unb.shooter.state;

import br.unb.shooter.screen.HostScreen;

public class HostState extends State {

    @Override
    public void create(StateMachine machine) {
        setScreen(new HostScreen());
        getScreen().setMachine(machine);
    }

}
