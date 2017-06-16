package com.github.lo54_project.broker_jms.broker;


import org.apache.activemq.broker.BrokerService;


public class ActiveMQJMSBroker implements IJMSBroker
{

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
