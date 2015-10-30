package br.unb.shooter.net.message;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.badlogic.gdx.utils.TimeUtils;

public class MessageQueue {

    public static final Integer LAG = 1000;

    private Integer messageSequence;

    private ConcurrentLinkedQueue<Message> messages;

    public MessageQueue() {
        messageSequence = 0;
        messages = new ConcurrentLinkedQueue<Message>();
    }

    /**
     * Add message to the queue.
     *
     * @param msg
     */
    public void addMessage(Message msg) {
        messages.add(msg);
    }

    /**
     * Retrieves next message sequence.
     *
     * @return
     */
    public Integer nextSequence() {
        messageSequence++;
        return messageSequence;
    }

    /**
     * Execute messages with fake lag.
     */
    public void executeMessages() {
        if (this.messages.size() > 0) {
            LinkedList<Message> snap = new LinkedList<Message>();
            for (Message msg : this.messages) {
                snap.add(msg);
            }
            List<Message> removables = new ArrayList<Message>();
            long timestamp = snap.getFirst().getTimestamp();
            if (TimeUtils.timeSinceMillis(timestamp) > LAG) {
                Iterator<Message> it = snap.iterator();
                while (it.hasNext()) {
                    Message message = it.next();
                    long messageTimestamp = message.getTimestamp();
                    if (!message.getIsExecuted() && TimeUtils.timeSinceMillis(messageTimestamp) > LAG) {
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
                    this.messages.remove(msg);
                }
            }
        }
    }

    public Integer getMessageSequence() {
        return messageSequence;
    }

    public void setMessageSequence(Integer messageSequence) {
        this.messageSequence = messageSequence;
    }

    public ConcurrentLinkedQueue<Message> getMessages() {
        return messages;
    }

    public void setMessages(ConcurrentLinkedQueue<Message> messages) {
        this.messages = messages;
    }

}
