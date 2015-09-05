package br.unb.shooter.net;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import br.unb.shooter.net.message.Message;

public class ShooterListener extends Listener {

    public void received(Connection c, Object p) {
        Message m = Message.makeMessage(p.toString());
        if (m != null) {
            m.execute();
        }
    }

}
