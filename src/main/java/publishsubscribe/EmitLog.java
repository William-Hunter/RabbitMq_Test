package publishsubscribe;
import java.io.IOException;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import util.ConfigProperties;

/**
 * Created by william on 2018/9/5.
 */
public class EmitLog {
    private static final String EXCHANGE_NAME = "logs";
    private final static String HOST = ConfigProperties.getConfigByName("host");
    private final static String USERNAME = "mikey";
    private final static String PASSWORD = "mikey";


    public static void main(String[] argv)
            throws java.io.IOException {

        argv=new String[]{"=======","AAAAAA","BBBBBB"};

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        String message = getMessage(argv);

        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }

    private static String getMessage(String[] strings){
        if (strings.length < 1){
            return "info: Hello World!";}
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
