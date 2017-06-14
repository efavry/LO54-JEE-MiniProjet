package com.github.lo54_project.app.repository.publisherdao;

import com.github.lo54_project.app.repository.publisherdao.exceptions.PublisherDaoException;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsDao implements IPublisherDao {

    private TopicConnectionFactory connectionFactory;
    private TopicConnection connection;
    private TopicSession session;

    public JmsDao() throws JMSException {
        this(null);
    }

    public JmsDao(String url) throws JMSException {
        if(url==null)
            url=ActiveMQConnectionFactory.DEFAULT_BROKER_URL;
        connectionFactory = new ActiveMQConnectionFactory(url);
        connection = null;
    }

    @Override
    public boolean publishText(String topic, String text) throws PublisherDaoException{
        if(session == null)
            return false;

        try {
            TextMessage msg = session.createTextMessage(text);
            return publishMessage(topic, msg);
        } catch (JMSException e) {
            throw new PublisherDaoException(e);
        }
    }

    private boolean publishMessage(String topic, Message message) throws PublisherDaoException {
        if(topic == null || session==null)
            return false;

        try {
            Topic jmsTopic = session.createTopic("topic");
            TopicPublisher publisher = session.createPublisher(jmsTopic);
            publisher.send(message);
            return true;
        } catch (JMSException e) {
            throw new PublisherDaoException(e);
        }
    }

    @Override
    public boolean startConnection() {
        try {
            connection = connectionFactory.createTopicConnection();
            session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

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
