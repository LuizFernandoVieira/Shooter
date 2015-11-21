package br.unb.shooter.entity;

public class TargetMark extends Entity {

    public TargetMark() {
        this.x = 0f;
        this.y = 0f;
        this.width = 12f;
        this.height = 12f;
    }

    public void update(float mapX, float mapY) {
        this.x = mapX;
        this.y = mapY;
    }

}
