package br.unb.shooter.entity;

import br.unb.shooter.controller.GameController;
import br.unb.shooter.util.Constants;

public class Shot extends Entity {

    public static final Integer VELOCITY = 8;

    private Boolean finish;

    private Double angle;

    private Player player;

    private Integer sequence;

    public Shot() {
        setPositionX(0f);
        setPositionY(0f);
        setHeight(6f);
        setWidth(6f);
        finish = false;
        sequence = 0;
    }

    public void create(Player player, Integer sequence) {
        Float playerXCentered = player.getPositionX() + player.getOffsetX();
        Float playerYCentered = player.getPositionY() + player.getOffsetY();

        Float mapX = GameController.getInstance().getMovement().getMap().getPositionX();
        Float mapY = GameController.getInstance().getMovement().getMap().getPositionY();

        Float mouseXCorrected = player.getTargetX() + mapX;
        Float mouseYCorrected = (Constants.CAMERA_HEIGHT - player.getTargetY()) + mapY;

        Float deltaX = (mouseXCorrected - playerXCentered);
        Float deltaY = (mouseYCorrected - playerYCentered);

        Double angle = Math.atan2(deltaY.doubleValue(), deltaX.doubleValue());
        
        Float x = (float) Math.cos(angle.floatValue());
        Float y = (float) Math.sin(angle.floatValue());

        setAngle(angle);
        setPositionX(playerXCentered + x*39);
        setPositionY(playerYCentered + y*39);
        setPlayer(player);
        setSequence(sequence);

        this.finish = false;
    }

    public void update() {
        Float mapX = GameController.getInstance().getMovement().getMap().getPositionX();
        Float mapY = GameController.getInstance().getMovement().getMap().getPositionY();

        if (getPositionX() < 0 || getPositionX() > (mapX + Constants.CAMERA_WIDTH) || getPositionY() < 0
                || getPositionY() > (mapY + Constants.CAMERA_HEIGHT)) {
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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

}
