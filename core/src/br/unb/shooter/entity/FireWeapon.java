package br.unb.shooter.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import br.unb.shooter.util.Constants;

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

        if (getFacing() == 0) {
            xOffset = 15;
        } else {
            xOffset = 27;
        }

        setPositionX(getOwner().getPositionX() + xOffset);
        setPositionY(getOwner().getPositionY() + yOffset);

        // isso fica temporariamente ate colocarmos a logica da mira nos
        // inimigos
        if (getOwner() != null) {
            if (getOwner() instanceof Player) {
                Player p = (Player) getOwner();
                Float playerXCentered = p.getPositionX() + (p.getWidth() / 2);
                Float playerYCentered = p.getPositionY() + (p.getHeight() / 2);

                Float mouseXCorrected = p.getTargetX();
                Float mouseYCorrected = (Constants.CAMERA_HEIGHT) - p.getTargetY();

                Float deltaX = (mouseXCorrected - playerXCentered);
                Float deltaY = (mouseYCorrected - playerYCentered);

                Double angle = Math.atan2(deltaY.doubleValue(), deltaX.doubleValue());

                setAngle(Math.toDegrees(angle));
                setFacing(p.getFacing());
            }
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

}
