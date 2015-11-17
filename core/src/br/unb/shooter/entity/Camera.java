package br.unb.shooter.entity;

public class Camera extends Entity {

    private Float startX;

    private Float startY;

    public Camera() {
        startX = 0f;
        startY = 0f;
        positionX = 0f;
        positionY = 0f;
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

}
