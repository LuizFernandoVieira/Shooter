package br.unb.shooter.state;

import br.unb.shooter.screen.StartScreen;

public class StartState extends State {

    private CutsceneState cutsceneState;

    public StartState() {
        this.cutsceneState = new CutsceneState();
    }

    @Override
    public void create(StateMachine machine) {
        setMachine(machine);
        setScreen(new StartScreen());
        getScreen().setMachine(machine);
        getScreen().create();
    }

    @Override
    public void handle(StateEventEnum event) {
        if (event.equals(StateEventEnum.TIMER_END) || event.equals(StateEventEnum.KEY_ESC)) {
            getMachine().changeState(cutsceneState);
        }
    }

    public CutsceneState getCutsceneState() {
        return cutsceneState;
    }

    public void setCutsceneState(CutsceneState cutsceneState) {
        this.cutsceneState = cutsceneState;
    }

}
