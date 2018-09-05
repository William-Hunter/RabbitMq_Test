package scene.chatroom;

import publishsubscribe.ChatNode;
import queue.SelfTalker;
import util.ConfigProperties;

import java.io.IOException;

/**
 * Created by william on 2018/6/25.
 */
public class Candy {

    public static void main(String[] argv) throws IOException {
        ChatNode candy=new ChatNode(ConfigProperties.getConfigByName("host"),"candy","candy");
        candy.listener("log");
        candy.monitor("log");
    }

}
