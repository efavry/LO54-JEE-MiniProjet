package com.github.lo54_project.broker;

import org.apache.activemq.broker.BrokerService;

import java.util.Formatter;

/**
 * Created by Notmoo on 27/05/2017.
 */
public class ActiveMQJMSBroker implements IJMSBroker{

    private BrokerService broker;

    public ActiveMQJMSBroker() {
        this.broker = new BrokerService();
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

    public void setName(String name) {
        broker.setBrokerName(name);
    }

    public String getName() {
        return broker.getBrokerName();
    }

    public void setId(String id) {
        broker.setBrokerId(id);
    }

    public boolean addConnector(String protocol, String address, int port) {
        try {
            broker.addConnector(String.format("%s://%s:%s", protocol, address, Integer.toString(port)));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
