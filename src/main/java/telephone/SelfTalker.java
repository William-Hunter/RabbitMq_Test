package telephone;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by william on 2018/6/25.
 */
public class SelfTalker {

    private String host;
    private String username;
    private String password;
    private Connection connection;
    private Channel channel;

    public SelfTalker(String host, String username, String password) throws IOException {
        this.host = host;
        this.username = username;
        this.password = password;

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(this.host);
        factory.setUsername(this.username);
        factory.setPassword(this.password);
        connection = factory.newConnection();
        this.channel = connection.createChannel();
    }


    public void listener(String QUEUE_NAME) throws IOException {
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*]  您已进入聊天室，退出请按 CTRL+C");
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            }
        };
        boolean autoAck = true; // acknowledgment is covered below
        channel.basicConsume(QUEUE_NAME, autoAck, consumer);

        channel.basicPublish("", QUEUE_NAME, null, new String(this.username+"登陆到了聊天室").getBytes());
    }

    public void monitor(String QUEUE_NAME) throws IOException {
        Scanner reader=new Scanner(System.in);
        String message =null;
        do {
            message = reader.nextLine();
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        }while (!message.equals("END"));                        //循环发送消息，直到用户输入END命令
    }

    public void close() throws IOException {
        this.channel.close();
        this.connection.close();
    }





}
