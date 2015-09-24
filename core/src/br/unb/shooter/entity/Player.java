package br.unb.shooter.entity;

import br.unb.shooter.util.Constants;

public class Player extends Entity {

    private Integer health;
    private Boolean moveUp;
    private Boolean moveRight;
    private Boolean moveDown;
    private Boolean moveLeft;
    private String name;
    private Integer connectionId;
    private Integer movingOffset;
    private Integer facing;

    private Boolean isMoving;

    private Boolean isChangingState;

    /**
     * Constructor.
     */
    public Player() {
        moveUp = false;
        moveRight = false;
        moveDown = false;
        moveLeft = false;
        isMoving = false;
        isChangingState = false;
        movingOffset = 2;
        positionX = 0;
        positionY = 0;
        width = 0;
        height = 0;
        facing = 0;
    }

    /**
     * Set moving status.
     */
    public void setMovingState() {
        if (moveUp || moveRight || moveDown || moveLeft) {
            isMoving = true;
        } else {
            isMoving = false;
        }
        isChangingState = true;
    }

    /**
     * Set facing.
     * 
     * @param x
     * @param y
     */
    public void setFacing(Integer x, Integer y) {
        Integer offsetX = x - positionX;
        Integer offsetY = (Constants.CAMERA_HEIGHT - y) - positionY;

        Integer absX = Math.abs(offsetX);
        Integer absY = Math.abs(offsetY);

        if (offsetY > 0 && absX < absY) {
            facing = 0;
        }
        if (offsetX > 0 && absX > absY) {
            facing = 1;
        }
        if (offsetY < 0 && absX < absY) {
            facing = 2;
        }
        if (offsetX < 0 && absX > absY) {
            facing = 3;
        }
    }

    /**
     * Updates player.
     */
    public void update() {
        if (moveUp) {
            setPositionY(getPositionY() + movingOffset);
        }
        if (moveDown) {
            setPositionY(getPositionY() - movingOffset);
        }
        if (moveRight) {
            setPositionX(getPositionX() + movingOffset);
        }
        if (moveLeft) {
            setPositionX(getPositionX() - movingOffset);
        }
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public Boolean getMoveUp() {
        return moveUp;
    }

    public void setMoveUp(Boolean moveUp) {
        this.moveUp = moveUp;
    }

    public Boolean getMoveRight() {
        return moveRight;
    }

    public void setMoveRight(Boolean moveRight) {
        this.moveRight = moveRight;
    }

    public Boolean getMoveDown() {
        return moveDown;
    }

    public void setMoveDown(Boolean moveDown) {
        this.moveDown = moveDown;
    }

    public Boolean getMoveLeft() {
        return moveLeft;
    }

    public void setMoveLeft(Boolean moveLeft) {
        this.moveLeft = moveLeft;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(Integer connectionId) {
        this.connectionId = connectionId;
    }

    public Boolean getIsMoving() {
        return isMoving;
    }

    public void setIsMoving(Boolean isMoving) {
        this.isMoving = isMoving;
    }

    public Boolean getIsChangingState() {
        return isChangingState;
    }

    public void setIsChangingState(Boolean isChangingState) {
        this.isChangingState = isChangingState;
    }

    @Override
    public String toString() {
        return this.name == null ? "" : this.name;
    }

    public Integer getFacing() {
        return facing;
    }

    public void setFacing(Integer facing) {
        this.facing = facing;
    }

}
