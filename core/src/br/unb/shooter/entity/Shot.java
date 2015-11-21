package br.unb.shooter.entity;

public class Shot extends Entity {

    public static final Integer VELOCITY = 8;

    private Boolean finish;

    private Double angle;

    private Player player;

    private Integer sequence;

    public Shot() {
        setX(0f);
        setY(0f);
        setHeight(6f);
        setWidth(6f);
        finish = false;
        sequence = 0;
    }

    public void create(Player player, Integer sequence) {

        Integer xOffset = 0;
        if (player.getFacing() == 0) {
            xOffset = 15;
        } else {
            xOffset = 27;
        }

        Float playerXCentered = player.getX() + xOffset;
        Float playerYCentered = player.getY() + 9 + 11;

        Float mouseX = player.getTargetX();
        Float mouseY = player.getTargetY();

        Float deltaX = (mouseX - playerXCentered);
        Float deltaY = (mouseY - playerYCentered);

        Double angle = Math.atan2(deltaY.doubleValue(), deltaX.doubleValue());

        Float x = (float) Math.cos(angle.floatValue());
        Float y = (float) Math.sin(angle.floatValue());

        setAngle(angle);
        setX(playerXCentered + x * 39);
        setY(playerYCentered + y * 39);
        setPlayer(player);
        setSequence(sequence);

        this.finish = false;
    }

    public void update() {
        // TODO: fix shot end
        Double x = getX() + VELOCITY * Math.cos(angle);
        Double y = getY() + VELOCITY * Math.sin(angle);
        setX(x.floatValue());
        setY(y.floatValue());
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
