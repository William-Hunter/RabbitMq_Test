package worker;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import util.ConfigProperties;

import java.util.Scanner;

/**
 * Created by william on 2018/6/25.
 */
public class NewTask {
    private final static String HOST = ConfigProperties.getConfigByName("host");
    private final static String QUEUE_NAME = "multipleWork";
    private final static String USERNAME = "william";
    private final static String PASSWORD = "lone";

    public static void main(String[] argv)throws java.io.IOException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        Scanner reader=new Scanner(System.in);
        String message =null;
        do {
            System.out.print(" [$] :");
            message = reader.nextLine();
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }while (!message.equals("END"));                        //循环发送消息，知道用户输入END命令

        channel.close();
        connection.close();
    }

    private static String getMessage(String[] strings){
        if (strings.length < 1){
            return "Hello World!";
        }
        return joinStrings(strings, " ");
    }

    private static String joinStrings(String[] strings, String delimiter) {
        int length = strings.length;
        if (length == 0) {return "";}
        StringBuilder words = new StringBuilder(strings[0]);
        for (int i = 1; i < length; i++) {
            words.append(delimiter).append(strings[i]);
        }
        return words.toString();
    }

}
