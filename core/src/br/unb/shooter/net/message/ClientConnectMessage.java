package br.unb.shooter.net.message;

import br.unb.shooter.controller.GameController;
import br.unb.shooter.controller.NetController;
import br.unb.shooter.util.Constants;

public class ClientConnectMessage extends Message {

    private String name;

    public ClientConnectMessage(String message) {
        this.id = MessageEnum.CLIENT_CONNECT.getId();
        translate(message);
    }

    public ClientConnectMessage() {
        this.id = MessageEnum.CLIENT_CONNECT.getId();
    }

    private void translate(String message) {
        String[] slices = message.split(Constants.SPACE);

        this.id = slices[0];
        this.name = slices[1];
    }

    @Override
    public void execute() {
        GameController.getInstance().createClientPlayer(name);

        NetController.getInstance().updateLobby();
    }

    @Override
    public String toString() {
        return this.id + Constants.SPACE + this.name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
