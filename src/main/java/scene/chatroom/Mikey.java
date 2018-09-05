package scene.chatroom;

import publishsubscribe.ChatNode;
import queue.SelfTalker;
import util.ConfigProperties;

import java.io.IOException;

/**
 * Created by william on 2018/6/25.
 */
public class Mikey {
    public static void main(String[] argv) throws IOException {
        ChatNode mikey=new ChatNode(ConfigProperties.getConfigByName("host"),"mikey","mikey");
        mikey.listener("log");
        mikey.monitor("log");
    }


}
