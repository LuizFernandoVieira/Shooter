package br.unb.shooter.movement;

import org.junit.Assert;
import org.junit.Test;

import br.unb.shooter.controller.GameController;
import br.unb.shooter.entity.Camera;
import br.unb.shooter.entity.Map;
import br.unb.shooter.entity.Player;

public class MovementTest {

    private Player createPlayer(Camera camera) {
        Float positionXPlayer1 = 400f;
        Float positionYPlayer1 = 400f;

        Player player = new Player();
        player.setPositionX(positionXPlayer1);
        player.setPositionY(positionYPlayer1);
        player.setStartX(positionXPlayer1);
        player.setStartY(positionYPlayer1);
        player.setScreenX(camera.getWidth() / 2 - Player.PLAYER_OFFSET_X);
        player.setScreenY(camera.getHeight() / 2 - Player.PLAYER_OFFSET_Y);
        player.setOffsetX(Player.PLAYER_OFFSET_X);
        player.setOffsetY(Player.PLAYER_OFFSET_Y);

        camera.setStartX(player.getPositionX());
        camera.setStartY(player.getPositionY());
        camera.setPositionX(player.getPositionX());
        camera.setPositionY(player.getPositionY());

        return player;
    }

    private void log(Movement movement) {
        Player player = movement.getPlayer();
        Camera camera = movement.getCamera();

        System.out.println("playerX: " + player.getPositionX() + " playerY: " + player.getPositionY() + " screenX: "
                + player.getScreenX() + " screenY: " + player.getScreenY() + " cameraX: " + camera.getPositionX()
                + " cameraY: " + camera.getPositionY());
    }

    @Test
    public void testMovevement() {
        Movement movement = new Movement();

        Camera camera = new Camera();

        camera.setWidth(600f);
        camera.setHeight(600f);

        Player player = createPlayer(camera);

        Float mapCols = 50f;
        Float mapRows = 50f;
        Float tileWidth = 32f;
        Float tileHeight = 32f;

        movement.setCamera(camera);
        movement.setPlayer(GameController.getInstance().getPlayer());
        movement.setMap(new Map());
        movement.getMap().setWidth(mapCols * tileWidth);
        movement.getMap().setHeight(mapRows * tileHeight);
        movement.getMap().setPositionX(0f);
        movement.getMap().setPositionY(0f);
        movement.getMap().setCols(mapCols);
        movement.getMap().setRows(mapRows);
        movement.getMap().getTile().setHeight(tileHeight);
        movement.getMap().getTile().setWidth(tileWidth);

        movement.setPlayer(player);

        log(movement);

        player.setMoveRight(true);

        for (int i = 0; i < 400; i++) {
            movement.update();
            log(movement);
        }

        Assert.assertTrue(true);
    }
}
