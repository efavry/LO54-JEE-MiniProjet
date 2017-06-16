package com.github.lo54_project.subscriber;

import javax.jms.MessageListener;
import java.io.Serializable;

public interface ISubscriber {
    public int subscribe(String topic, MessageListener listener);
    public boolean unsubscribe(int suscriberID);
    public boolean startConnection();
    public boolean stopConnection();
    public boolean closeConnection();
}
