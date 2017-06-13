package com.github.lo54_project.subscriber;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Notmoo on 27/05/2017.
 */
public class ActiveMQSubscriber implements ISubscriber {

    private int topicSubCounter;
    private TopicConnectionFactory connectionFactory;
    private TopicConnection connection;
    private TopicSession session;

    private Map<Integer, TopicSubscriber> subscribers;

    public ActiveMQSubscriber(String url) throws JMSException {
        if(url==null || url.isEmpty())
            throw new IllegalArgumentException("Empty url is not allowed");

        topicSubCounter = 1;
        connectionFactory = new ActiveMQConnectionFactory(url);
        connection = connectionFactory.createTopicConnection();

        subscribers = new HashMap<>();
    }

    public int subscribe(String title, MessageListener listener) {
        if(session!=null && title!= null && !title.isEmpty()) {

            if(topicSubCounter ==Integer.MAX_VALUE)
                throw new IndexOutOfBoundsException("Too many topics registered.");

            try {
                TopicSubscriber sub = session.createSubscriber(session.createTopic(title));
                sub.setMessageListener(listener);
                subscribers.put(topicSubCounter, sub);
                topicSubCounter++;
                return topicSubCounter -1;
            } catch (JMSException e) {
                e.printStackTrace();
            }

        }
        return -1;
    }

    public boolean unsubscribe(int subscriberID) {
        if(session!=null) {
            if(subscribers.containsKey(subscriberID)){
                try {
                    subscribers.get(subscriberID).close();
                    subscribers.remove(subscriberID);
                    return true;
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
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

    public boolean stopConnection() {
        try {
            session.close();
            connection.stop();
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
