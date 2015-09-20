package br.unb.shooter.state;

import br.unb.shooter.controller.GameController;
import br.unb.shooter.controller.NetController;
import br.unb.shooter.screen.GameScreen;

public class GameState extends State {

    private Boolean isServer;

    public GameState(Boolean isServer) {
        this.isServer = isServer;
    }

    @Override
    public void create(final StateMachine machine) {
        setMachine(machine);
        setScreen(new GameScreen());
        getScreen().setMachine(machine);
        getScreen().create();

        if (!NetController.getInstance().getIsMultiplayer()) {
            GameController.getInstance().createServerPlayer("Player");
        }
    }

    @Override
    public void update() {
        GameController.getInstance().getPlayer().update();
        getScreen().update();
        if (isServer) {
            NetController.getInstance().updateGame();
        }
        if (!isServer) {
            if (GameController.getInstance().getPlayer().getIsMoving()) {
                NetController.getInstance().sendPlayerInput(GameController.getInstance().getPlayer());
            }
        }
    }

    @Override
    public void handle(StateEventEnum event) {
    }

}
