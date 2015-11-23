package br.unb.shooter.entity;

public class TargetMark extends Entity {

    public static final Float MARK_OFFSET_X = 16f;

    public static final Float MARK_OFFSET_Y = 16f;

    private Boolean moveUp;
    private Boolean moveRight;
    private Boolean moveDown;
    private Boolean moveLeft;

    private Float velocity;

    private Float intensityX;

    private Float intensityY;

    private Float startX;

    private Float startY;

    private Float offsetX;

    private Float offsetY;

    public TargetMark() {
        moveUp = false;
        moveRight = false;
        moveDown = false;
        moveLeft = false;
        velocity = 20f;
        intensityX = 1f;
        intensityY = 1f;
        x = 0f;
        y = 0f;
        width = 32f;
        height = 32f;
        offsetX = MARK_OFFSET_X;
        offsetY = MARK_OFFSET_Y;
        startX = 0f;
        startY = 0f;
    }

    /**
     * Updates the target.
     */
    public void update() {
        if (moveUp) {
            setY(getY() + (velocity * Math.abs(intensityY)));
            // Damping
            if (intensityY < -0.01f) {
                intensityY = intensityY * 0.9f;
            } else {
                intensityY = 0f;
                moveUp = false;
            }
        }
        if (moveDown) {
            setY(getY() - (velocity * Math.abs(intensityY)));
            // Damping
            if (intensityY > 0.01f) {
                intensityY = intensityY * 0.9f;
            } else {
                intensityY = 0f;
                moveDown = false;
            }
        }
        if (moveRight) {
            setX(getX() + (velocity * Math.abs(intensityX)));
            // Damping
            if (intensityX > 0.01f) {
                intensityX = intensityX * 0.9f;
            } else {
                intensityX = 0f;
                moveRight = false;
            }
        }
        if (moveLeft) {
            setX(getX() - (velocity * Math.abs(intensityX)));
            // Damping
            if (intensityX < -0.01f) {
                intensityX = intensityX * 0.9f;
            } else {
                intensityX = 0f;
                moveLeft = false;
            }
        }

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

    public Float getVelocity() {
        return velocity;
    }

    public void setVelocity(Float velocity) {
        this.velocity = velocity;
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

    public Float getIntensityX() {
        return intensityX;
    }

    public void setIntensityX(Float intensityX) {
        this.intensityX = intensityX;
    }

    public Float getIntensityY() {
        return intensityY;
    }

    public void setIntensityY(Float intensityY) {
        this.intensityY = intensityY;
    }

}
