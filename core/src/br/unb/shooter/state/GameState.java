package br.unb.shooter.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;

import br.unb.shooter.controller.GameController;
import br.unb.shooter.controller.NetController;
import br.unb.shooter.entity.Player;
import br.unb.shooter.screen.GameScreen;

public class GameState extends State {

    private Boolean isServer;

    private Long startTime;

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

        startTime = TimeUtils.millis();
    }

    @Override
    public void update() {
        GameController.getInstance().getPlayer().update();
        getScreen().update();
        if (isServer && TimeUtils.timeSinceMillis(startTime) > 100) {
            NetController.getInstance().updateGame();
            startTime = TimeUtils.millis();
        }

        if (!isServer) {
            String text = "";

            for (Player player : GameController.getInstance().getPlayersMap().values()) {
                text += player.hashCode() + " ";
            }

            text += GameController.getInstance().getPlayer().hashCode();

            Gdx.app.log("GAME_STATE", text);
        }
    }

    @Override
    public void handle(StateEventEnum event) {
    }

}
