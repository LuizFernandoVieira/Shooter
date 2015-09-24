package br.unb.shooter.entity;

import br.unb.shooter.util.Constants;

public class TargetMark extends Entity {

    public TargetMark() {
        this.positionX = 0;
        this.positionY = 0;
        this.width = 12;
        this.height = 12;
    }

    public void update(int screenX, int screenY) {
        this.positionX = screenX;
        this.positionY = Constants.CAMERA_HEIGHT - screenY;
    }

}
