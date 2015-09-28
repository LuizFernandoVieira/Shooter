package br.unb.shooter.entity;

import br.unb.shooter.util.Constants;

public class TargetMark extends Entity {

    public TargetMark() {
        this.positionX = 0f;
        this.positionY = 0f;
        this.width = 12;
        this.height = 12;
    }

    public void update(float screenX, float screenY) {
        this.positionX = screenX;
        this.positionY = Constants.CAMERA_HEIGHT - screenY;
    }

}
