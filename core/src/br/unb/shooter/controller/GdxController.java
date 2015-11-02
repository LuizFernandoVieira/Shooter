package br.unb.shooter.controller;

import java.util.HashMap;

import br.unb.shooter.entity.Player;
import br.unb.shooter.entity.graphic.player.IPlayerState;
import br.unb.shooter.entity.graphic.player.PlayerGdx;
import br.unb.shooter.entity.graphic.shot.ShotGdx;
import br.unb.shooter.entity.graphic.targetmark.TargetMarkGdx;
import br.unb.shooter.entity.graphic.weapon.WeaponGdx;

public class GdxController {
    private static GdxController instance;

    private PlayerGdx playerGdx;

    private TargetMarkGdx markGdx;

    private ShotGdx shotGdx;
    
    private WeaponGdx weaponGdx;

    public static GdxController getInstance() {
        if (instance == null) {
            instance = new GdxController();
        }
        return instance;
    }

    /**
     * Add player to gdx controller.
     */
    public void addPlayer(Player player) {
        if (playerGdx == null) {
            playerGdx = new PlayerGdx();
            playerGdx.setStateMap(new HashMap<Integer, IPlayerState>());
        }
        playerGdx.getStateMap().put(player.getId(), playerGdx.getDefaultState());
    }

    public PlayerGdx getPlayerGdx() {
        if (playerGdx == null) {
            playerGdx = new PlayerGdx();
            playerGdx.setStateMap(new HashMap<Integer, IPlayerState>());
        }
        return playerGdx;
    }

    public void setPlayerGdx(PlayerGdx playerGdx) {
        this.playerGdx = playerGdx;
    }

    public TargetMarkGdx getMarkGdx() {
        if (markGdx == null) {
            markGdx = new TargetMarkGdx();
        }
        return markGdx;
    }

    public void setMarkGdx(TargetMarkGdx markGdx) {
        this.markGdx = markGdx;
    }

    public ShotGdx getShotGdx() {
        if (shotGdx == null) {
            shotGdx = new ShotGdx();
        }
        return shotGdx;
    }

    public void setShotGdx(ShotGdx shotGdx) {
        this.shotGdx = shotGdx;
    }
    
    public WeaponGdx getWeaponGdx() {
        if(weaponGdx == null) {
            weaponGdx = new WeaponGdx();
        }
        return weaponGdx;
    }

    public void setWeaponGdx(WeaponGdx weaponGdx) {
        this.weaponGdx = weaponGdx;
    }
}
