package br.unb.shooter.net.message;

import br.unb.shooter.util.Constants;

public abstract class Message {

    protected String id;

    /**
     * Instantiate a message object.
     * 
     * @param message message
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

}
