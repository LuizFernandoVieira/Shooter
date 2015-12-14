package br.unb.shooter.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.unb.shooter.collision.PlayerCollision;
import br.unb.shooter.collision.ShotCollision;
import br.unb.shooter.entity.Enemy;
import br.unb.shooter.entity.FireWeapon;
import br.unb.shooter.entity.Player;
import br.unb.shooter.entity.Shot;
import br.unb.shooter.entity.TargetMark;
import br.unb.shooter.util.Constants;

public class GameController {

    private Player player;

    private String serverName;

    private HashMap<Integer, Player> playersMap;

    private HashMap<Integer, Integer> shotsSequenceMap;

    private HashMap<Integer, Enemy> enemiesMap;

    private HashMap<Integer, FireWeapon> weaponsMap;

    private HashMap<Integer, Shot> shotsMap;

    private List<Integer> removedShots;

    private TargetMark targetMark;

    private static GameController instance;

    private Boolean isStarted = false;

    private Float mouseX;

    private Float mouseY;

    private PlayerCollision playerCollision;

    private ShotCollision shotCollision;

    public GameController() {
        shotsMap = new HashMap<Integer, Shot>();
        mouseX = 0f;
        mouseY = 0f;
        removedShots = new ArrayList<Integer>();
        playerCollision = new PlayerCollision();
        shotCollision = new ShotCollision();
        targetMark = new TargetMark();
    }

    /**
     * Create a singleton from GameController.
     * 
     * @return GameController instance
     */
    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
        }

        return instance;
    }

    /**
     * Create a player.
     * 
     * @param name Player's name
     */
    public void createServerPlayer(String name) {
        player = new Player();
        player.setName(name);
        player.setId(1);

        if (playersMap == null) {
            playersMap = new HashMap<Integer, Player>();
        }

        playersMap.put(player.getId(), player);

        GdxController.getInstance().addPlayer(player);

        if (weaponsMap == null) {
            weaponsMap = new HashMap<Integer, FireWeapon>();
        }

        FireWeapon weapon = new FireWeapon();
        weapon.setOwner(player);
        weaponsMap.put(player.getId(), weapon);

        GdxController.getInstance().addWeapon(weapon);
    }

    /**
     * Create a player on server from a connect message.
     * 
     * @param name Player's name
     */
    public void createClientPlayer(String name) {
        if (playersMap != null) {
            Player player = new Player();
            player.setName(name);
            player.setId(playersMap.size() + 1);
            playersMap.put(player.getId(), player);

            GdxController.getInstance().addPlayer(player);

            if (weaponsMap == null) {
                weaponsMap = new HashMap<Integer, FireWeapon>();
            }

            FireWeapon weapon = new FireWeapon();
            weapon.setOwner(player);
            weaponsMap.put(player.getId(), weapon);

            GdxController.getInstance().addWeapon(weapon);
        }
    }

    /**
     * Initialize game state.
     */
    public void startGame() {
        Float positionXPlayer1 = 280f;
        Float positionXPlayer2 = 500f;
        Float positionXPlayer3 = 280f;
        Float positionXPlayer4 = 280f;
        Float positionYPlayer1 = 267f;
        Float positionYPlayer2 = 267f;
        Float positionYPlayer3 = 267f;
        Float positionYPlayer4 = 267f;

        Integer index = 0;
        for (Player player : GameController.getInstance().playersMap.values()) {
            if (index == 0) {
                player.setX(positionXPlayer1);
                player.setY(positionYPlayer1);
                player.setStartX(positionXPlayer1);
                player.setStartY(positionYPlayer1);
                player.setOffsetX(Constants.PLAYER_OFFSET_X);
                player.setOffsetY(Constants.PLAYER_OFFSET_Y);
                player.setHeight(Constants.PLAYER_IDLE_HEIGHT);
                player.setWidth(Constants.PLAYER_IDLE_WIDTH);

                targetMark.setStartX(positionXPlayer1 + Constants.PLAYER_OFFSET_X - Constants.TARGET_MARK_WIDTH / 2);
                targetMark.setStartY(positionYPlayer1 + Constants.PLAYER_OFFSET_Y - Constants.TARGET_MARK_HEIGHT / 2);
                targetMark.setX(targetMark.getStartX());
                targetMark.setY(targetMark.getStartY());
            }
            if (index == 1) {
                player.setX(positionXPlayer2);
                player.setY(positionYPlayer2);
                player.setStartX(positionXPlayer2);
                player.setStartY(positionYPlayer2);
                player.setOffsetX(Constants.PLAYER_OFFSET_X);
                player.setOffsetY(Constants.PLAYER_OFFSET_Y);
                player.setHeight(66f);
                player.setWidth(42f);
            }
            if (index == 2) {
                player.setX(positionXPlayer3);
                player.setY(positionYPlayer3);
                player.setStartX(positionXPlayer3);
                player.setStartY(positionYPlayer3);
                player.setOffsetX(Constants.PLAYER_OFFSET_X);
                player.setOffsetY(Constants.PLAYER_OFFSET_Y);
                player.setHeight(66f);
                player.setWidth(42f);
            }
            if (index == 3) {
                player.setX(positionXPlayer4);
                player.setY(positionYPlayer4);
                player.setStartX(positionXPlayer4);
                player.setStartY(positionYPlayer4);
                player.setOffsetX(Constants.PLAYER_OFFSET_X);
                player.setOffsetY(Constants.PLAYER_OFFSET_Y);
                player.setHeight(66f);
                player.setWidth(42f);
            }
            index++;
        }

    }

    /**
     * Create a shot.
     * 
     * @param player
     */
    public Shot createShot(Player player) {
        if (shotsMap == null) {
            shotsMap = new HashMap<Integer, Shot>();
        }

        if (shotsSequenceMap == null) {
            shotsSequenceMap = new HashMap<Integer, Integer>();
        }

        if (!shotsSequenceMap.containsKey(player.getId())) {
            shotsSequenceMap.put(player.getId(), 0);
        }

        Shot shot = new Shot();

        shotsSequenceMap.put(player.getId(), shotsSequenceMap.get(player.getId()) + 1);

        shot.create(player, shotsSequenceMap.get(player.getId()));

        // Hash function that generates global shot sequence
        Integer shotSequence = ((player.getId() - 1) + (shotsSequenceMap.get(player.getId()) - 1) * 4) + 1;

        shot.setId(shotSequence);

        shotsMap.put(shotSequence, shot);

        MusicController.getInstance().shoot();

        return shot;
    }

    /**
     * Remove one shot.
     *
     * @param shot
     */
    public void removeShot(Shot shot) {
        shotsMap.remove(shot.getId());
    }

    /**
     * Reset player state.
     */
    public void resetPlayersState() {
        for (Player player : playersMap.values()) {
            if (player.getIsChangingState()) {
                player.setIsChangingState(false);
            }
            if (player.getIsShooting()) {
                player.setIsShooting(false);
            }
        }
    }

    public void addEnemy(Enemy enemy) {
        if (this.enemiesMap == null) {
            this.enemiesMap = new HashMap<Integer, Enemy>();
        }
        Integer cellX = Math.round((enemy.getX()));
        Integer cellY = Math.round(enemy.getY());
        this.enemiesMap.put(cellX + cellY, enemy);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public HashMap<Integer, Player> getPlayersMap() {
        return playersMap;
    }

    public void setPlayersMap(HashMap<Integer, Player> playersMap) {
        this.playersMap = playersMap;
    }

    public HashMap<Integer, Enemy> getEnemiesMap() {
        return enemiesMap;
    }

    public void setEnemiesMap(HashMap<Integer, Enemy> enemiesMap) {
        this.enemiesMap = enemiesMap;
    }

    public HashMap<Integer, Shot> getShotsMap() {
        return shotsMap;
    }

    public void setShotsMap(HashMap<Integer, Shot> shotsMap) {
        this.shotsMap = shotsMap;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public Boolean getIsStarted() {
        return isStarted;
    }

    public void setIsStarted(Boolean isStarted) {
        this.isStarted = isStarted;
    }

    public Float getMouseX() {
        return mouseX;
    }

    public void setMouseX(Float mouseX) {
        this.mouseX = mouseX;
    }

    public Float getMouseY() {
        return mouseY;
    }

    public void setMouseY(Float mouseY) {
        this.mouseY = mouseY;
    }

    public List<Integer> getRemovedShots() {
        return removedShots;
    }

    public void setRemovedShots(List<Integer> removedShots) {
        this.removedShots = removedShots;
    }

    public PlayerCollision getPlayerCollision() {
        return playerCollision;
    }

    public void setPlayerCollision(PlayerCollision playerCollision) {
        this.playerCollision = playerCollision;
    }

    public HashMap<Integer, FireWeapon> getWeaponsMap() {
        return weaponsMap;
    }

    public void setWeaponsMap(HashMap<Integer, FireWeapon> weaponsMap) {
        this.weaponsMap = weaponsMap;
    }

    public TargetMark getTargetMark() {
        return targetMark;
    }

    public void setTargetMark(TargetMark targetMark) {
        this.targetMark = targetMark;
    }

    public ShotCollision getShotCollision() {
        return shotCollision;
    }

    public void setShotCollision(ShotCollision shotCollision) {
        this.shotCollision = shotCollision;
    }

}
