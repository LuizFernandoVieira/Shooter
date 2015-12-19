package br.unb.shooter.entity;

import br.unb.shooter.util.Constants;

public class HealthBar extends Entity {
    private Float maxWidth;

    public HealthBar() {
        this.width = Constants.BEAR_HEALTH_WIDTH;
        this.maxWidth = Constants.BEAR_HEALTH_WIDTH;
        this.height = Constants.BEAR_HEALTH_HEIGHT;
    }

    public Float getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(Float maxWidth) {
        this.maxWidth = maxWidth;
    }

    public void update(Enemy enemy) {
        this.x = enemy.getX() + Constants.BEAR_HEALTH_OFFSET_X;
        this.y = enemy.getY() + Constants.BEAR_HEALTH_OFFSET_Y;
        this.width = (Constants.BEAR_HEALTH_WIDTH * enemy.getHealth()) / Constants.BEAR_HEALTH;
    }
}
