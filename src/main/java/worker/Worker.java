package worker;

/**
 * Created by william on 2018/6/22.
 */

import com.rabbitmq.client.*;
import util.ConfigProperties;
import java.io.IOException;

public class Worker {
    private final static String HOST = ConfigProperties.getConfigByName("host");
    private final static String QUEUE_NAME = "multipleWork";
    private final static String USERNAME = "candy";
    private final static String PASSWORD = "candy";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
                try {
                    doWork(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println(" [x] Done");
                }
            }
        };
        boolean autoAck = true; // acknowledgment is covered below
        channel.basicConsume(QUEUE_NAME, autoAck, consumer);
    }

    private static void doWork(String task) throws InterruptedException {
        for (char ch: task.toCharArray()) {
//            if (ch == '.') Thread.sleep(1000);
            Thread.sleep(1000);
        }
    }

}
