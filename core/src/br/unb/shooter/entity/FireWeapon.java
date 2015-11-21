package br.unb.shooter.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FireWeapon extends Weapon {

    private Double angle;
    private Integer facing;

    private float xOffset = 15f;
    private float yOffset = 9f;

    public FireWeapon() {
        setWidth(39f);
        setHeight(28f);
        setAngle(0.0);
        setFacing(0);
    }

    @Override
    public void update() {

        if (getOwner() != null) {

            if (getFacing() == 0) {
                xOffset = 15;
            } else {
                xOffset = 27;
            }

            setX(getOwner().getX() + xOffset);
            setY(getOwner().getY() + yOffset);

            Float deltaX = 0f;
            Float deltaY = 0f;

            if (getOwner() instanceof Player) {
                Player p = (Player) getOwner();
                Float playerXCentered = p.getX() + (p.getWidth() / 2);
                Float playerYCentered = p.getY() + (p.getHeight() / 2);

                Float mouseX = p.getTargetX();
                Float mouseY = p.getTargetY();

                deltaX = (mouseX - playerXCentered);
                deltaY = (mouseY - playerYCentered);

                setFacing(p.getFacing());
            }

            Double angle = Math.atan2(deltaY.doubleValue(), deltaX.doubleValue());

            setAngle(Math.toDegrees(angle));

        }
    }

    @Override
    public void render(SpriteBatch batch) {

    }

    public Double getAngle() {
        return angle;
    }

    public void setAngle(Double angle) {
        this.angle = angle;
    }

    public Integer getFacing() {
        return facing;
    }

    public void setFacing(Integer facing) {
        this.facing = facing;
    }

    public float getxOffset() {
        return xOffset;
    }

    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }

    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }

}
