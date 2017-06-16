package com.github.lo54_project.subscriber;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Notmoo on 27/05/2017.
 */
public class ActiveMQSubscriber implements ISubscriber {

    private TopicConnectionFactory connectionFactory;
    private TopicConnection connection;
    private TopicSession session;

    public ActiveMQSubscriber() throws JMSException {
        this(null);
    }

    public ActiveMQSubscriber(String url) throws JMSException {
        if(url==null)
            url = ActiveMQConnectionFactory.DEFAULT_BROKER_URL;

        connectionFactory = new ActiveMQConnectionFactory(url);
        connection = connectionFactory.createTopicConnection();

    }

    public int subscribe(String title, MessageListener listener) {
        if(session!=null && title!= null && !title.isEmpty()) {
            try {
                TopicSubscriber sub = session.createSubscriber(session.createTopic(title));
                sub.setMessageListener(listener);
            } catch (JMSException e) {
                e.printStackTrace();
            }

        }
        return -1;
    }

    public boolean startConnection() {
        try {
            connection.start();
            session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            return true;
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean closeConnection() {
        try {
            session.close();
            connection.close();
            return true;
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return false;
    }
}
