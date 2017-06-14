package com.github.lo54_project.broker;

/**
 * Created by Notmoo on 27/05/2017.
 */
public interface IJMSBroker {

    void start() throws Exception;
    void waitUntilStarted();
    void stop() throws Exception;
    void waitUntilStopped();
}
