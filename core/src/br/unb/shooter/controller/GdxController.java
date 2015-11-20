package br.unb.shooter.controller;

import java.util.HashMap;

import br.unb.shooter.entity.Enemy;
import br.unb.shooter.entity.Player;
import br.unb.shooter.entity.graphic.enemy.EnemyGdx;
import br.unb.shooter.entity.graphic.enemy.IEnemyState;
import br.unb.shooter.entity.graphic.map.MapGdx;
import br.unb.shooter.entity.graphic.player.IPlayerState;
import br.unb.shooter.entity.graphic.player.PlayerGdx;
import br.unb.shooter.entity.graphic.shot.ShotGdx;
import br.unb.shooter.entity.graphic.targetmark.TargetMarkGdx;
import br.unb.shooter.entity.graphic.weapon.FireWeaponGdx;

public class GdxController {
    private static GdxController instance;

    private PlayerGdx playerGdx;
    
    private EnemyGdx enemyGdx;

    private TargetMarkGdx markGdx;

    private ShotGdx shotGdx;

    private FireWeaponGdx weaponGdx;

    private MapGdx mapGdx;

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
    
    /**
     * Add enemy to gdx controller.
     */
    public void addEnemy(Enemy enemy) {
        if (enemyGdx == null) {
            enemyGdx = new EnemyGdx();
            enemyGdx.setStateMap(new HashMap<Integer, IEnemyState>());
        }
        enemyGdx.getStateMap().put(enemy.getId(), enemyGdx.getDefaultState());
    }

    public EnemyGdx getEnemyGdx() {
        if (enemyGdx == null) {
            enemyGdx = new EnemyGdx();
            enemyGdx.setStateMap(new HashMap<Integer, IEnemyState>());
        }
        return enemyGdx;
    }

    public void setEnemyGdx(EnemyGdx enemyGdx) {
        this.enemyGdx = enemyGdx;
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

    public FireWeaponGdx getWeaponGdx() {
        if (weaponGdx == null) {
            weaponGdx = new FireWeaponGdx();
        }
        return weaponGdx;
    }

    public void setWeaponGdx(FireWeaponGdx weaponGdx) {
        this.weaponGdx = weaponGdx;
    }

    public MapGdx getMapGdx() {
        if (mapGdx == null) {
            mapGdx = new MapGdx();
        }

        return mapGdx;
    }

    public void setMapGdx(MapGdx mapGdx) {
        this.mapGdx = mapGdx;
    }

}
