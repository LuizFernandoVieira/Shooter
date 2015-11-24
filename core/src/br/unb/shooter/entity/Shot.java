package br.unb.shooter.entity;

import br.unb.shooter.util.Constants;

public class Shot extends Entity {

	public static final Float VELOCITY = Constants.SHOT_VELOCITY;

	private Boolean finish;

	private Double angle;

	private Player player;

	private Integer sequence;

	public Shot() {
		setX(0f);
		setY(0f);
		setHeight(Constants.SHOT_HEIGHT);
		setWidth(Constants.SHOT_WIDTH);
		finish = false;
		sequence = 0;
	}

	public void create(Player player, Integer sequence) {

		Float xOffset = 0f;
		if (player.getFacing() == 0) {
			xOffset = Constants.WEAPON_OFFSET_FROM_PLAYER_X_FACING_RIGHT;
		} else {
			xOffset = Constants.WEAPON_OFFSET_FROM_PLAYER_X_FACING_LEFT;
		}

		Float playerXCentered = player.getX() + xOffset;
		Float playerYCentered = player.getY();

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
