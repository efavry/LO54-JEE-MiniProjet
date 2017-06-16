package com.github.lo54_project.broker;

import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;

import java.net.URI;
import java.util.Formatter;

/**
 * Created by Notmoo on 27/05/2017.
 */
public class ActiveMQJMSBroker implements IJMSBroker{

    private BrokerService broker;

    public ActiveMQJMSBroker(String url) throws Exception {
        broker = new BrokerService();
        broker.addConnector(url);
    }

    public void start() throws Exception {
        broker.start();
    }

    public void waitUntilStarted() {
        broker.waitUntilStarted();
    }

    public void stop() throws Exception {
        broker.stop();
    }

    public void waitUntilStopped() {
        broker.waitUntilStopped();
    }
}
