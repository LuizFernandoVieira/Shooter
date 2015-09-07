package br.unb.shooter.net.message;

import java.util.HashMap;

import br.unb.shooter.controller.GameController;
import br.unb.shooter.entity.GameServer;
import br.unb.shooter.util.Constants;

public class ServerNameMessage extends Message {

    private String clientIp;

    private String serverName;

    private String serverIp;

    public ServerNameMessage() {
        this.id = MessageEnum.SERVER_NAME.getId();
    }

    /**
     * Constructor.
     * 
     * @param clientIp clientIp
     * @param serverName serverName
     * @param serverIp serverIp
     */
    public ServerNameMessage(String clientIp, String serverName, String serverIp) {
        this.id = MessageEnum.SERVER_NAME.getId();
        this.clientIp = clientIp;
        this.serverName = serverName;
        this.serverIp = serverIp;
    }

    public ServerNameMessage(String message) {
        this.id = MessageEnum.SERVER_NAME.getId();
        translate(message);
    }

    private void translate(String message) {
        String[] slices = message.split(Constants.SPACE);

        this.id = slices[0];

        this.clientIp = slices[1];

        this.serverName = slices[2];

        this.serverIp = slices[3];
    }

    @Override
    public void execute() {
        if (GameController.getInstance().getServers() == null) {
            GameController.getInstance().setServers(new HashMap<String, GameServer>());
        }

        if (GameController.getInstance().getServers().containsKey(this.serverIp)) {

            GameServer gameServer = new GameServer();

            gameServer.setIp(this.serverIp);
            gameServer.setName(this.serverName);

            GameController.getInstance().getServers().put(this.serverIp, gameServer);

        }
    }

    @Override
    public String toString() {
        return this.id + Constants.SPACE + this.clientIp + Constants.SPACE + this.serverName + Constants.SPACE
                + this.serverIp;
    }

}
