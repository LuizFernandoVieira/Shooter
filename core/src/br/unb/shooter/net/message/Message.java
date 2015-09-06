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

        if (code.equals(MessageEnum.INPUT.getId())) {
            return new InputMessage(message);
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
