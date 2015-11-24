package br.unb.shooter.net.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.unb.shooter.controller.GameController;
import br.unb.shooter.entity.Player;
import br.unb.shooter.entity.Shot;
import br.unb.shooter.util.Constants;

public class ServerUpdateMessage extends Message {

	private Integer playersLength;

	private List<Player> players;

	private Integer shotsLength;

	private List<Shot> shots;

	private Long lastInput;

	private Integer removedShotsLength;

	private List<Integer> removedShots;

	/**
	 * Constructor.
	 */
	public ServerUpdateMessage() {
		super();
		this.id = MessageEnum.SERVER_UPDATE.getId();
	}

	/**
	 * Constructor.
	 * 
	 * @param message
	 */
	public ServerUpdateMessage(String message) {
		super();
		this.id = MessageEnum.SERVER_UPDATE.getId();
		translate(message);
	}

	/**
	 * Constructor.
	 * 
	 * @param playersMap
	 *            Player's map
	 */
	public ServerUpdateMessage(HashMap<Integer, Player> playersMap, HashMap<Integer, Shot> shotsMap, Long lastInput,
			List<Integer> removedShots) {
		super();
		this.id = MessageEnum.SERVER_UPDATE.getId();
		this.playersLength = playersMap.size();
		this.players = new ArrayList<Player>();
		this.players.addAll(playersMap.values());
		this.shotsLength = shotsMap.size();
		this.shots = new ArrayList<Shot>();
		this.shots.addAll(shotsMap.values());
		this.lastInput = lastInput;
		this.removedShotsLength = removedShots.size();
		this.removedShots = new ArrayList<Integer>();
		this.removedShots.addAll(removedShots);
	}

	private void translate(String message) {
		String[] slices = message.split(Constants.SPACE);

		this.id = slices[0];

		this.playersLength = Integer.valueOf(slices[1]);

		if (players == null) {
			players = new ArrayList<Player>();
		}

		int offset = 2;

		for (int i = 0; i < playersLength; i++) {
			Player player = new Player();

			player.setId(Integer.valueOf(slices[offset + 0]));
			player.setName(slices[offset + 1]);
			player.setX(Float.valueOf(slices[offset + 2]));
			player.setY(Float.valueOf(slices[offset + 3]));
			player.setTargetX(Float.valueOf(slices[offset + 4]));
			player.setTargetY(Float.valueOf(slices[offset + 5]));
			player.setIsMoving(slices[offset + 6].equals("1") ? true : false);
			player.setFacing(Integer.valueOf(slices[offset + 7]));

			offset += 8;

			this.players.add(player);
		}

		this.shotsLength = Integer.valueOf(slices[offset]);

		if (shots == null) {
			shots = new ArrayList<Shot>();
		}

		offset += 1;

		for (int i = 0; i < shotsLength; i++) {
			Shot shot = new Shot();

			shot.setId(Integer.valueOf(slices[offset + 0]));
			shot.setX(Float.valueOf(slices[offset + 1]));
			shot.setY(Float.valueOf(slices[offset + 2]));
			shot.setAngle(Double.valueOf(slices[offset + 3]));
			shot.setFinish(slices[offset + 4].equals(Constants.ONE));
			shot.setPlayer(new Player());
			shot.getPlayer().setId(Integer.valueOf(slices[offset + 5]));
			shot.setSequence(Integer.valueOf(slices[offset + 6]));

			offset += 7;

			this.shots.add(shot);
		}

		this.lastInput = Long.valueOf(slices[offset]);

		offset += 1;

		this.removedShotsLength = Integer.valueOf(slices[offset]);

		if (removedShots == null) {
			removedShots = new ArrayList<Integer>();
		}

		offset += 1;

		for (int i = 0; i < removedShotsLength; i++) {
			this.removedShots.add(Integer.valueOf(slices[offset]));
			offset += 1;
		}
	}

	@Override
	public String toString() {
		String message = "";

		message = this.id + Constants.SPACE + this.playersLength;

		for (Player player : players) {
			message += (Constants.SPACE + player.getId() + Constants.SPACE + player.getName() + Constants.SPACE
					+ player.getX() + Constants.SPACE + player.getY() + Constants.SPACE + player.getTargetX()
					+ Constants.SPACE + player.getTargetY() + Constants.SPACE + (player.getIsMoving() ? "1" : "0")
					+ Constants.SPACE + player.getFacing());
		}

		message += Constants.SPACE + this.shotsLength;

		for (Shot shot : shots) {
			message += (Constants.SPACE + shot.getId() + Constants.SPACE + shot.getX() + Constants.SPACE + shot.getY()
					+ Constants.SPACE + shot.getAngle() + Constants.SPACE + Constants.convertBoolean(shot.getFinish()))
					+ Constants.SPACE + shot.getPlayer().getId() + Constants.SPACE + shot.getSequence();
		}

		message += Constants.SPACE + this.lastInput;

		message += Constants.SPACE + this.removedShotsLength;

		for (Integer id : removedShots) {
			message += (Constants.SPACE + id.toString());
		}

		return message;
	}

	@Override
	public void execute() {
		// Set current server player status
		for (Player player : players) {
			Player playerOnClient = GameController.getInstance().getPlayersMap().get(player.getId());
			playerOnClient.setX(player.getX());
			playerOnClient.setY(player.getY());
			playerOnClient.setTargetX(player.getTargetX());
			playerOnClient.setTargetY(player.getTargetY());
			playerOnClient.setIsMoving(player.getIsMoving());
			playerOnClient.setFacing(player.getFacing());
		}

		for (Shot shot : shots) {
			Shot shotOnClient = GameController.getInstance().getShotsMap().get(shot.getId());
			if (shotOnClient == null) {
				shotOnClient = new Shot();
				GameController.getInstance().getShotsMap().put(shot.getId(), shot);
			}
			shotOnClient.setX(shot.getX());
			shotOnClient.setY(shot.getY());
			shotOnClient.setAngle(shot.getAngle());
			shotOnClient.setFinish(shot.getFinish());
			shotOnClient.setPlayer(shot.getPlayer());
			shotOnClient.setSequence(shot.getSequence());
		}

		for (Integer id : removedShots) {
			GameController.getInstance().getShotsMap().remove(id);
		}
	}

}
