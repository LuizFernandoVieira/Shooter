package br.unb.shooter.entity;

import br.unb.shooter.util.Constants;

public class Shot extends Entity {

	public static final Float VELOCITY = Constants.SHOT_VELOCITY;

	public static final Float DISTANCE = Constants.SHOT_DISTANCE;

	private Boolean finish;

	private Double angle;

	private Player player;

	private Integer sequence;

	private Float startX;

	private Float startY;

	private Float endX;

	private Float endY;

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
		setStartX(getX());
		setStartY(getY());

		Double endX = getStartX() + DISTANCE * Math.cos(angle);
		Double endY = getStartY() + DISTANCE * Math.sin(angle);
		setEndX(endX.floatValue());
		setEndY(endY.floatValue());

		this.finish = false;
	}

	public void update() {
		Double x = getX() + VELOCITY * Math.cos(angle);
		Double y = getY() + VELOCITY * Math.sin(angle);
		setX(x.floatValue());
		setY(y.floatValue());

		boolean finishX = false;
		boolean finishY = false;

		if (endX > startX && x > endX) {
			finishX = true;
		}
		if (endX < startX && x < endX) {
			finishX = true;
		}
		if (endY > startY && y > endY) {
			finishY = true;
		}
		if (endY < startY && y < endY) {
			finishY = true;
		}
		if (finishX && finishY) {
			finish = true;
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

	public Float getStartX() {
		return startX;
	}

	public void setStartX(Float startX) {
		this.startX = startX;
	}

	public Float getStartY() {
		return startY;
	}

	public void setStartY(Float startY) {
		this.startY = startY;
	}

	public Float getEndX() {
		return endX;
	}

	public void setEndX(Float endX) {
		this.endX = endX;
	}

	public Float getEndY() {
		return endY;
	}

	public void setEndY(Float endY) {
		this.endY = endY;
	}

}
