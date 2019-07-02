package com.barbbecker.poc_rabbit;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {

    public static void main(String[] args) throws IOException, TimeoutException{
        ConnectionFactory connectionFactory = new ConnectionFactory();

        try (Connection connection = connectionFactory.newConnection()) {
            Channel channel = connection.createChannel();
            channel.queueDeclare("nomeDaFila", false, false, false, null);

            com.rabbitmq.client.Consumer consumer = new DefaultConsumer(channel) {
                public void handleDelivery(String consumerTag, Envelope envelope,
                                           AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println("Mensagem recebida: " + message);
                }
            };
        }
    }
}
