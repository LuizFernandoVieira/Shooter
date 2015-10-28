package br.unb.shooter.net.message;

import com.badlogic.gdx.utils.TimeUtils;

import br.unb.shooter.controller.NetController;
import br.unb.shooter.util.Constants;

public abstract class Message {

	protected String id;

	protected Integer sequence;

	protected Long timestamp;

	protected Boolean isExecuted;

	/**
	 * Constructor.
	 */
	public Message() {
		this.sequence = NetController.getInstance().getMessageQueue().nextSequence();
		this.timestamp = TimeUtils.millis();
		this.isExecuted = false;
	}

	/**
	 * Instantiate a message object.
	 * 
	 * @param message
	 *            message
	 * @return Message
	 */
	public static Message makeMessage(String message) {
		Message msg = null;

		String code = message.indexOf(Constants.SPACE) > -1 ? message.substring(0, message.indexOf(Constants.SPACE))
				: message;

		if (code.equals(MessageEnum.CLIENT_INPUT.getId())) {
			return new ClientInputMessage(message);
		} else if (code.equals(MessageEnum.CLIENT_CONNECT.getId())) {
			return new ClientConnectMessage(message);
		} else if (code.equals(MessageEnum.SERVER_UPDATE_LOBBY.getId())) {
			return new ServerUpdateLobbyMessage(message);
		} else if (code.equals(MessageEnum.SERVER_START.getId())) {
			return new ServerStartMessage(message);
		} else if (code.equals(MessageEnum.SERVER_UPDATE.getId())) {
			return new ServerUpdateMessage(message);
		}

		return msg;
	}

	public abstract void execute();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Boolean getIsExecuted() {
		return isExecuted;
	}

	public void setIsExecuted(Boolean isExecuted) {
		this.isExecuted = isExecuted;
	}

}
