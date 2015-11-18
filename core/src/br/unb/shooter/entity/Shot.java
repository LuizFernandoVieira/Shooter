package br.unb.shooter.entity;

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
        Float playerXCentered = player.getPositionX() + (player.getWidth() / 2);
        Float playerYCentered = player.getPositionY() + (player.getHeight() / 2);

        // Float mouseXCorrected = player.getTargetX();
        // Float mouseYCorrected = (Constants.CAMERA_HEIGHT -
        // player.getTargetY());

        Float mouseXCorrected = player.getTargetX() + (player.getPositionX() - player.getScreenX());
        Float mouseYCorrected = (Constants.CAMERA_HEIGHT - player.getTargetY())
                + (player.getPositionY() - player.getScreenY());

        Float deltaX = (mouseXCorrected - playerXCentered);
        Float deltaY = (mouseYCorrected - playerYCentered);

        Double angle = Math.atan2(deltaY.doubleValue(), deltaX.doubleValue());

        setAngle(angle);
        setPositionX(playerXCentered);
        setPositionY(playerYCentered);
        setPlayer(player);
        setSequence(sequence);

//          System.out.println(player.getPositionX() - player.getScreenX());
//        System.out.println("mira: " + (player.getTargetX() + (player.getPositionX() - player.getScreenX())));
//        System.out.println("weapon: " + player.getWeapon().getPositionX());
        
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
            System.out.println(getPositionX());
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
