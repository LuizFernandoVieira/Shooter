package br.unb.shooter.state;

import br.unb.shooter.controller.NetController;
import br.unb.shooter.screen.MenuScreen;

public class MenuState extends State {

    @Override
    public void create(final StateMachine machine) {
        setScreen(new MenuScreen());
        getScreen().setMachine(machine);

        getScreen().create();

        NetController.getInstance().setIsMultiplayer(false);
    }

}
