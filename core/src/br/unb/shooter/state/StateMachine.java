package br.unb.shooter.state;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;

import br.unb.shooter.controller.NetController;
import br.unb.shooter.net.message.Message;

public class StateMachine {

    private IState state = new StartState();

    public void create() {
        state.create(this);
    }

    public void update() {
        if (NetController.getInstance().getMessages().size() > 0) {
            List<Message> removables = new ArrayList<Message>();
            long timestamp = NetController.getInstance().getMessages().getFirst().getTimestamp();
            if (TimeUtils.timeSinceMillis(timestamp) > NetController.LAG) {
                Iterator<Message> it = NetController.getInstance().getMessages().iterator();
                while (it.hasNext()) {
                    Message message = it.next();
                    long messageTimestamp = message.getTimestamp();
                    if (!message.getIsExecuted() && TimeUtils.timeSinceMillis(messageTimestamp) > NetController.LAG) {
                        Gdx.app.log("EXECUTED", "message: " + message.getId());
                        message.execute();
                        message.setIsExecuted(true);
                        removables.add(message);
                    } else {
                        break;
                    }
                }
            }
            if (removables.size() > 0) {
                for (Message msg : removables) {
                    NetController.getInstance().getMessages().remove(msg);
                }
            }
        }

        state.update();
    }

    public void draw() {
        state.draw();
    }

    public void dispose() {
        state.dispose();
    }

    /**
     * Change state of the machine.
     * 
     * @param state state
     */
    public void changeState(IState state) {
        this.state.dispose();
        this.state = state;
        this.state.create(this);
    }

    /**
     * Handle state transitions.
     * 
     * @param endTimer
     */
    public void handle(StateEventEnum event) {
        this.state.handle(event);
    }

    public IState getState() {
        return state;
    }

    public void setState(IState state) {
        this.state = state;
    }

}
