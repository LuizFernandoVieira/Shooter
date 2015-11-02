package br.unb.shooter.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import br.unb.shooter.controller.GameController;
import br.unb.shooter.util.Constants;

public class FireWeapon extends Weapon{
    
    private Double angle;
    
    public FireWeapon() {
        setWidth(39);
        setHeight(28);
        setAngle(0.0);
    }

    @Override
    public void update() {
        setPositionX(getOwner().getPositionX());
        setPositionY(getOwner().getPositionY());
        
        GameController.getInstance().getPlayer().setTargetX(Float.valueOf(GameController.getInstance().getMouseX()));
        GameController.getInstance().getPlayer().setTargetY(Float.valueOf(GameController.getInstance().getMouseY()));

        // isso fica temporariamente ate colocarmos a logica da mira nos inimigos
        if(getOwner() != null) {
            if(getOwner() instanceof Player) {
                Player p = (Player) getOwner();
                Float playerXCentered = p.getPositionX() + (p.getWidth() / 2);
                Float playerYCentered = p.getPositionY() + (p.getHeight() / 2);

                Float mouseXCorrected = p.getTargetX();
                Float mouseYCorrected = (Constants.CAMERA_HEIGHT) - p.getTargetY();

                Float deltaX = (mouseXCorrected - playerXCentered);
                Float deltaY = (mouseYCorrected - playerYCentered);

                Double angle = Math.atan2(deltaY.doubleValue(), deltaX.doubleValue());

                setAngle(Math.toDegrees(angle));
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
    
}
