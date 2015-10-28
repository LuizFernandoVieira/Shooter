package br.unb.shooter.net;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;

import br.unb.shooter.controller.NetController;
import br.unb.shooter.net.message.Message;

public class ShooterListener extends Listener {

    @Override
    public void received(Connection connection, Object object) {
        Message message = Message.makeMessage(object.toString());
        if (message != null) {
            // message.execute();
            NetController.getInstance().getMessages().add(message);
            Log.debug("Received: " + message.toString());
        }
    }

}
