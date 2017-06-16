package com.github.lo54_project.broker_jms.subscriber;

import javax.jms.MessageListener;
import java.io.Serializable;

public interface ISubscriber {
    public int subscribe(String topic, MessageListener listener);
    public boolean startConnection();
    public boolean closeConnection();
}
