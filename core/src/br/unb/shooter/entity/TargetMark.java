package br.unb.shooter.entity;

import br.unb.shooter.util.Constants;

public class TargetMark extends Entity {

    public TargetMark() {
        this.positionX = 0f;
        this.positionY = 0f;
        this.width = 12f;
        this.height = 12f;
    }

    public void update(float screenX, float screenY) {
        this.positionX = screenX;
        this.positionY = Constants.CAMERA_HEIGHT - screenY;
    }

}
