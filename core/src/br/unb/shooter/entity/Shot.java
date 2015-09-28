package br.unb.shooter.entity;

import br.unb.shooter.util.Constants;

public class Shot extends Entity {

    public static final Integer VELOCITY = 8;

    private Boolean finish;

    private Double angle;

    public Shot() {
        setPositionX(0f);
        setPositionY(0f);
        setHeight(6);
        setWidth(6);
        finish = false;
    }

    public void create(Player player, Float mouseX, Float mouseY) {
        Float playerXCentered = player.getPositionX() + (player.getWidth() / 2);
        Float playerYCentered = player.getPositionY() + (player.getHeight() / 2);

        Float mouseXCorrected = mouseX;
        Float mouseYCorrected = (Constants.CAMERA_HEIGHT - mouseY);

        Float deltaX = (mouseXCorrected - playerXCentered);
        Float deltaY = (mouseYCorrected - playerYCentered);

        Double angle = Math.atan2(deltaY.doubleValue(), deltaX.doubleValue());

        setAngle(angle);
        setPositionX(playerXCentered);
        setPositionY(playerYCentered);

        this.finish = false;
    }

    public void update() {
        if (getPositionX() < 0 || getPositionX() > Constants.CAMERA_WIDTH || getPositionY() < 0
                || getPositionY() > Constants.CAMERA_HEIGHT) {
            this.finish = true;
        } else {
            Double x = getPositionX() + VELOCITY * Math.cos(angle);
            Double y = getPositionY() + VELOCITY * Math.sin(angle);
            setPositionX(x.floatValue());
            setPositionY(y.floatValue());
        }
    }

    public Boolean getFinish() {
        return this.finish;
    }

    public Double getAngle() {
        return angle;
    }

    public void setAngle(Double angle) {
        this.angle = angle;
    }

    public void setFinish(Boolean finish) {
        this.finish = finish;
    }

}
