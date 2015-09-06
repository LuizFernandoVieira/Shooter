package br.unb.shooter.net.message;

import br.unb.shooter.util.Constants;

public class InputMessage extends Message {

    private Integer targetX;

    private Integer targetY;

    private Boolean leftActionButton;

    private Boolean rightActionButton;

    private Boolean moveUp;

    private Boolean moveDown;

    private Boolean moveRight;

    private Boolean moveLeft;

    public InputMessage(String message) {
        this.id = MessageEnum.INPUT.getId();
        translate(message);
    }

    @Override
    public void execute() {
    }

    private void translate(String message) {
        String[] slices = message.split(Constants.SPACE);

        this.id = MessageEnum.INPUT.getId();
        this.targetX = Integer.valueOf(slices[1]);
        this.targetY = Integer.valueOf(slices[2]);
        this.leftActionButton = Constants.ONE.equals(slices[3]);
        this.rightActionButton = Constants.ONE.equals(slices[4]);
        this.moveUp = Constants.ONE.equals(slices[5]);
        this.moveDown = Constants.ONE.equals(slices[6]);
        this.moveRight = Constants.ONE.equals(slices[7]);
        this.moveLeft = Constants.ONE.equals(slices[8]);
    }

    public Integer getTargetX() {
        return targetX;
    }

    public void setTargetX(Integer targetX) {
        this.targetX = targetX;
    }

    public Integer getTargetY() {
        return targetY;
    }

    public void setTargetY(Integer targetY) {
        this.targetY = targetY;
    }

    public Boolean getLeftActionButton() {
        return leftActionButton;
    }

    public void setLeftActionButton(Boolean leftActionButton) {
        this.leftActionButton = leftActionButton;
    }

    public Boolean getRightActionButton() {
        return rightActionButton;
    }

    public void setRightActionButton(Boolean rightActionButton) {
        this.rightActionButton = rightActionButton;
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
