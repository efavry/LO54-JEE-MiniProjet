package com.github.lo54_project.app.repository.publisherdao;

import com.github.lo54_project.app.repository.publisherdao.exceptions.PublisherDaoException;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsDao implements IPublisherDao {

    private TopicConnectionFactory connectionFactory;
    private TopicConnection connection;
    private TopicSession session;

    private Topic registrationTopic;

    public JmsDao() throws JMSException {
        this(null);
    }

    public JmsDao(String url) throws JMSException {
        if(url==null)
            url=ActiveMQConnectionFactory.DEFAULT_BROKER_URL;
        connectionFactory = new ActiveMQConnectionFactory(url);
        connection = null;

        registrationTopic = null;
    }

    @Override
    public boolean publishText(int topicID, String text) throws PublisherDaoException{
        if(session == null)
            return false;

        try {
            TextMessage msg = session.createTextMessage(text);
            return publishMessage(msg);
        } catch (JMSException e) {
            throw new PublisherDaoException(e);
        }
    }

    private boolean publishMessage(Message message) throws PublisherDaoException {
        if(registrationTopic == null || session==null)
            return false;

        try {
            TopicPublisher publisher = session.createPublisher(registrationTopic);
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

            // create the Topic to which messages will be sent
            registrationTopic = session.createTopic("topic");

            return true;
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean closeConnection() {
        try {
            registrationTopic=null;
            session.close();
            connection.close();
            return true;
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return false;
    }
}
