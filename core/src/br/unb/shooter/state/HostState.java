package br.unb.shooter.state;

import br.unb.shooter.screen.HostScreen;

public class HostState extends State {

    @Override
    public void create(StateMachine machine) {
        setScreen(new HostScreen());
        getScreen().setMachine(machine);
    }

    @Override
    public void handle(StateEventEnum event) {
        if (event.equals(StateEventEnum.BUTTON_START)) {
            getMachine().changeState(new LobbyServerState());
        }
    }

}
