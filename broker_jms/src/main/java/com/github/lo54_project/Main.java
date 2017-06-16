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
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.net.Inet4Address;

/**
 * Created by Notmoo on 03/06/2017.
 */
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
            String clientUrl = "tcp://"+ Inet4Address.getLocalHost().getHostAddress()+":61616";
            String brokerConnectorUrl = "tcp://0.0.0.0:61616";

            appendLine("Attempting to start broker");
            broker = new ActiveMQJMSBroker(brokerConnectorUrl);
            broker.start();
            broker.waitUntilStarted();
            appendLine("Broker started on '"+brokerConnectorUrl+"'");
            try {
                subscriber = new ActiveMQSubscriber(clientUrl);
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
                    appendLine("Subscribed to '"+topic+"' on '"+clientUrl+"' successfully");
                }else{
                    appendLine("Failed to subscribe to '"+topic+"' on '"+clientUrl+"'");
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
