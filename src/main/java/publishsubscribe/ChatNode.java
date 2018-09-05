package publishsubscribe;

import com.rabbitmq.client.*;
import util.ConfigProperties;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by william on 2018/9/5.
 */
public class ChatNode {
    private String host;
    private String username;
    private String password;
    private Connection connection;
    private Channel channel;

    public ChatNode(String host,String username,String password) throws IOException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setUsername(username);
        factory.setPassword(password);
        this.host=host;
        this.username=username;
        this.password=password;
        this.connection = factory.newConnection();
        this.channel = connection.createChannel();
    }

    public void listener(String EXCHANGE_NAME) throws IOException {
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, "");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("\n"+ message );
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }


    /**
     * 监视用户输入
     * @param EXCHANGE_NAME
     * @throws IOException
     */
    public void monitor(String EXCHANGE_NAME) throws IOException {
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        Scanner reader=new Scanner(System.in);
        String message =null;
        do {
            message = this.username+":"+reader.nextLine();
            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
        }while (!message.equals("END"));                        //循环发送消息，直到用户输入END命令
        close();
    }

    public void close() throws IOException {
        this.channel.close();
        this.connection.close();
    }

}
