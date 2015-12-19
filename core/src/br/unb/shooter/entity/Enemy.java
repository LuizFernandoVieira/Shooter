package br.unb.shooter.entity;

import br.unb.shooter.controller.GameController;
import br.unb.shooter.util.Constants;

public class Enemy extends Entity {

    private Integer health;

    private Boolean moveUp;
    private Boolean moveRight;
    private Boolean moveDown;
    private Boolean moveLeft;

    private String name;

    private Integer velocity;

    private Integer facing;
    private Integer previousFacing;

    private Float targetX;

    private Float targetY;

    private Boolean isMoving;

    private Boolean isChangingState;

    private Boolean isShooting;

    private Weapon weapon;

    private Float startX;

    private Float startY;

    private float stateTime;

    public Enemy() {
        health = Constants.BEAR_HEALTH;
        moveUp = false;
        moveRight = false;
        moveDown = false;
        moveLeft = false;
        targetX = 0f;
        targetY = 0f;
        isMoving = false;
        isChangingState = false;
        isShooting = false;
        velocity = 3;
        x = 0f;
        y = 0f;
        width = 0f;
        height = 0f;
        facing = 0;
        weapon = new FireWeapon();
        weapon.setOwner(this);
        startX = 0f;
        stateTime = 0f;
    }

    public void setMovingState() {
        if (moveUp || moveRight || moveDown || moveLeft) {
            isMoving = true;
        } else {
            isMoving = false;
        }
        isChangingState = true;
    }

    public void setFacing() {
        Float offsetX = GameController.getInstance().getPlayer().getX() - (x + width / 2);

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

    public void update() {

        if (health <= 0) {
            // GameController.getInstance().getEnemiesMap().remo;
        }

        Player player = GameController.getInstance().getPlayer();

        Float playerXCentered = player.getX();
        Float playerYCentered = player.getY();

        Float enemyX = this.getX();
        Float enemyY = this.getY();

        Float deltaX = (enemyX - playerXCentered);
        Float deltaY = (enemyY - playerYCentered);

        Float distance = (float) Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));

        if (distance < Constants.BEAR_ATTACK_RANGE) {
            if (!this.isMoving) {
                stateTime = 0;
            }
            this.isMoving = true;
        } else {
            if (this.isMoving) {
                stateTime = 0;
            }
            this.isMoving = false;
        }

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

    public Integer getVelocity() {
        return velocity;
    }

    public void setVelocity(Integer velocity) {
        this.velocity = velocity;
    }

    public Integer getFacing() {
        return facing;
    }

    public void setFacing(Integer facing) {
        this.facing = facing;
    }

    public Integer getPreviousFacing() {
        return previousFacing;
    }

    public void setPreviousFacing(Integer previousFacing) {
        this.previousFacing = previousFacing;
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

    public Boolean getIsShooting() {
        return isShooting;
    }

    public void setIsShooting(Boolean isShooting) {
        this.isShooting = isShooting;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
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

    public float getStateTime() {
        return stateTime;
    }

    public void setStateTime(float stateTime) {
        this.stateTime = stateTime;
    }

}
