package br.unb.shooter.state;

import br.unb.shooter.controller.GameController;
import br.unb.shooter.controller.NetController;
import br.unb.shooter.screen.GameScreen;

public class GameState extends State {

    @Override
    public void create(final StateMachine machine) {
        setMachine(machine);
        setScreen(new GameScreen());
        getScreen().setMachine(machine);
        getScreen().create();

        if (!NetController.getInstance().getIsMultiplayer()) {
            GameController.getInstance().createServerPlayer("Player");
            GameController.getInstance().startGame();
        }
    }

    @Override
    public void update() {
        getScreen().update();
        NetController.getInstance().sendMessages();
        GameController.getInstance().resetPlayersState();
    }

    @Override
    public void handle(StateEventEnum event) {
    }

}
