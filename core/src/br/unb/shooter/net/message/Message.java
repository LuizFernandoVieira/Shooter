package br.unb.shooter.net.message;

import br.unb.shooter.util.Constants;

public abstract class Message {

    protected String id;

    public static Message makeMessage(String message) {
        Message m = null;

        String code = message.indexOf(Constants.SPACE) > -1 ? message.substring(0, message.indexOf(Constants.SPACE))
                : message;

        if (code.equals(MessageEnum.INPUT.getId())) {
            return new InputMessage(message);
        }

        return m;
    }

    public abstract void execute();

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

}
