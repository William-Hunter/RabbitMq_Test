/**
 * Created by william on 2018/6/22.
 */

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Send {

    private final static String QUEUE_NAME = "hello";
    private final static String HOST = "www.tongchuangkeji.net";
    private final static String USERNAME = "candy";
    private final static String PASSWORD = "candy";


    public static void main(String[] argv)throws java.io.IOException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" CONNECTED TO QUEUE:"+QUEUE_NAME+",of Host:"+HOST+",used by:"+USERNAME);

        String message = "Hello World! and the time is "+new SimpleDateFormat("HH:mm:ss").format(new Date());
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }






}
