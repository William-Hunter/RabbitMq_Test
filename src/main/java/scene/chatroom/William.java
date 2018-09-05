package scene.chatroom;

import publishsubscribe.ChatNode;
import util.ConfigProperties;

import java.io.IOException;

/**
 * Created by william on 2018/6/25.
 */
public class William {
    public static void main(String[] argv) throws IOException {
        ChatNode william=new ChatNode(ConfigProperties.getConfigByName("host"),"william","lone");
        william.listener("log");
        william.monitor("log");
    }


}
