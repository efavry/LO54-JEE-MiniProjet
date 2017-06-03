package com.github.lo54_project;

import com.github.lo54_project.broker.ActiveMQJMSBroker;
import com.github.lo54_project.broker.IJMSBroker;
import com.github.lo54_project.subscriber.ActiveMQSubscriber;
import com.github.lo54_project.subscriber.ISubscriber;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * Created by Notmoo on 03/06/2017.
 */
public class Main extends Application{
    public static void main(String[] args){
        launch();
    }

    private TextArea textArea;
    private IJMSBroker broker;
    private ISubscriber subscriber;

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane mainPane = new BorderPane();
        textArea = new TextArea();
        textArea.setWrapText(true);
        mainPane.setCenter(textArea);

        primaryStage.setOnCloseRequest((event)->{
            if(subscriber!=null){
                subscriber.closeConnection();
            }
            if(broker!=null){
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
        broker = new ActiveMQJMSBroker();
        try {
            broker.setName("lo54_jms_broker");
            broker.addConnector("tcp", "localhost", 10000);
            broker.start();
            appendLine("JSM broker started");

            subscriber = new ActiveMQSubscriber("tcp://localhost:10000");
            if(subscriber.startConnection()) {
                subscriber.subscribe("lo54/subscription", message -> {
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
                appendLine("Subscribed to 'lo54/subscription' on 'tcp://localhost:10000' successfully");
            }else{
                appendLine("Failed to subscribe to 'lo54/subscription' on 'tcp://localhost:10000'");
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
