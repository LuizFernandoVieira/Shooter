package br.unb.shooter.net.message;

import br.unb.shooter.controller.GameController;
import br.unb.shooter.controller.NetController;
import br.unb.shooter.entity.Player;
import br.unb.shooter.util.Constants;

public class ClientInputMessage extends Message {

    private Integer playerId;

    private Boolean moveUp;

    private Boolean moveDown;

    private Boolean moveRight;

    private Boolean moveLeft;

    private Float mouseX;

    private Float mouseY;

    private Boolean leftMouseButton;

    /**
     * Constructor.
     * 
     * @param message
     */
    public ClientInputMessage(String message) {
        super();
        this.id = MessageEnum.CLIENT_INPUT.getId();
        translate(message);
    }

    /**
     * Constructor.
     * 
     * @param player
     * @param mouseX
     * @param mouseY
     */
    public ClientInputMessage(Player player, Float mouseX, Float mouseY) {
        super();
        this.id = MessageEnum.CLIENT_INPUT.getId();
        this.playerId = player.getId();
        this.moveUp = player.getMoveUp();
        this.moveRight = player.getMoveRight();
        this.moveDown = player.getMoveDown();
        this.moveLeft = player.getMoveLeft();
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.leftMouseButton = player.getIsShooting();
    }

    @Override
    public void execute() {
        Player sender = GameController.getInstance().getPlayersMap().get(this.playerId);
        sender.setMoveUp(moveUp);
        sender.setMoveRight(moveRight);
        sender.setMoveDown(moveDown);
        sender.setMoveLeft(moveLeft);
        sender.setMovingState();
        sender.update();
        sender.setFacing(mouseX, mouseY);
        sender.setTargetX(mouseX);
        sender.setTargetY(mouseY);
        if (leftMouseButton) {
            sender.setIsShooting(true);
        }
        NetController.getInstance().setLastInputTime(this.getTimestamp());
    }

    private void translate(String message) {
        String[] slices = message.split(Constants.SPACE);

        this.id = MessageEnum.CLIENT_INPUT.getId();
        this.playerId = Integer.valueOf(slices[1]);
        this.moveUp = Constants.ONE.equals(slices[2]);
        this.moveRight = Constants.ONE.equals(slices[3]);
        this.moveDown = Constants.ONE.equals(slices[4]);
        this.moveLeft = Constants.ONE.equals(slices[5]);
        this.mouseX = Float.valueOf(slices[6]);
        this.mouseY = Float.valueOf(slices[7]);
        this.leftMouseButton = Constants.ONE.equals(slices[8]);
    }

    @Override
    public String toString() {
        return this.id + Constants.SPACE + this.playerId + Constants.SPACE + Constants.convertBoolean(this.moveUp)
                + Constants.SPACE + Constants.convertBoolean(this.moveRight) + Constants.SPACE
                + Constants.convertBoolean(this.moveDown) + Constants.SPACE + Constants.convertBoolean(this.moveLeft)
                + Constants.SPACE + mouseX + Constants.SPACE + mouseY + Constants.SPACE
                + Constants.convertBoolean(this.leftMouseButton);
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

    public Boolean getLeftMouseButton() {
        return leftMouseButton;
    }

    public void setLeftMouseButton(Boolean leftMouseButton) {
        this.leftMouseButton = leftMouseButton;
    }

}
