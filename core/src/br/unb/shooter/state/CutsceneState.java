package br.unb.shooter.state;

public class CutsceneState extends State {

    @Override
    public void create(StateMachine machine) {
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
