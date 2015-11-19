package br.unb.shooter.entity;

public class Entity {

    private Integer id;
    protected Float positionX;
    protected Float positionY;
    protected Float screenX;
    protected Float screenY;
    protected Float width;
    protected Float height;

    public Entity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getPositionX() {
        return positionX;
    }

    public void setPositionX(Float positionX) {
        this.positionX = positionX;
    }

    public Float getPositionY() {
        return positionY;
    }

    public void setPositionY(Float positionY) {
        this.positionY = positionY;
    }

    public Float getWidth() {
        return width;
    }

    public void setWidth(Float width) {
        this.width = width;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getScreenX() {
        return screenX;
    }

    public void setScreenX(Float screenX) {
        this.screenX = screenX;
    }

    public Float getScreenY() {
        return screenY;
    }

    public void setScreenY(Float screenY) {
        this.screenY = screenY;
    }

}
