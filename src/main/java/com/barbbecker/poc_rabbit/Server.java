package com.barbbecker.poc_rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Server {

    public static void main(String[] args) throws IOException, TimeoutException{

        ConnectionFactory connectionFactory = new ConnectionFactory();
//        connectionFactory.setUsername("username");
//        connectionFactory.setPassword("password");
//        connectionFactory.setHost("ip-server");

        try (Connection connection = connectionFactory.newConnection()) {
            Channel channel = connection.createChannel();
            channel.queueDeclare("nomeDaFila", false, false, false, null);
            String message = "Olá Mundo";
            channel.basicPublish("", "nomeDaFila", null, message.getBytes("UTF-8"));

            System.out.println("Mensagem enviada!");
        }
    }
}
