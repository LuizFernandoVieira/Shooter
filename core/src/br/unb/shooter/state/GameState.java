package br.unb.shooter.state;

import br.unb.shooter.controller.GameController;
import br.unb.shooter.controller.NetController;
import br.unb.shooter.screen.GameScreen;

public class GameState extends State {

    @Override
    public void create(final StateMachine machine) {
        setMachine(machine);

        if (!NetController.getInstance().getIsMultiplayer()) {
            GameController.getInstance().createServerPlayer("Player");
            GameController.getInstance().startGame();
        }

        setScreen(new GameScreen());
        getScreen().setMachine(machine);
        getScreen().create();
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
