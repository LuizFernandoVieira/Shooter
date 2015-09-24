package br.unb.shooter.entity;

import br.unb.shooter.util.Constants;

public class Shot extends Entity {

    private static final Integer VELOCITY = 4;

    private Integer facing;

    private Boolean finish;

    public Shot() {
        setPositionX(0);
        setPositionY(0);
        setHeight(6);
        setWidth(6);

        facing = 0;
    }

    public void create(Integer facing, Integer x, Integer y) {
        this.facing = facing;
        setPositionX(x);
        setPositionY(y);
        this.finish = false;
    }

    public void update() {
        if (facing == 0) {
            setPositionY(getPositionY() + VELOCITY);
        }
        if (facing == 1) {
            setPositionX(getPositionX() + VELOCITY);
        }
        if (facing == 2) {
            setPositionY(getPositionY() - VELOCITY);
        }
        if (facing == 3) {
            setPositionX(getPositionX() - VELOCITY);
        }
        if (getPositionX() < 0 || getPositionX() > Constants.CAMERA_WIDTH || getPositionY() < 0
                || getPositionY() > Constants.CAMERA_HEIGHT) {
            this.finish = true;
        }
    }

    public Boolean getFinish() {
        return this.finish;
    }
}
