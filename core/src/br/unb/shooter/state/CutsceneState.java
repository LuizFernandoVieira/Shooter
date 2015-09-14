package br.unb.shooter.state;

import br.unb.shooter.screen.CutsceneScreen;

public class CutsceneState extends State {

    @Override
    public void create(StateMachine machine) {
        setScreen(new CutsceneScreen());
        getScreen().setMachine(machine);
    }

}
