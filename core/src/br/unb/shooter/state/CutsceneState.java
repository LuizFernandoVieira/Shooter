package br.unb.shooter.state;

import br.unb.shooter.screen.CutsceneScreen;

public class CutsceneState extends State {

    @Override
    public void create(StateMachine machine) {
        setMachine(machine);
        setScreen(new CutsceneScreen());
        getScreen().setMachine(machine);
        getScreen().create();
    }

    @Override
    public void handle(StateEventEnum event) {
        if (event.equals(StateEventEnum.STATE_END)) {
            getMachine().changeState(new MenuState());
        }
    }

}
