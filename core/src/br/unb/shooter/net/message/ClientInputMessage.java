package br.unb.shooter.net.message;

import br.unb.shooter.controller.GameController;
import br.unb.shooter.entity.Player;
import br.unb.shooter.util.Constants;

public class ClientInputMessage extends Message {

    private Integer playerId;

    private Boolean moveUp;

    private Boolean moveDown;

    private Boolean moveRight;

    private Boolean moveLeft;

    public ClientInputMessage(String message) {
        this.id = MessageEnum.CLIENT_INPUT.getId();
        translate(message);
    }

    public ClientInputMessage(Player player) {
        this.id = MessageEnum.CLIENT_INPUT.getId();
        this.playerId = player.getId();
        this.moveUp = player.getMoveUp();
        this.moveRight = player.getMoveRight();
        this.moveDown = player.getMoveDown();
        this.moveLeft = player.getMoveLeft();
    }

    @Override
    public void execute() {
        Player sender = GameController.getInstance().getPlayersMap().get(this.playerId);
        sender.setMoveUp(moveUp);
        sender.setMoveRight(moveRight);
        sender.setMoveDown(moveDown);
        sender.setMoveLeft(moveLeft);
        sender.update();
    }

    private void translate(String message) {
        String[] slices = message.split(Constants.SPACE);

        this.id = MessageEnum.CLIENT_INPUT.getId();
        this.playerId = Integer.valueOf(slices[1]);
        this.moveUp = Constants.ONE.equals(slices[2]);
        this.moveRight = Constants.ONE.equals(slices[3]);
        this.moveDown = Constants.ONE.equals(slices[4]);
        this.moveLeft = Constants.ONE.equals(slices[5]);
    }

    @Override
    public String toString() {
        return this.id + Constants.SPACE + this.playerId + Constants.SPACE + convertBoolean(this.moveUp)
                + Constants.SPACE + convertBoolean(this.moveRight) + Constants.SPACE + convertBoolean(this.moveDown)
                + Constants.SPACE + convertBoolean(this.moveLeft);
    }

    private String convertBoolean(Boolean bool) {
        return bool ? "1" : "0";
    }

    public Boolean getMoveUp() {
        return moveUp;
    }

    public void setMoveUp(Boolean moveUp) {
        this.moveUp = moveUp;
    }

    public Boolean getMoveDown() {
        return moveDown;
    }

    public void setMoveDown(Boolean moveDown) {
        this.moveDown = moveDown;
    }

    public Boolean getMoveRight() {
        return moveRight;
    }

    public void setMoveRight(Boolean moveRight) {
        this.moveRight = moveRight;
    }

    public Boolean getMoveLeft() {
        return moveLeft;
    }

    public void setMoveLeft(Boolean moveLeft) {
        this.moveLeft = moveLeft;
    }

}
