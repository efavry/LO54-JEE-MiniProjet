package com.github.lo54_project.broker;

public interface IJMSBroker {

    void start() throws Exception;
    void waitUntilStarted();
    void stop() throws Exception;
    void waitUntilStopped();
}
