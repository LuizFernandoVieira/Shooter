package br.unb.shooter.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import br.unb.shooter.controller.GameController;
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

        if (getOwner() != null) {

            if (getFacing() == 0) {
                xOffset = 15;
            } else {
                xOffset = 27;
            }

            setPositionX(getOwner().getPositionX() + xOffset);
            setPositionY(getOwner().getPositionY() + yOffset);

             GameController.getInstance().getPlayer().setTargetX(Float.valueOf(GameController.getInstance().getMouseX()));
             GameController.getInstance().getPlayer().setTargetY(Float.valueOf(GameController.getInstance().getMouseY()));
            
             Float mapX =
             GameController.getInstance().getMovement().getMap().getPositionX();
             Float mapY =
             GameController.getInstance().getMovement().getMap().getPositionY();
            
             Float deltaX = 0f;
             Float deltaY = 0f;
            
             if (getOwner() instanceof Player) {
             Player p = (Player) getOwner();
             Float playerXCentered = p.getPositionX() + (p.getWidth() / 2);
             Float playerYCentered = p.getPositionY() + (p.getHeight() / 2);
            
             Float mouseXCorrected = p.getTargetX() + mapX;
             Float mouseYCorrected = (Constants.CAMERA_HEIGHT -
             p.getTargetY()) + mapY;
            
             deltaX = (mouseXCorrected - playerXCentered);
             deltaY = (mouseYCorrected - playerYCentered);
            
             setFacing(p.getFacing());
             }
             if (getOwner() instanceof Enemy) {
             Enemy e = (Enemy) getOwner();
             Float enemyXCentered = e.getPositionX() + (e.getWidth() / 2);
             Float enemyYCentered = e.getPositionY() + (e.getHeight() / 2);
            
             Float mouseXCorrected = e.getTargetX() + mapX;
             Float mouseYCorrected = (Constants.CAMERA_HEIGHT -
             e.getTargetY()) + mapY;
            
             deltaX = (mouseXCorrected - enemyXCentered);
             deltaY = (mouseYCorrected - enemyYCentered);
            
             setFacing(e.getFacing());
             }
            
             Double angle = Math.atan2(deltaY.doubleValue(),
             deltaX.doubleValue());
            
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
