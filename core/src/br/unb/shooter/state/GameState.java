package br.unb.shooter.state;

import br.unb.shooter.controller.GameController;
import br.unb.shooter.controller.NetController;
import br.unb.shooter.screen.GameScreen;

public class GameState extends State {

    @Override
    public void create(final StateMachine machine) {
        setScreen(new GameScreen());
        getScreen().setMachine(machine);

        if (!NetController.getInstance().getIsMultiplayer()) {
            GameController.getInstance().createServerPlayer("Player");
        }
    }

    @Override
    public void update() {
        GameController.getInstance().getPlayer().update();
        getScreen().update();
    }

}
