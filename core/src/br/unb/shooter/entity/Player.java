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

    private Float velocity;

    private Integer facing;
    private Integer previousFacing;

    private Float targetX;

    private Float targetY;

    private Float oldTargetX;

    private Float oldTargetY;

    private Boolean isMoving;

    private Boolean isChangingState;

    private Boolean isTargetMoving;

    private Boolean isShooting;

    private Float startX;

    private Float startY;

    private Float offsetX;

    private Float offsetY;

    /**
     * Constructor.
     */
    public Player() {
        moveUp = false;
        moveRight = false;
        moveDown = false;
        moveLeft = false;
        targetX = 0f;
        targetY = 0f;
        isMoving = false;
        isChangingState = false;
        isShooting = false;
        velocity = Constants.PLAYER_VELOCITY;
        x = 0f;
        y = 0f;
        width = 0f;
        height = 0f;
        facing = 0;
        offsetX = 0f;
        offsetY = 0f;
        startX = 0f;
        startY = 0f;
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
     */
    public void setFacing() {
        Float offsetX = targetX - (x + width / 2);

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
            setY(getY() + velocity);
        }
        if (moveDown) {
            setY(getY() - velocity);
        }
        if (moveRight) {
            setX(getX() + velocity);
        }
        if (moveLeft) {
            setX(getX() - velocity);
        }

        setFacing();

        if (targetX != oldTargetX || targetY != oldTargetY) {
            isTargetMoving = true;
        } else {
            isTargetMoving = false;
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

    public void saveOldTargetXY() {
        oldTargetX = targetX;
        oldTargetY = targetY;
    }

    public Float getStartX() {
        return startX;
    }

    public void setStartX(Float startX) {
        this.startX = startX;
    }

    public Float getStartY() {
        return startY;
    }

    public void setStartY(Float startY) {
        this.startY = startY;
    }

    public Float getVelocity() {
        return velocity;
    }

    public void setVelocity(Float velocity) {
        this.velocity = velocity;
    }

    public Float getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(Float offsetX) {
        this.offsetX = offsetX;
    }

    public Float getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(Float offsetY) {
        this.offsetY = offsetY;
    }

    public Boolean getIsTargetMoving() {
        return isTargetMoving;
    }

    public void setIsTargetMoving(Boolean isTargetMoving) {
        this.isTargetMoving = isTargetMoving;
    }

    public Float getOldTargetX() {
        return oldTargetX;
    }

    public void setOldTargetX(Float oldTargetX) {
        this.oldTargetX = oldTargetX;
    }

    public Float getOldTargetY() {
        return oldTargetY;
    }

    public void setOldTargetY(Float oldTargetY) {
        this.oldTargetY = oldTargetY;
    }

}
