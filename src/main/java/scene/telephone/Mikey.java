package scene.telephone;

import util.ConfigProperties;
import worker.SelfTalker;

import java.io.IOException;

/**
 * Created by william on 2018/6/25.
 */
public class Mikey {
    public static void main(String[] argv) throws IOException {
        SelfTalker mikey=new SelfTalker(ConfigProperties.getConfigByName("host"),"mikey","mikey");
        mikey.listener("candy2mikey");
        mikey.monitor("mikey2candy");
    }


}
