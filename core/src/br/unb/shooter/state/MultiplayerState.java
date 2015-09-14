package br.unb.shooter.state;

import br.unb.shooter.controller.NetController;
import br.unb.shooter.screen.MultiplayerScreen;

public class MultiplayerState extends State {

    @Override
    public void create(StateMachine machine) {
        setScreen(new MultiplayerScreen());
        getScreen().setMachine(machine);

        NetController.getInstance().setIsMultiplayer(true);
    }

}
