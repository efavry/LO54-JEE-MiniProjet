package com.github.lo54_project.broker_jms;


import com.github.lo54_project.broker_jms.broker.ActiveMQJMSBroker;
import com.github.lo54_project.broker_jms.broker.IJMSBroker;
import com.github.lo54_project.broker_jms.subscriber.ActiveMQSubscriber;
import com.github.lo54_project.broker_jms.subscriber.ISubscriber;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.JMSException;
import javax.jms.TextMessage;

public class Main extends Application{
    public static void main(String[] args){
        launch();
    }

    private TextArea textArea;
    private ISubscriber subscriber;
    private IJMSBroker broker;

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane mainPane = new BorderPane();
        textArea = new TextArea();
        textArea.setWrapText(true);
        textArea.setEditable(false);
        mainPane.setCenter(textArea);

        primaryStage.setOnCloseRequest((event)->{
            if(subscriber!=null){
                subscriber.closeConnection();
                try {
                    broker.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Scene scene = new Scene(mainPane, 500,600);
        primaryStage.setScene(scene);
        primaryStage.show();

        initJMS();
    }

    private void initJMS(){

        try {
            String topic = "topic";
            String url = "tcp://localhost:61616";

            broker = new ActiveMQJMSBroker(url);
            broker.start();
            broker.waitUntilStarted();
            appendLine("Broker started on '"+url+"'");
            try {
                subscriber = new ActiveMQSubscriber();
                if(subscriber.startConnection()) {
                    subscriber.subscribe(topic, message -> {
                        if (TextMessage.class.isInstance(message)) {
                            try {
                                appendLine("incoming text message : " + ((TextMessage) message).getText());
                            } catch (JMSException e) {
                                appendLine(e.getMessage());
                            }

                        } else {
                            appendLine("incoming message : " + message.toString());
                        }
                    });
                    appendLine("Subscribed to '"+topic+"' on '"+url+"' successfully");
                }else{
                    appendLine("Failed to subscribe to '"+topic+"' on '"+url+"'");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void appendLine(String text){
        Platform.runLater(()->{
            textArea.appendText("> ");
            textArea.appendText(text);
            textArea.appendText("\n");
        });
    }
}
