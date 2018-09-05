package publishsubscribe;

import com.rabbitmq.client.*;
import util.ConfigProperties;

import java.io.IOException;
/**
 * Created by william on 2018/9/5.
 */
public class ReceiveLogs {
    private static final String EXCHANGE_NAME = "logs";
    private final static String HOST = ConfigProperties.getConfigByName("host");
    private final static String USERNAME = "candy";
    private final static String PASSWORD = "candy";



    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, "");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }


}
