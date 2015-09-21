package br.unb.shooter.controller;

import java.util.HashMap;

import br.unb.shooter.entity.Player;
import br.unb.shooter.entity.graphic.player.IPlayerState;
import br.unb.shooter.entity.graphic.player.PlayerGdx;

public class GdxController {
    private static GdxController instance;

    public static GdxController getInstance() {
        if (instance == null) {
            instance = new GdxController();
        }
        return instance;
    }

    private PlayerGdx playerGdx;

    /**
     * Add player to gdx controller.
     */
    public void addPlayer(Player player) {
        if (playerGdx == null) {
            playerGdx = new PlayerGdx();
            playerGdx.setStateMap(new HashMap<Integer, IPlayerState>());
        }
        playerGdx.getStateMap().put(player.getId(), playerGdx.getIdleState());
    }

    public PlayerGdx getPlayerGdx() {
        return playerGdx;
    }

    public void setPlayerGdx(PlayerGdx playerGdx) {
        this.playerGdx = playerGdx;
    }
}
