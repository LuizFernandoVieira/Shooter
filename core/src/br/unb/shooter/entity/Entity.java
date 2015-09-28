package br.unb.shooter.entity;

public class Entity {

    private Integer id;
    protected Float positionX;
    protected Float positionY;
    protected Integer width;
    protected Integer height;

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

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

}
