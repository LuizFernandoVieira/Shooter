package br.unb.shooter.entity;

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
    private Integer previousFacing;

    private Float targetX;

    private Float targetY;

    private Boolean isMoving;

    private Boolean isChangingState;

    private Boolean isShooting;

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
        isShooting = false;
        movingOffset = 2;
        positionX = 0f;
        positionY = 0f;
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
    public void setFacing(Float x, Float y) {
        Float offsetX = x - (positionX + width / 2);

        previousFacing = facing;

        if (offsetX > 0) {
            facing = 0;
        } else {
            facing = 1;
        }
        if (facing != previousFacing) {
            isChangingState = true;
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

    public Boolean getIsShooting() {
        return isShooting;
    }

    public void setIsShooting(Boolean isShooting) {
        this.isShooting = isShooting;
    }

    public Float getTargetX() {
        return targetX;
    }

    public void setTargetX(Float targetX) {
        this.targetX = targetX;
    }

    public Float getTargetY() {
        return targetY;
    }

    public void setTargetY(Float targetY) {
        this.targetY = targetY;
    }

}
