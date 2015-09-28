package br.unb.shooter.controller;

import java.util.HashMap;

import br.unb.shooter.entity.Enemy;
import br.unb.shooter.entity.Player;
import br.unb.shooter.entity.Shot;

public class GameController {

    private Player player;

    private String serverName;

    private HashMap<Integer, Player> playersMap;

    private HashMap<Integer, Enemy> enemiesMap;

    private HashMap<Integer, Shot> shotsMap;

    private static GameController instance;

    private Boolean isStarted = false;

    private Integer mouseX;

    private Integer mouseY;

    private Integer shotSequence;

    public GameController() {
        shotsMap = new HashMap<Integer, Shot>();
        mouseX = 0;
        mouseY = 0;
        shotSequence = 0;
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
        }
    }

    /**
     * Initialize game state.
     */
    public void startGame() {
        Float positionXPlayer1 = 0f;
        Float positionXPlayer2 = 64f;
        Float positionXPlayer3 = 128f;
        Float positionXPlayer4 = 192f;
        Float positionYPlayer1 = 0f;
        Float positionYPlayer2 = 0f;
        Float positionYPlayer3 = 0f;
        Float positionYPlayer4 = 0f;

        Integer index = 0;
        for (Player player : GameController.getInstance().playersMap.values()) {
            if (index == 0) {
                player.setPositionX(positionXPlayer1);
                player.setPositionY(positionYPlayer1);
            }
            if (index == 1) {
                player.setPositionX(positionXPlayer2);
                player.setPositionY(positionYPlayer2);
            }
            if (index == 2) {
                player.setPositionX(positionXPlayer3);
                player.setPositionY(positionYPlayer3);
            }
            if (index == 3) {
                player.setPositionX(positionXPlayer4);
                player.setPositionY(positionYPlayer4);
            }
            index++;
        }
    }

    /**
     * Create a shot.
     * 
     * @param player
     * @param mouseX
     * @param mouseY
     */
    public void createShot(Player player, Float mouseX, Float mouseY) {
        if (shotsMap == null) {
            shotsMap = new HashMap<Integer, Shot>();
        }
        shotSequence++;
        Shot shot = new Shot();

        shot.create(player, mouseX, mouseY);
        shot.setId(shotSequence);

        shotsMap.put(shotSequence, shot);
    }

    public void removeShot(Shot shot) {
        shotsMap.remove(shot.getId());
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

    public Integer getMouseX() {
        return mouseX;
    }

    public void setMouseX(Integer mouseX) {
        this.mouseX = mouseX;
    }

    public Integer getMouseY() {
        return mouseY;
    }

    public void setMouseY(Integer mouseY) {
        this.mouseY = mouseY;
    }

}
