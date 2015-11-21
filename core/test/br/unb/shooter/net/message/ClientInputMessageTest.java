package br.unb.shooter.net.message;

import org.junit.Test;

import br.unb.shooter.controller.GameController;

public class ClientInputMessageTest {

    @Test
    public void testMovement() {
        GameController.getInstance().createServerPlayer("Server");
        GameController.getInstance().createClientPlayer("Client");
        GameController.getInstance().startGame();
    }

}
