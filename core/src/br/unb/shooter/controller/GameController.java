package br.unb.shooter.controller;

import java.util.HashMap;

import br.unb.shooter.entity.Enemy;
import br.unb.shooter.entity.Player;
import br.unb.shooter.entity.Shot;

public class GameController {

    private Player player;

    private String serverName;

    private HashMap<Integer, Player> playersMap;

    private HashMap<Integer, Integer> shotsSequenceMap;

    private HashMap<Integer, Enemy> enemiesMap;

    private HashMap<Integer, Shot> shotsMap;

    private static GameController instance;

    private Boolean isStarted = false;

    private Float mouseX;

    private Float mouseY;

    public GameController() {
        shotsMap = new HashMap<Integer, Shot>();
        mouseX = 0f;
        mouseY = 0f;
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
     */
    public void createShot(Player player) {
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

}
