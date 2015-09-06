package br.unb.shooter.controller;

import java.util.HashMap;

import com.badlogic.gdx.math.Vector2;

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
        player = new Player(new Vector2(0, 0));
        player.setName(name);
        player.setId(1);

        if (playersMap == null) {
            playersMap = new HashMap<Integer, Player>();
        }

        playersMap.put(player.getId(), player);
    }

    /**
     * Create a player from a connect message.
     * 
     * @param name Player's name
     */
    public void createClientPlayer(String name) {
        if (playersMap != null) {
            Player player = new Player();
            player.setName(name);
            player.setId(playersMap.size() + 1);
            playersMap.put(player.getId(), player);
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

}
