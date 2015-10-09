package br.unb.shooter.net;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import br.unb.shooter.controller.NetController;
import br.unb.shooter.net.message.Message;

public class ShooterListener extends Listener {

    @Override
    public void received(Connection connection, Object object) {
        Message message = Message.makeMessage(object.toString());
        if (message != null) {
            // message.execute();
            Gdx.app.log("MESSAGE", message.getId() + " - server: " + NetController.getInstance().getIsServer());
            NetController.getInstance().getMessages().add(message);
        }
    }

}
