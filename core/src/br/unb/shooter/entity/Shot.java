package br.unb.shooter.entity;

import br.unb.shooter.util.Constants;

public class Shot extends Entity {

    private static final Integer VELOCITY = 8;

    private Boolean finish;

    private Double angle;

    public Shot() {
        setPositionX(0);
        setPositionY(0);
        setHeight(6);
        setWidth(6);
        finish = false;
    }

    public void create(Player player, Integer x, Integer y) {
        setPositionX(x);
        setPositionY(y);
        this.finish = false;
    }

    public void update() {
        if (getPositionX() < 0 || getPositionX() > Constants.CAMERA_WIDTH || getPositionY() < 0
                || getPositionY() > Constants.CAMERA_HEIGHT) {
            this.finish = true;
        } else {
            setPositionX(getPositionX() + Double.valueOf(VELOCITY * Math.cos(angle)).intValue());
            setPositionY(getPositionY() + Double.valueOf(VELOCITY * Math.sin(angle)).intValue());
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
