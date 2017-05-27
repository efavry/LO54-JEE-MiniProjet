package com.github.lo54_project.core.Repository.PublisherDao;

import com.github.lo54_project.core.Repository.PublisherDao.exceptions.PublisherDaoException;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class JmsDao implements IPublisherDao {

    private int topicIdCounter;
    private TopicConnectionFactory connectionFactory;
    private TopicConnection connection;
    private TopicSession session;

    private Map<Integer, Topic> topics;

    public JmsDao(String url) throws JMSException {
        if(url==null || url.isEmpty())
            throw new IllegalArgumentException("Empty url is not allowed");

        topicIdCounter = 1;
        connectionFactory = new ActiveMQConnectionFactory(url);
        connection = connectionFactory.createTopicConnection();

        topics = new HashMap<>();
    }

    @Override
    public int addTopic(String title) throws PublisherDaoException {
        if(session!=null && title!= null && !title.isEmpty()) {

            if(topicIdCounter==Integer.MAX_VALUE)
                throw new IndexOutOfBoundsException("Too many topics registered.");

            try {
                Topic topic = session.createTopic(title);
                topics.put(topicIdCounter, topic);
                topicIdCounter++;
                return topicIdCounter-1;
            } catch (JMSException e) {
                throw new PublisherDaoException(e);
            }
        }
        return -1;
    }

    @Override
    public boolean removeTopic(int topicID) throws PublisherDaoException {
        if(topics.containsKey(topicID)){
            topics.remove(topicID);
            return true;
        }
        return false;
    }

    @Override
    public boolean publishText(int topicID, String text) throws PublisherDaoException{
        if(session == null)
            return false;

        try {
            TextMessage msg = session.createTextMessage();
            msg.setText(text);
             return publishMessage(topicID, msg);
        } catch (JMSException e) {
            throw new PublisherDaoException(e);
        }
    }

    @Override
    public boolean publishObject(int topicID, Serializable obj) throws PublisherDaoException {
        if(session == null)
            return false;

        try {
            ObjectMessage msg = session.createObjectMessage();
            msg.setObject(obj);
            return publishMessage(topicID, msg);
        } catch (JMSException e) {
            throw new PublisherDaoException(e);
        }
    }

    private boolean publishMessage(int topicID, Message message) throws PublisherDaoException {
        if(topics.containsKey(topicID) && topics.get(topicID)!=null){
            try {
                TopicPublisher publisher = session.createPublisher(topics.get(topicID));
                publisher.publish(message);
                return true;
            } catch (JMSException e) {
                throw new PublisherDaoException(e);
            }
        }
        return false;
    }

    @Override
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

    @Override
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

    @Override
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
